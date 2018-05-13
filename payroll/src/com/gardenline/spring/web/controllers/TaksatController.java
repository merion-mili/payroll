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

import com.gardenline.spring.web.dao.Taksat;
import com.gardenline.spring.web.service.TaksatService;

@Controller
public class TaksatController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1651553771237012541L;



	@Autowired
	private TaksatService taksatService;

	
	@RequestMapping(value = "/taksat", method = RequestMethod.GET)
	public ModelAndView taksatList() {
		ModelAndView model = new ModelAndView("taksat");
		List<Taksat> taksat = taksatService.getCurrent();
		model.addObject("taksat", taksat);
		return model;
	}

	@RequestMapping("/addtaksat")
	public String addTaksat(Model model) {
		model.addAttribute("taksat", new Taksat());
		return "addtaksat";

	}

	@RequestMapping(value = "/createtaksat", method = RequestMethod.POST)
	public String createTaksat(
			@ModelAttribute("taksat") Taksat taksat,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "addtaksat";
		}

		taksatService.createTaksen(taksat);

		return "redirect:/taksat";
	}

	@RequestMapping(value = "/edittaksat/{id}", method = RequestMethod.GET)
	public String editTaksat(@PathVariable("id") int id, ModelMap model) {

		model.addAttribute("taksat", taksatService.getTaksatById(id));
		

		return "edittaksat";
	}

	@RequestMapping(value = "/edittaksat/{id}", method = RequestMethod.POST)
	public String postTaksat(@ModelAttribute("taksat") Taksat taksat,
			@PathVariable("id") int id, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "edittaksat";
		}

		taksatService.saveOrUpdate(id, taksat);

		return "redirect:/taksat";
	}


	 @RequestMapping("/deletetaksat/{id}")
	    public String deleteTaksat(@PathVariable("id") int id, Model model){
	        taksatService.deleteTaksat(id);
	        return "redirect:/taksat";
	    }
	 

}
