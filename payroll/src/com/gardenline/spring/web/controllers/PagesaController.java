package com.gardenline.spring.web.controllers;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.AktivPasiv;
import com.gardenline.spring.web.dao.AktivPasivEditor;
import com.gardenline.spring.web.dao.EmpBankCollectionEditor;
import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeBank;
import com.gardenline.spring.web.dao.EmployeeBankEditor;
import com.gardenline.spring.web.dao.EmployeeSalaryStanding;
import com.gardenline.spring.web.dao.Pagesat;
import com.gardenline.spring.web.service.AktivPasivService;
import com.gardenline.spring.web.service.BankService;
import com.gardenline.spring.web.service.EmployeeBankService;
import com.gardenline.spring.web.service.EmployeeSalaryService;
import com.gardenline.spring.web.service.EmployeeService;
import com.gardenline.spring.web.service.PagesaService;

@Controller
public class PagesaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeBankService employeeBankService;

	@Autowired
	private EmployeeBankEditor employeeBankEditor;

	@Autowired
	private BankService bankService;

	@Autowired
	private AktivPasivService aktivPasivService;

	@Autowired
	private EmployeeSalaryService employeeSalaryService;

	@Autowired
	private PagesaService pagesaService;

	@Autowired
	private AktivPasivEditor aktivPasivEditor;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));

		binder.registerCustomEditor(AktivPasiv.class, this.aktivPasivEditor);
		binder.registerCustomEditor(EmployeeBank.class, this.employeeBankEditor);
		binder.registerCustomEditor(ArrayList.class, "empBanks",
				new EmpBankCollectionEditor(ArrayList.class, bankService));
		

	}

	@RequestMapping(value = "/pagesat", method = RequestMethod.GET)
	public ModelAndView allPagesat() {

		ModelAndView model = new ModelAndView("pagesat");
		List<Pagesat> pagesat = pagesaService.getCurrent();
		List<Employee> list = employeeService.getCurrent();
		List<AktivPasiv> aktivpasiv = aktivPasivService.getCurrent();

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

		model.addObject("aktivpasiv", aktivpasiv);
		model.addObject("empBanks", empBanks);
		model.addObject("pagesat", pagesat);
		return model;

	}

	@RequestMapping("/addpagesen")
	public String addTimePerday(Model model, HttpServletRequest request) {
		model.addAttribute("pagesen", new Pagesat());
		List<AktivPasiv> aktivpasiv = aktivPasivService.getCurrent();
		model.addAttribute("aktivpasiv", aktivpasiv);
		return "addpagesen";

	}

	@RequestMapping(value = "/createpagesen", method = RequestMethod.POST)
	public String createPagesen(@ModelAttribute("pagesen") Pagesat pagesat,
			BindingResult result, ModelMap model) throws ParseException {

		if (result.hasErrors()) {
			return "addpagesen";
		}

		double gjendjeCash = pagesat.getAktivPasiv().getGjendjeCash();
		double gjendjeBank = pagesat.getAktivPasiv().getGjendjeBank();
		Employee employee = pagesat.getAktivPasiv().getEmployee();

		Date date = pagesat.getDate();

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		pagesat.setMonth(month);
		pagesat.setYear(year);
		List<EmployeeSalaryStanding> standing = employeeSalaryService
				.getStanding(year, month - 1);
		EmployeeSalaryStanding employeeSalaryStandingPerEmployee = getEmployeeSalaryStandingPerEmployee(
				employee, standing);
		double cashAmount = 0;
		double bankAmount = 0;

		if (employeeSalaryStandingPerEmployee == null) {
			cashAmount = 0;
			bankAmount = 0;
		} else {

			cashAmount = employeeSalaryStandingPerEmployee.getCashAmount();

			bankAmount = employeeSalaryStandingPerEmployee.getPaganeto();
		}

		double paradhenie = pagesat.getParadhenie();
		double pagesaCash = pagesat.getPagesaCash();
		double pagesaBank = pagesat.getPagesaBank();
		pagesat.setCashAmount(cashAmount);
		pagesat.setBankAmount(bankAmount);

		double totalCash = cashAmount - pagesaCash - paradhenie + gjendjeCash;
		double totalBank = bankAmount - pagesaBank + gjendjeBank;

		pagesat.setTotalBank(totalBank);
		pagesat.setTotalCash(totalCash);

		pagesat.getAktivPasiv().setGjendjeCash(totalCash);
		pagesat.getAktivPasiv().setGjendjeBank(totalBank);
		pagesaService.createPagesen(pagesat);

		return "redirect:/pagesat";
	}

	private EmployeeSalaryStanding getEmployeeSalaryStandingPerEmployee(
			Employee employee, List<EmployeeSalaryStanding> standing) {
		for (EmployeeSalaryStanding employeeSalaryStanding : standing) {
			if (employeeSalaryStanding.getSn().equalsIgnoreCase(
					employee.getSecurityNumber())) {
				return employeeSalaryStanding;
			}
		}
		return null;
	}

	@RequestMapping(value = "/editpagesen/{id}", method = RequestMethod.GET)
	public String editPagesen(@PathVariable("id") int id, ModelMap model) {

		AktivPasiv aktivPasiv = pagesaService.getAktivPasivPagesat(id);
		List<EmployeeBank> empBanks = employeeBankService
				.getEmployeeBanks(aktivPasiv.getEmployee().getEmpId());
		model.addAttribute("empBanks", empBanks);
		model.addAttribute("pagesen", pagesaService.getPagesen(id));
		model.addAttribute("aktivpasiv", aktivPasiv);

		return "editpagesen";

	}

	@RequestMapping(value = "/editpagesen/{id}", method = RequestMethod.POST)
	public String postEditPagesen(@ModelAttribute("pagesen") Pagesat pagesat,
			@PathVariable("id") int id, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "editpagesen";
		}

		pagesaService.saveOrUpdate(id, pagesat);

		return "redirect:/pagesat";
	}

	@RequestMapping("/deletepagesen/{id}")
	public String deletePagesen(@PathVariable("id") int id, Model model) {
		pagesaService.deletePagesen(id);
		return "redirect:/pagesat";
	}

	@RequestMapping("/getEmployeeBanksAktiv/{id}")
	@ResponseBody
	public List<EmployeeBank> getEmployeeBanksAktiv(@PathVariable("id") int id) {
		Employee employeeForAktivPasiv = aktivPasivService
				.getEmployeeForAktivPasiv(id);

		return employeeBankService.getEmployeeBanks(employeeForAktivPasiv
				.getEmpId());

	}
}
