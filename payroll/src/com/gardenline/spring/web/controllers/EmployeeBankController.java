package com.gardenline.spring.web.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.Bank;
import com.gardenline.spring.web.dao.BankEditor;
import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeBank;
import com.gardenline.spring.web.dao.EmployeeEditor;
import com.gardenline.spring.web.service.BankService;
import com.gardenline.spring.web.service.EmployeeBankService;
import com.gardenline.spring.web.service.EmployeeService;

@Controller
public class EmployeeBankController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;


	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeBankService employeeBankService;

	@Autowired
	private EmployeeEditor employeeEditor;

	@Autowired
	private BankEditor bankEditor;

	@Autowired
	private BankService bankService;



	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Employee.class, this.employeeEditor);
		binder.registerCustomEditor(Bank.class, this.bankEditor);
	}

	@RequestMapping(value = "/allempbanks", method = RequestMethod.GET)
	public ModelAndView employeeList() {
		ModelAndView model = new ModelAndView("employeebanks");
		List<EmployeeBank> employeeBanks = employeeBankService.getCurrent();
		model.addObject("employeeBanks", employeeBanks);
		return model;
	}

	
	@RequestMapping("/addEmployeeBank/{empId}")
	public String addEployeeBank(@PathVariable("empId") int empId, Model model) {
		model.addAttribute("employeeBank", new EmployeeBank());

		List<Bank> banks = bankService.getCurrent();
		List<Employee> employee = employeeService.getEmployees(empId);

		List<EmployeeBank> empBanks = employeeBankService.getEmployeeBanks(empId);
		for (EmployeeBank employeeBank : empBanks) {
			banks.removeIf(s -> s.getBankId() == employeeBank.getBank()
					.getBankId());
		}

		model.addAttribute("employees", employee);
		model.addAttribute("banks", banks);
		return "addEmployeeBank";

	}

	@RequestMapping(value = "/createEmployeeBank", method = RequestMethod.POST)
	public String createEmployeeBank(
			@ModelAttribute("employeeBank") EmployeeBank employeeBank,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addEmployeeBank";
		}

		employeeBankService.createBankEmp(employeeBank);

		return "redirect:/allempbanks";
	}


	@RequestMapping(value = "/editEmployeeBank/{id}", method = RequestMethod.GET)
	public String editEmployeeBAnk(@PathVariable("id") int id, ModelMap model) {
		List<Bank> banks = bankService.getCurrent();
		EmployeeBank empBank = employeeBankService.getEmployeeBank(id);
		List<Employee> employees = employeeService.getCurrent();
		model.addAttribute("employees", employees);
		model.addAttribute("banks", banks);
		model.addAttribute("empBank", empBank);

		return "editEmployeeBank";
	}

	@RequestMapping(value = "/editEmployeeBank/{id}", method = RequestMethod.POST)
	public String postEditEmployeeBank(
			@ModelAttribute("empBank") EmployeeBank employeeBank,
			@PathVariable("id") int id,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "editEmployeeBank";
		}

	
		employeeBankService.updateEmployeeBank(id, employeeBank);

		return "redirect:/allempbanks";
	}


	@RequestMapping("/deleteEmployeeBank/{id}")
	public String deleteEmployeeBank(@PathVariable("id") int id, Model model) {
		employeeBankService.deleteBankEmployee(id);
		return "redirect:/allempbanks";
	}
	
	@RequestMapping(value = "/empbanks/{empId}", method = RequestMethod.GET)
	public ModelAndView empBankList(@PathVariable("empId") int empId) {
		ModelAndView model = new ModelAndView("employeebanks");
		List<EmployeeBank> empBanks = employeeBankService.getEmployeeBanks(empId);
		model.addObject("employeeBanks", empBanks);
		return model;
	}


}
