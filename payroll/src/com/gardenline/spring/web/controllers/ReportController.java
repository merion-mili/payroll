package com.gardenline.spring.web.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.AktivPasiv;
import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeBank;
import com.gardenline.spring.web.dao.EmployeeEnabled;
import com.gardenline.spring.web.dao.EmployeeJob;
import com.gardenline.spring.web.dao.EmployeeSalary;
import com.gardenline.spring.web.dao.EmployeeSalaryStanding;
import com.gardenline.spring.web.dao.Muajt;
import com.gardenline.spring.web.dao.Paga;
import com.gardenline.spring.web.dao.Recupero;
import com.gardenline.spring.web.dao.RecuperoEditor;
import com.gardenline.spring.web.dao.Year;
import com.gardenline.spring.web.dao.YearEditor;
import com.gardenline.spring.web.service.AktivPasivService;
import com.gardenline.spring.web.service.EmployeeBankService;
import com.gardenline.spring.web.service.EmployeeEnabledService;
import com.gardenline.spring.web.service.EmployeeJobService;
import com.gardenline.spring.web.service.EmployeeRegister;
import com.gardenline.spring.web.service.EmployeeSalaryService;
import com.gardenline.spring.web.service.EmployeeService;
import com.gardenline.spring.web.service.PagaService;
import com.gardenline.spring.web.service.RecuperoService;
import com.gardenline.spring.web.service.SalaryService;
import com.gardenline.spring.web.service.YearService;
import com.gardenline.spring.web.utils.ExcelAktivPasivReport;
import com.gardenline.spring.web.utils.ExcelEmployeeBankReport;
import com.gardenline.spring.web.utils.ExcelEmployeeEnabledReport;
import com.gardenline.spring.web.utils.ExcelEmployeeJobReport;
import com.gardenline.spring.web.utils.ExcelEmployeeReport;
import com.gardenline.spring.web.utils.ExcelEmployeeSalariesReport;
import com.gardenline.spring.web.utils.ExcelMonthSalaryReport;
import com.gardenline.spring.web.utils.ExcelPagaReport;
import com.gardenline.spring.web.utils.ExcelRecuperotReport;
import com.gardenline.spring.web.utils.ExcelRegisterReport;
import com.gardenline.spring.web.utils.ExcelWorkingDaysReport;

@Controller
public class ReportController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2979935135190732438L;

	@Autowired
	private EmployeeJobService employeeJobService;
	
	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private EmployeeEnabledService employeeEnabledService;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RecuperoService recuperoService;
	

	@Autowired
	private AktivPasivService aktivPasivService;

	@Autowired
	private EmployeeBankService employeeBankService;

	@Autowired
	private EmployeeSalaryService employeeSalaryService;

	@Autowired
	private YearService yearService;
	

	@Autowired
	private RecuperoEditor recuperoEditor;

	@Autowired
	private PagaService pagaService;
	@Autowired
	private YearEditor yearEditor;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		
		binder.registerCustomEditor(Year.class, this.yearEditor);

		binder.registerCustomEditor(Recupero.class, this.recuperoEditor);
	}


	@RequestMapping(value = "/reportEmployee", method = RequestMethod.GET)
	public ModelAndView getEmployee() {
		List<Employee> employees = employeeService.getCurrent();
		return new ModelAndView(new ExcelEmployeeReport(), "emps", employees);
	}

	@RequestMapping(value = "/reportEmpJob", method = RequestMethod.GET)
	public ModelAndView getEmployeeJobExcel() {
		List<EmployeeJob> empJobs = employeeJobService.getCurrent();
		return new ModelAndView(new ExcelEmployeeJobReport(), "empJobs",
				empJobs);
	}
	
	@RequestMapping(value = "/reportEmpSalaries", method = RequestMethod.GET)
	public ModelAndView getEmployeeSalariesExcel() {
		List<EmployeeSalary> empSalaries = salaryService.getCurrent();
		return new ModelAndView(new ExcelEmployeeSalariesReport(), "empSalaries",
				empSalaries);
	}
	
	@RequestMapping(value = "/reportEmpEnabled", method = RequestMethod.GET)
	public ModelAndView getEmployeeEnabledExcel() {
		List<EmployeeEnabled> empEnabled = employeeEnabledService.getCurrent();
		return new ModelAndView(new ExcelEmployeeEnabledReport(), "empEnabled",
				empEnabled);
	}
	
	@RequestMapping(value = "/reportEmpBank", method = RequestMethod.GET)
	public ModelAndView getEmployeeBankExcel() {
		List<EmployeeBank> empBanks = employeeBankService.getCurrent();
		return new ModelAndView(new ExcelEmployeeBankReport(), "empBanks",
				empBanks);
	}

	@RequestMapping(value = "/reportOfPresence", method = RequestMethod.GET)
	public ModelAndView getPresenceExcel() {
		List<Paga> pagat = pagaService.getCurrent();
		return new ModelAndView(new ExcelPagaReport(), "pagat", pagat);
	}

	@RequestMapping(value = "/searchWorkingsDaysForExcel", method = RequestMethod.GET)
	public ModelAndView searchWorkingsDaysForExcel() {
		ModelAndView model = new ModelAndView("generateexcelworkdays");
		List<Muajt> enums = Arrays.asList(Muajt.values());
		List<Year> years = yearService.getCurrent();
		model.addObject("enums", enums);
		System.out.println(enums.size());
		model.addObject("years", years);
		model.addObject("workingdays", new Paga());
		return model;
	}

	@RequestMapping(value = "/excelWorkingsDayReport", method = RequestMethod.GET)
	public ModelAndView excelWorkingsDayReport(@ModelAttribute("workingdays") Paga paga, @RequestParam int year,
			@RequestParam int month) throws ParseException {
		List<EmployeeSalaryStanding> standing = employeeSalaryService
				.getStanding(year, month);
		return new ModelAndView(new ExcelWorkingDaysReport(), "standing",
				standing);

	}

	@RequestMapping(value = "/searchpresenceperEmployee", method = RequestMethod.GET)
	public ModelAndView searchpresenceperEmployee() {
		ModelAndView model = new ModelAndView("generateprecenceemployee");
		List<Recupero> recuperot = recuperoService.getCurrent();
		List<Muajt> enums = Arrays.asList(Muajt.values());
		List<Year> years = yearService.getCurrent();
		model.addObject("recuperot",recuperot);
		model.addObject("enums",enums);
		model.addObject("years", years);
		model.addObject("paga", new Paga());
		return model;
	}

	@RequestMapping(value = "/excelemployeePresenceReport", method = RequestMethod.GET)
	public ModelAndView excelemployeePresenceReport(@ModelAttribute("pagat") Paga paga, @RequestParam int year,
			@RequestParam int month) throws ParseException {
		List<Paga> pagaMontYearPerEmployee = pagaService.getPagaMontYearPerEmployee(year, month, paga.getRecuperoEmployee());
		
		return new ModelAndView(new ExcelPagaReport(), "pagat",pagaMontYearPerEmployee);

	}

	
	@RequestMapping(value = "/searchMonthSalaryExcel", method = RequestMethod.GET)
	public ModelAndView searchPagaExcel() {
		ModelAndView model = new ModelAndView("excelsalarystanding");
		List<Muajt> enums = Arrays.asList(Muajt.values());
		List<Year> years = yearService.getCurrent();
		model.addObject("enums", enums);
		System.out.println(enums.size());
		model.addObject("years", years);
		model.addObject("employeesalary", new Paga());
		return model;
	}

	@RequestMapping(value = "/resultMonthSalaryExcel", method = RequestMethod.GET)
	public ModelAndView resultPagaExcel(
			@ModelAttribute("employeesalary") Paga paga,
			@RequestParam int year, @RequestParam int month)
			throws ParseException {
		ModelAndView model = new ModelAndView(new ExcelMonthSalaryReport());
		List<EmployeeBank> empbanks = employeeBankService.getCurrent();
		List<EmployeeSalaryStanding> standing = employeeSalaryService
				.getStanding(year, month);

		model.addObject("standing", standing);
		model.addObject("empbanks", empbanks);
		return model;

		/*
		 * return new ModelAndView(new ExcelMonthSalaryReport(),"standing",
		 * standing);
		 */
	}
	
	@RequestMapping(value = "/reportAktivPasiv", method = RequestMethod.GET)
	public ModelAndView getAktivPasiv() {
		List<AktivPasiv> aktivpasivet = aktivPasivService.getCurrent();
		return new ModelAndView(new ExcelAktivPasivReport(), "aktivpasivet", aktivpasivet);
	}
	
	
	
	@RequestMapping(value = "/reportRecuperot", method = RequestMethod.GET)
	public ModelAndView getExcelRecuperot() {
		List<Recupero> recuperot = recuperoService.getCurrent();
		return new ModelAndView(new ExcelRecuperotReport(), "recuperot", recuperot);
	}
	
	
	@RequestMapping(value = "/searchemployeeregister", method = RequestMethod.GET)
	public ModelAndView searchEmployeeRegister() {
		ModelAndView model = new ModelAndView("excelemployeeregister");
		List<Recupero> recuperot = recuperoService.getCurrent();
		List<Year> years = yearService.getCurrent();
		List<Muajt> enums = Arrays.asList(Muajt.values());
		model.addObject("years", years);
		model.addObject("enums", enums);
		model.addObject("recuperot", recuperot);
		model.addObject("employeeregister", new Paga());
		return model;
	}
	
	@RequestMapping(value = "/reportemployeeregister", method = RequestMethod.GET)
	public ModelAndView rezultEmployeeRegister(	@ModelAttribute("employeeregister") Paga paga, @RequestParam int year) throws ParseException {
			
		EmployeeRegister employeeRegister = employeeSalaryService.getEmployeeRegister(year, paga.getRecuperoEmployee());
	
		return new ModelAndView(new ExcelRegisterReport(), "standingPerEmployee", employeeRegister);
	}


}
