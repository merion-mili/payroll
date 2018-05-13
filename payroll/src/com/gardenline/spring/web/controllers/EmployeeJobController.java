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
import com.gardenline.spring.web.dao.EmployeeJob;
import com.gardenline.spring.web.dao.Job;
import com.gardenline.spring.web.dao.JobEditor;
import com.gardenline.spring.web.service.EmployeeJobService;
import com.gardenline.spring.web.service.EmployeeService;
import com.gardenline.spring.web.service.JobService;

@Controller
public class EmployeeJobController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;


	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeJobService employeeJobService;

	@Autowired
	private EmployeeEditor employeeEditor;

	@Autowired
	private JobEditor jobEditor;

	@Autowired
	private JobService jobService;



	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    dateFormat.setLenient(false);
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	    // true passed to CustomDateEditor constructor means convert empty String to null
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(Employee.class, this.employeeEditor);
		binder.registerCustomEditor(Job.class, this.jobEditor);
	}

	@RequestMapping(value = "/allempjobs", method = RequestMethod.GET)
	public ModelAndView employeeJobList() {
		ModelAndView model = new ModelAndView("empjobs");
		List<EmployeeJob> employeeJobs = employeeJobService.getCurrent();
		model.addObject("empJobs", employeeJobs);
		return model;
	}

	
	@RequestMapping("/addEmployeeJob/{empId}")
	public String addEmployeeJob(@PathVariable("empId") int empId, Model model) {
		model.addAttribute("employeeJob", new EmployeeJob());

		List<Job> jobs = jobService.getCurrent();
		List<Employee> employee = employeeService.getEmployees(empId);

		List<EmployeeJob> empJobs = employeeJobService.getEmployeeJobs(empId);
		for (EmployeeJob employeeJob : empJobs) {
			jobs.removeIf(s -> s.getJobId() == employeeJob.getJob()
					.getJobId());
		}

		model.addAttribute("employees", employee);
		model.addAttribute("jobs", jobs);
		return "addEmployeeJob";

	}

	@RequestMapping(value = "/createEmployeeJob", method = RequestMethod.POST)
	public String createEmployeeJob(
			@ModelAttribute("employeeJob") EmployeeJob employeeJob,
			BindingResult result, ModelMap model) {


		if (result.hasErrors()) {
			return "addEmployeeJob";
		}

		employeeJobService.createJobEmp(employeeJob);

		return "redirect:/allempjobs";
	}


	@RequestMapping(value = "/editEmployeeJob/{id}", method = RequestMethod.GET)
	public String editEmployeeJob(@PathVariable("id") int id, ModelMap model) {
		List<Job> jobs = jobService.getCurrent();
		EmployeeJob empJob = employeeJobService.getEmployeeJob(id);
		List<Employee> employees = employeeJobService.getEmployeeForId(id);
		model.addAttribute("employees", employees);
		model.addAttribute("jobs", jobs);
		model.addAttribute("empJob", empJob);

		return "editEmployeeJob";
	}

	@RequestMapping(value = "/editEmployeeJob/{id}", method = RequestMethod.POST)
	public String postedEmployeeJob(
			@ModelAttribute("empJob") EmployeeJob employeeJob,
			@PathVariable("id") int id,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "editEmployeeJob";
		}

		//employeeJob.setEnabled(enabled);
		employeeJobService.updateEmployeeJob(id, employeeJob);

		return "redirect:/allempjobs";
	}


	@RequestMapping("/deleteEmployeeJob/{id}")
	public String deleteEmployeeJob(@PathVariable("id") int id, Model model) {
		employeeJobService.deleteEmployeeJob(id);
		return "redirect:/allempjobs";
	}
	
	@RequestMapping(value = "/empjobs/{empId}", method = RequestMethod.GET)
	public ModelAndView empJoblist(@PathVariable("empId") int empId) {
		ModelAndView model = new ModelAndView("empjobs");
		List<EmployeeJob> employeeJobs = employeeJobService.getEmployeeJobs(empId);
		model.addObject("empJobs", employeeJobs);
		return model;
	}
	
	
	/*@RequestMapping(value = "/changeSalary/{empId}", method = RequestMethod.GET)
	public String changeSalary(@PathVariable("empId") int empId,
			ModelMap model) {
		List<Employee> employees = employeeService.getEmployees(empId);
		List<Job> jobs = employeeJobService.getJobsForEmployee(empId);
		
		
		model.addAttribute("employeeJob", new EmployeeJob());
		model.addAttribute("employees", employees);
		model.addAttribute("jobs", jobs);
		
		return "changeEmployeeSalary";
	}

	
	@RequestMapping(value = "/createEmployeeSalary", method = RequestMethod.POST)
	public String createEmployeeSalary(
			@ModelAttribute("employeeJob") EmployeeJob employeeJob,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "changeEmployeeSalary";
		}

		
		employeeJobService.createJobEmp(employeeJob);

		return "redirect:/employees";
	}*/


}
