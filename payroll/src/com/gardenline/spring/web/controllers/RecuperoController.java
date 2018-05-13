package com.gardenline.spring.web.controllers;

import java.io.Serializable;
import java.util.ArrayList;
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

import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeEditor;
import com.gardenline.spring.web.dao.Recupero;
import com.gardenline.spring.web.service.EmployeeService;
import com.gardenline.spring.web.service.RecuperoService;

@Controller
public class RecuperoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RecuperoService recuperoService;

	@Autowired
	private EmployeeEditor employeeEditor;



	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		binder.registerCustomEditor(Employee.class, this.employeeEditor);
	

	}

	@RequestMapping(value = "/recuperot", method = RequestMethod.GET)
	public ModelAndView recuperoList() {
		ModelAndView model = new ModelAndView("recuperot");
		List<Recupero> recuperot = recuperoService.getCurrent();
		model.addObject("recuperot", recuperot);
		return model;
	}

	@RequestMapping("/addrecupero")
	public String addRecupero(Model model) {
		model.addAttribute("recupero", new Recupero());
		List<Employee> employees = employeeService.getCurrent();
		model.addAttribute("employees", employees);
		return "addrecupero";

	}

	@RequestMapping(value = "/createrecupero", method = RequestMethod.POST)
	public String createrecupero(
			@ModelAttribute("recupero") Recupero recupero,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addrecupero";
		}

		recuperoService.createRecupero(recupero);

		return "redirect:/recuperot";
	}

	@RequestMapping(value = "/editrecupero/{id}", method = RequestMethod.GET)
	public String editRecupero(@PathVariable("id") int id, ModelMap model) {
		List<Employee> employees = new ArrayList<Employee>();
		Employee employeeForRecupero = recuperoService.getEmployeeForRecupero(id);
		employees.add(employeeForRecupero);
		model.addAttribute("employees", employees);
		model.addAttribute("recupero", recuperoService.getRecupero(id));
		//model.addAttribute("employee", recuperoService.getEmployeeForRecupero(id));

		return "editrecupero";
	}

	@RequestMapping(value = "/editrecupero/{id}", method = RequestMethod.POST)
	public String postEditRecupero(@ModelAttribute("recupero") Recupero recupero,
			@PathVariable("id") int id, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "editrecuepro";
		}

		recuperoService.saveOrUpdate(id, recupero);

		return "redirect:/recuperot";
	}


	 @RequestMapping("/deleterecupero/{id}")
	    public String deleteRecupero(@PathVariable("id") int id, Model model){
	        recuperoService.deleteRecupero(id);
	        return "redirect:/recuperot";
	    }
	 

}
