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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeEditor;
import com.gardenline.spring.web.dao.EmployeeEnabled;
import com.gardenline.spring.web.service.EmployeeEnabledService;
import com.gardenline.spring.web.service.EmployeeService;

@Controller
public class EmployeeEnabledController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeEnabledService employeeEnabledService;

	@Autowired
	private EmployeeEditor employeeEditor;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new
		 CustomDateEditor(dateFormat, true));

		
		binder.registerCustomEditor(Employee.class, this.employeeEditor);

	}

	@RequestMapping(value = "/allempenabled", method = RequestMethod.GET)
	public ModelAndView employeeEnabledList() {
		ModelAndView model = new ModelAndView("empenables");
		List<EmployeeEnabled> employeeEnabled = employeeEnabledService
				.getCurrent();
		model.addObject("employeeEnabled", employeeEnabled);
		return model;
	}

	@RequestMapping("/addEmployeeEnabled/{empId}")
	public String addEmployeeEnabled(@PathVariable("empId") int empId,
			Model model) {
		model.addAttribute("employeeEnabled", new EmployeeEnabled());

		List<Employee> employee = employeeService.getEmployees(empId);
		model.addAttribute("employees", employee);
		return "addEmployeeEnabled";

	}

	@RequestMapping(value = "/createEmployeeEnabled", method = RequestMethod.POST)
	public String createEmployeeEnabled(
			@ModelAttribute("employeeEnabled") EmployeeEnabled employeeEnabled,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addEmployeeEnabled";
		}

		employeeEnabledService.createEmpEnabled(employeeEnabled);

		return "redirect:/allempenabled";
	}

	@RequestMapping(value = "/editEmployeeEnabled/{id}", method = RequestMethod.GET)
	public String editEmployeeEnabled(@PathVariable("id") int id, ModelMap model) {
		EmployeeEnabled empEnabled = employeeEnabledService
				.getEmployeeEnabled(id);
		List<Employee> employees = employeeEnabledService.getEmployeeForId(id);
		model.addAttribute("employees", employees);
		model.addAttribute("empEnabled", empEnabled);

		return "editEmployeeEnabled";
	}

	@RequestMapping(value = "/editEmployeeEnabled/{id}", method = RequestMethod.POST)
	public String postedEmployeeEnabled(
			@ModelAttribute("empEnabled") EmployeeEnabled employeeEnabled,
			@RequestParam(value = "enabled", required = true) boolean enabled,
			@PathVariable("id") int id, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "editEmployeeEnabled";
		}

		employeeEnabled.setEnabled(enabled);
		employeeEnabledService.updateEmployeeEnabled(id, employeeEnabled);

		return "redirect:/allempenabled";
	}

	@RequestMapping("/deleteEmployeeEnabled/{id}")
	public String deleteEmployeeEnabled(@PathVariable("id") int id, Model model) {
		employeeEnabledService.deleteEmployeeEnabled(id);
		return "redirect:/allempenabled";
	}

	@RequestMapping(value = "/empenabled/{empId}", method = RequestMethod.GET)
	public ModelAndView empEnabledList(@PathVariable("empId") int empId) {
		ModelAndView model = new ModelAndView("empenables");
		List<EmployeeEnabled> employeeEnabled = employeeEnabledService
				.getEmployeesEnabled(empId);
		model.addObject("employeeEnabled", employeeEnabled);
		return model;
	}

}
