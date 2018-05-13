package com.gardenline.spring.web.controllers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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

import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeEditor;
import com.gardenline.spring.web.dao.EmployeeSalary;
import com.gardenline.spring.web.service.EmployeeService;
import com.gardenline.spring.web.service.SalaryService;

@Controller
public class SalaryController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;


	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private EmployeeEditor employeeEditor;



	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	   // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Employee.class, this.employeeEditor);
		
	}

	@RequestMapping(value = "/allempsalaries", method = RequestMethod.GET)
	public ModelAndView empSalaryList() {
		ModelAndView model = new ModelAndView("empsalaries");
		List<EmployeeSalary> empSalaries = salaryService.getCurrent();
		model.addObject("empSalaries", empSalaries);
		return model;
	}

	
	@RequestMapping("/addEmpSalary/{empId}")
	public String addEmpSalary(@PathVariable("empId") int empId, Model model) {
		model.addAttribute("empSalary", new EmployeeSalary());
		List<Employee> employee = employeeService.getEmployees(empId);
		model.addAttribute("employees", employee);
		return "addEmpSalary";

	}

	@RequestMapping(value = "/createEmpSalary", method = RequestMethod.POST)
	public String createEmpSalary(
			@ModelAttribute("empSalary") EmployeeSalary empSalary,
			BindingResult result, ModelMap model) {


		if (result.hasErrors()) {
			return "addEmpSalary";
		}

		salaryService.createEmpSalary(empSalary);

		return "redirect:/allempsalaries";
	}


	@RequestMapping(value = "/editEmpSalary/{id}", method = RequestMethod.GET)
	public String editEmpSalary(@PathVariable("id") int id, ModelMap model) {
		
		EmployeeSalary empSalary = salaryService.getEmployeeSalary(id);
		List<Employee> employees = salaryService.getEmployeeForId(id);
		model.addAttribute("employees", employees);
		model.addAttribute("empSalary", empSalary);

		return "editEmpSalary";
	}

	@RequestMapping(value = "/editEmpSalary/{id}", method = RequestMethod.POST)
	public String postedEmpSalary(
			@ModelAttribute("empSalary") EmployeeSalary empSalary,
			@PathVariable("id") int id,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "editEmpSalary";
		}

	
		salaryService.updateEmployeeSalary(id, empSalary);

		return "redirect:/allempsalaries";
	}


	@RequestMapping("/deleteEmpSalary/{id}")
	public String deleteEmpSalary(@PathVariable("id") int id, Model model) {
		salaryService.deleteEmployeeSalary(id);
		return "redirect:/allempsalaries";
	}
	
	@RequestMapping(value = "/empsalaries/{empId}", method = RequestMethod.GET)
	public ModelAndView empJoblist(@PathVariable("empId") int empId) {
		ModelAndView model = new ModelAndView("empsalaries");
		List<EmployeeSalary> empSalaries = salaryService.getEmployeeSalaries(empId);
		model.addObject("empSalaries", empSalaries);
		return model;
	}
	
	
	

}
