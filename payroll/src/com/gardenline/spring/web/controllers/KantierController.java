package com.gardenline.spring.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.Kantier;
import com.gardenline.spring.web.service.KantierService;

@Controller
public class KantierController {

	@Autowired
	private KantierService kantierService;
	
	@RequestMapping(value="/kantiere", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView model = new ModelAndView("kantiere");
		List<Kantier> kantiere = kantierService.getCurrent();
		model.addObject("kantiere", kantiere);
		return model;
	}
	
	@RequestMapping(value="/updatekantier/{id}", method=RequestMethod.GET)
	public ModelAndView updateKantier(@PathVariable("id") int id){
		ModelAndView model = new ModelAndView("kantierform");
		Kantier kantier = kantierService.getKaniter(id);
		model.addObject("kantier", kantier);
		return model;
	}

	
	@RequestMapping(value="/addkantier", method=RequestMethod.GET)
	public ModelAndView addkantier(){
		ModelAndView model = new ModelAndView("addkantierform");
		Kantier kantier = new Kantier();
		model.addObject("kantier", kantier);
		return model;
	}
	
	
	@RequestMapping(value="/addkantier", method=RequestMethod.POST)
	public ModelAndView addKantierPost(@ModelAttribute("kantier") Kantier kantier){
		kantierService.createKantier(kantier);
		return new ModelAndView("redirect:/kantiere");
	}
	
	
	@RequestMapping(value="/updatekantier/{id}" , method=RequestMethod.POST)
	public ModelAndView saveKantier(@ModelAttribute("kantier") Kantier kantier, @PathVariable("id") int id){
		kantierService.saveOrUpdate(id, kantier);
		return new ModelAndView("redirect:/kantiere");
	}
	
	
	
	@RequestMapping(value="/deletekantier/{id}", method=RequestMethod.GET)
	public ModelAndView deleteKantier(@PathVariable("id") int id){
		kantierService.deleteKantier(id);
		return new ModelAndView("redirect:/kantiere");
	}
	
}
