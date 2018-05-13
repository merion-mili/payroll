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

import com.gardenline.spring.web.dao.Year;
import com.gardenline.spring.web.service.YearService;

@Controller
public class YearController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4732026284147829465L;
	@Autowired
	private YearService yearService;
	
	@RequestMapping(value="/years", method=RequestMethod.GET)
	public ModelAndView yearlist(){
		ModelAndView model = new ModelAndView("years");
		List<Year> list = yearService.getCurrent();
		model.addObject("years", list);
		return model;
	}
	
	@RequestMapping(value="/updateYear/{yearId}", method=RequestMethod.GET)
	public ModelAndView updateYear(@PathVariable("yearId") int id){
		ModelAndView model = new ModelAndView("edityear");
		Year year = yearService.getYear(id);
		model.addObject("year", year);
		return model;
	}

	
	@RequestMapping(value="/updateYear/{yearId}" , method=RequestMethod.POST)
	public ModelAndView updateKantierPost(@ModelAttribute("year") 
	Year year, @PathVariable("yearId") int id){
		yearService.saveOrUpdate(id, year);
		return new ModelAndView("redirect:/years");
	}

	
	@RequestMapping(value="/addYear", method=RequestMethod.GET)
	public ModelAndView addYear(){
		ModelAndView model = new ModelAndView("year");
		Year year = new Year();
		model.addObject("yearForm", year);
		return model;
	}
	
	
	@RequestMapping(value="/saveYear", method=RequestMethod.POST)
	public ModelAndView saveYear(@ModelAttribute("yearForm") Year year){
		yearService.create(year);
		return new ModelAndView("redirect:/years");
	}
	
	
	
/*	@RequestMapping(value="/updateYear/{yearId}", method=RequestMethod.GET)
	public ModelAndView updateBank(@PathVariable("id") int id){
		ModelAndView model = new ModelAndView("edityear");
		Year year = yearService.getYear(id);
		model.addObject("year", year);
		return model;
	}

	
	

	@RequestMapping(value="/updateYear/{id}" , method=RequestMethod.POST)
	public ModelAndView updateKantierPost(@ModelAttribute("year") 
	Year year, @PathVariable("id") int id){
		yearService.saveOrUpdate(id, year);
		return new ModelAndView("redirect:/years");
	}
	*/
	
	@RequestMapping(value="/deleteYear/{id}", method=RequestMethod.GET)
	public ModelAndView save(@PathVariable("id") int id){
		yearService.delete(id);
		return new ModelAndView("redirect:/years");
	}
	

	
	
}
