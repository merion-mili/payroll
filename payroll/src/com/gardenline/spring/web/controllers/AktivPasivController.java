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

import com.gardenline.spring.web.dao.AktivPasiv;
import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeEditor;
import com.gardenline.spring.web.service.AktivPasivService;
import com.gardenline.spring.web.service.EmployeeService;

@Controller
public class AktivPasivController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AktivPasivService aktivPasivService;

	@Autowired
	private EmployeeEditor employeeEditor;



	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Employee.class, this.employeeEditor);
	

	}

	@RequestMapping(value = "/aktivpasivet", method = RequestMethod.GET)
	public ModelAndView aktivPasivList() {
		ModelAndView model = new ModelAndView("aktivpasivet");
		List<AktivPasiv> aktivpasivet = aktivPasivService.getCurrent();
		model.addObject("aktivpasivet", aktivpasivet);
		return model;
	}

	@RequestMapping("/addaktivpasiv")
	public String addAktivPasiv(Model model) {
		model.addAttribute("aktivpasiv", new AktivPasiv());
		List<Employee> employees = employeeService.getCurrent();
		model.addAttribute("employees", employees);
		return "addaktivpasiv";

	}

	@RequestMapping(value = "/createaktivpasiv", method = RequestMethod.POST)
	public String createAktivpasiv(
			@ModelAttribute("aktivpasiv") AktivPasiv aktivpasiv,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addaktivpasiv";
		}

		aktivPasivService.createAktivPasiv(aktivpasiv);

		return "redirect:/aktivpasivet";
	}

	@RequestMapping(value = "/editaktivpasiv/{id}", method = RequestMethod.GET)
	public String editAktivPasiv(@PathVariable("id") int id, ModelMap model) {

		model.addAttribute("employees", employeeService.getCurrent());
		model.addAttribute("aktivpasiv", aktivPasivService.getAktivPasiv(id));
		model.addAttribute("employee", aktivPasivService.getEmployeeForAktivPasiv(id));

		return "editaktivpasiv";
	}

	@RequestMapping(value = "/editaktivpasiv/{id}", method = RequestMethod.POST)
	public String postEditAktivPasiv(@ModelAttribute("aktivpasiv") AktivPasiv aktivpasiv,
			@PathVariable("id") int id, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "editaktivpasiv";
		}

		aktivPasivService.saveOrUpdate(id, aktivpasiv);

		return "redirect:/aktivpasivet";
	}


	 @RequestMapping("/deleteaktivpasiv/{id}")
	    public String deleteAktivPasiv(@PathVariable("id") int id, Model model){
	        aktivPasivService.deleteAktivPasiv(id);;
	        return "redirect:/aktivpasivet";
	    }
	 

}
