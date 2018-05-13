package com.gardenline.spring.web.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeBank;
import com.gardenline.spring.web.dao.EmployeeSalaryStanding;
import com.gardenline.spring.web.dao.Muajt;
import com.gardenline.spring.web.dao.Paga;
import com.gardenline.spring.web.dao.Year;
import com.gardenline.spring.web.dao.YearEditor;
import com.gardenline.spring.web.service.EmployeeBankService;
import com.gardenline.spring.web.service.EmployeeSalaryService;
import com.gardenline.spring.web.service.EmployeeService;
import com.gardenline.spring.web.service.YearService;

@Controller
public class EmployeeSalaryController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;

	@Autowired
	private EmployeeSalaryService employeeSalaryService;

	@Autowired
	private YearEditor yearEditor;

	@Autowired
	private YearService yearService;

	@Autowired
	private EmployeeBankService employeeBankService;

	@Autowired
	private EmployeeService employeeService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Year.class, this.yearEditor);

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search() {
		ModelAndView model = new ModelAndView("employeesalarystanding");
		List<Muajt> enums = Arrays.asList(Muajt.values());
		List<Year> years = yearService.getCurrent();
		model.addObject("enums", enums);
		System.out.println(enums.size());
		model.addObject("years", years);
		model.addObject("employeesalary", new Paga());
		return model;

	}

	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public ModelAndView employeeSalaryStanding(
			@ModelAttribute("employeesalary") Paga paga,
			@RequestParam int year, @RequestParam int month)
			throws ParseException {
		ModelAndView model = new ModelAndView("allempsalary");
		List<EmployeeSalaryStanding> standing = employeeSalaryService
				.getStanding(year, month);
		List<EmployeeBank> empBanks2 = employeeBankService.getCurrent();

		Map<String, List<EmployeeBank>> empBanks = new HashMap<String, List<EmployeeBank>>();

		for (EmployeeSalaryStanding employee : standing) {
			empBanks.put(
					employee.getSn(),
					empBanks2
							.stream()
							.filter(s -> s.getEmployee().getSecurityNumber()
									.equalsIgnoreCase(employee.getSn()))
							.collect(Collectors.toList()));

		}

		model.addObject("empBanks", empBanks);
		model.addObject("standing", standing);
		return model;
	}

	@RequestMapping(value = "/searchWorkingsDays", method = RequestMethod.GET)
	public ModelAndView searchWorkingsDays() {
		ModelAndView model = new ModelAndView("workingdaystanding");
		List<Muajt> enums = Arrays.asList(Muajt.values());
		List<Year> years = yearService.getCurrent();
		model.addObject("enums", enums);
		System.out.println(enums.size());
		model.addObject("years", years);
		model.addObject("workingdays", new Paga());
		return model;
	}

	@RequestMapping(value = "/resultWorkingsDays", method = RequestMethod.GET)
	public ModelAndView employeeWorkingDayStanding(
			@ModelAttribute("workingdays") Paga paga, @RequestParam int year,
			@RequestParam int month) throws ParseException {
		ModelAndView model = new ModelAndView("allworkingsday");
		List<EmployeeSalaryStanding> standing = employeeSalaryService
				.getStanding(year, month);
		model.addObject("standing", standing);
		return model;
	}

	@RequestMapping(value = "/searchregister", method = RequestMethod.GET)
	public ModelAndView searchRegister() {
		ModelAndView model = new ModelAndView("searchemployeregister");
		List<Year> years = yearService.getCurrent();
		List<Employee> employees = employeeService.getCurrent();
		model.addObject("years", years);
		model.addObject("employees", employees);
		model.addObject("employeeregister", new Paga());

		return model;

	}

}
