package com.gardenline.spring.web.controllers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.Bank;
import com.gardenline.spring.web.dao.EmpBankCollectionEditor;
import com.gardenline.spring.web.dao.EmpJobCollectionEditor;
import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeBank;
import com.gardenline.spring.web.dao.EmployeeEnabled;
import com.gardenline.spring.web.dao.EmployeeJob;
import com.gardenline.spring.web.dao.EmployeeSalary;
import com.gardenline.spring.web.dao.FormValidationGroup;
import com.gardenline.spring.web.dao.Job;
import com.gardenline.spring.web.service.BankService;
import com.gardenline.spring.web.service.EmployeeBankService;
import com.gardenline.spring.web.service.EmployeeEnabledService;
import com.gardenline.spring.web.service.EmployeeJobService;
import com.gardenline.spring.web.service.EmployeeService;
import com.gardenline.spring.web.service.JobService;
import com.gardenline.spring.web.service.SalaryService;

@Controller
public class EmployeeController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;


	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeJobService employeeJobService;
	
	@Autowired
	private SalaryService employeeSalaryService;
	
	@Autowired
	private EmployeeEnabledService employeeEnabledService;

	@Autowired
	private EmployeeBankService employeeBankService;

	@Autowired
	private JobService jobService;

	@Autowired
	private BankService bankService;

	@Autowired
	private ServletContext servletContext;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));

		binder.registerCustomEditor(ArrayList.class, "empJobs",
				new EmpJobCollectionEditor(ArrayList.class, jobService));
		binder.registerCustomEditor(ArrayList.class, "empBanks",
				new EmpBankCollectionEditor(ArrayList.class, bankService));

	}

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public ModelAndView employeeList() {
		ModelAndView model = new ModelAndView("employees");
		List<Employee> list = employeeService.getCurrent();
		List<EmployeeJob> empJobs2 = employeeJobService.getCurrent();

		Map<Integer, List<EmployeeJob>> empJobs = new HashMap<Integer, List<EmployeeJob>>();

		for (Employee employee : list) {
			empJobs.put(
					employee.getEmpId(),
					empJobs2.stream()
							.filter(s -> s.getEmployee().getEmpId() == employee
									.getEmpId()).collect(Collectors.toList()));

		}

		List<EmployeeBank> empBanks2 = employeeBankService.getCurrent();

		Map<Integer, List<EmployeeBank>> empBanks = new HashMap<Integer, List<EmployeeBank>>();

		for (Employee employee : list) {
			empBanks.put(
					employee.getEmpId(),
					empBanks2
							.stream()
							.filter(s -> s.getEmployee().getEmpId() == employee
									.getEmpId()).collect(Collectors.toList()));

		}

		model.addObject("employees", list);
		model.addObject("empJobs", empJobs);
		model.addObject("empBanks", empBanks);

		return model;
	}

	@RequestMapping(value = "/searchEnabledEmployees", method = RequestMethod.GET)
	public ModelAndView searchEnabledEmployeeList() {
		ModelAndView model = new ModelAndView("searchenabledemployees");
		model.addObject("employeeenabled", new EmployeeEnabled());
		return model;

	}

	@RequestMapping(value = "/enabledEmployees", method = RequestMethod.GET)
	public ModelAndView enabledEmployeeList(
			@ModelAttribute("employeeenabled") EmployeeEnabled employeeEnabled,
			@RequestParam Date startDate, @RequestParam boolean enabled) {
		ModelAndView model = new ModelAndView("employees");
		List<Employee> list = employeeService.getEnabledEmployees(startDate,
				enabled);
		
		model.addObject("employees", list);
		

		return model;
	}

	@RequestMapping("/newemployee")
	public String addEployee(Model model) {
		model.addAttribute("employee", new Employee());
		List<Job> jobs = jobService.getCurrent();
		List<Bank> banks = bankService.getCurrent();
		model.addAttribute("jobs", jobs);
		model.addAttribute("banks", banks);
		return "newemployee";

	}

	@RequestMapping(value = "/createemployee", method = RequestMethod.POST)
	public String createemployee(@ModelAttribute("employee") @Validated(FormValidationGroup.class)Employee employee,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "newemployee";
		}

		if (employeeService.exists(employee.getSecurityNumber())) {
			System.out.println("Caught duplicate security number");
			result.rejectValue("securityNumber",
					"DuplicateKey.employee.securityNumber");
			return "newemployee";
		}

		try {
			employeeService.create(employee);
			MultipartFile cardImage = employee.getEmployeeCard();
			saveImage(employee.getEmpId() + ".png", cardImage);

		} catch (DuplicateKeyException e) {
			result.rejectValue("securityNumber",
					"DuplicateKey.employee.security number",
					"This employee already exist");
			return "newemployee";
		} catch (RuntimeException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return "redirect:/employees";
	}

	private void saveImage(String filename, MultipartFile image)
			throws RuntimeException, IOException {
		try {
			File file = new File(servletContext.getRealPath("/")
					+ "resources\\images\\" + filename);
			System.out.println(file);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
			
		} catch (IOException e) {
			throw e;
		}
	}

	@RequestMapping(value = "/editEmployee/{empId}", method = RequestMethod.GET)
	public String editEmployee(@PathVariable("empId") int id, ModelMap model) {

		Employee employee = employeeService.getEmployee(id);
		model.addAttribute("employee", employee);
		return "editEmployee";
	}

	@RequestMapping(value = "/editEmployee/{empId}", method = RequestMethod.POST)
	public String postEditEmployee(
			@ModelAttribute("employee") Employee employee,
			@PathVariable("empId") int empId, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "editEmployee";
		}
		
		MultipartFile cardImage = employee.getEmployeeCard();
		try {

			saveImage(empId + ".png", cardImage);

			employeeService.updateEmployee(empId, employee);

		}catch (RuntimeException | IOException e) {

			e.printStackTrace();
		}

		return "redirect:/employees";
	}

	@RequestMapping("/viewEmployee/{empId}")
	public String getEmployee(@PathVariable int empId, Model model) {
		Employee employee = employeeService.getEmployee(empId);

		List<EmployeeJob> empJobs2 = employeeJobService.getCurrent();
		
		List<EmployeeSalary> empSalary2 = employeeSalaryService.getCurrent();

		Map<Integer, List<EmployeeJob>> empJobs = new HashMap<Integer, List<EmployeeJob>>();

		empJobs.put(
				employee.getEmpId(),
				empJobs2.stream()
						.filter(s -> s.getEmployee().getEmpId() == employee
								.getEmpId()).collect(Collectors.toList()));
		Map<Integer, List<EmployeeSalary>> empSalaries = new HashMap<Integer, List<EmployeeSalary>>();

		empSalaries.put(
				employee.getEmpId(),
				empSalary2.stream()
						.filter(s -> s.getEmployee().getEmpId() == employee
								.getEmpId()).collect(Collectors.toList()));
		model.addAttribute("employee", employee);
		model.addAttribute("empSalaries", empSalaries);
		model.addAttribute("empJobs", empJobs);

		return "viewEmployee";
	}

	@RequestMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable("id") int id, Model model) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees";
	}

}
