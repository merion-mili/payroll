package com.gardenline.spring.web.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.Bank;
import com.gardenline.spring.web.service.BankService;
import com.gardenline.spring.web.service.EmployeeService;

@Controller
public class BankController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2672811950014133215L;

	@Autowired
	private BankService bankService;

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/banks", method = RequestMethod.GET)
	public ModelAndView banklist() {

		ModelAndView model = new ModelAndView("banks");
		List<Bank> list = bankService.getCurrent();
		model.addObject("banks", list);
		return model;
	}

	@RequestMapping(value = "/updateBank/{id}", method = RequestMethod.GET)
	public ModelAndView updateBank(@PathVariable("id") int id) {
		ModelAndView model = new ModelAndView("editbank");
		Bank bank = bankService.getBank(id);
		model.addObject("bank", bank);
		return model;
	}

	@RequestMapping(value = "/addBank", method = RequestMethod.GET)
	public ModelAndView addBank() {
		ModelAndView model = new ModelAndView("addbank");
		Bank bank = new Bank();
		model.addObject("bank", bank);
		return model;
	}

	@RequestMapping(value = "/addBank", method = RequestMethod.POST)
	public ModelAndView addBankPost(@ModelAttribute("bank") Bank bank) {
		bankService.createBank(bank);
		return new ModelAndView("redirect:/banks");
	}

	@RequestMapping(value = "/updateBank/{id}", method = RequestMethod.POST)
	public ModelAndView updateKantierPost(@ModelAttribute("bank") Bank bank,
			@PathVariable("id") int id) {
		bankService.saveOrUpdate(id, bank);
		return new ModelAndView("redirect:/banks");
	}

	@RequestMapping(value = "/deleteBank/{id}", method = RequestMethod.GET)
	public ModelAndView deleteBank(@PathVariable("id") int id) {
		bankService.deleteBank(id);
		return new ModelAndView("redirect:/banks");
	}

}
