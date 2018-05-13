package com.gardenline.spring.web.controllers;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.Siguracionet;
import com.gardenline.spring.web.service.SiguracionetService;

@Controller
public class SiguracioniController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;



	@Autowired
	private SiguracionetService siguracioniService;

	
	@RequestMapping(value = "/sigurimet", method = RequestMethod.GET)
	public ModelAndView sigurimetList() {
		ModelAndView model = new ModelAndView("sigurimet");
		List<Siguracionet> siguracionet = siguracioniService.getCurrent();
		model.addObject("siguracionet", siguracionet);
		return model;
	}

	@RequestMapping("/addsigurimet")
	public String addSigurimet(Model model) {
		model.addAttribute("siguracionet", new Siguracionet());
		return "addsigurimet";

	}

	@RequestMapping(value = "/createsigurimet", method = RequestMethod.POST)
	public String createSigurimet(
			@ModelAttribute("siguracionet") Siguracionet siguracionet,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addsigurimet";
		}

		siguracioniService.createSiguracionin(siguracionet);;

		return "redirect:/sigurimet";
	}

	@RequestMapping(value = "/editsigurimet/{id}", method = RequestMethod.GET)
	public String editSigurimet(@PathVariable("id") int id, ModelMap model) {

		model.addAttribute("siguracionet", siguracioniService.getSiguracioninById(id));
		

		return "editsigurimet";
	}

	@RequestMapping(value = "/editsigurimet/{id}", method = RequestMethod.POST)
	public String postSigurimet(@ModelAttribute("siguracionet") Siguracionet siguracionet,
			@PathVariable("id") int id, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "editsigurimet";
		}

		siguracioniService.saveOrUpdate(id, siguracionet);

		return "redirect:/sigurimet";
	}


	 @RequestMapping("/deletesigurimet/{id}")
	    public String deleteSigurimet(@PathVariable("id") int id, Model model){
	        siguracioniService.deleteSiguracionin(id);
	        return "redirect:/sigurimet";
	    }
	 

}
