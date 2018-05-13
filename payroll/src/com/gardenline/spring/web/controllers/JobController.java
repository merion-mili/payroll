package com.gardenline.spring.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gardenline.spring.web.dao.Job;
import com.gardenline.spring.web.service.JobService;

@Controller
public class JobController {

	@Autowired
	private JobService jobService;
	
	@RequestMapping(value="/jobs", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView model = new ModelAndView("jobs");
		List<Job> list = jobService.getCurrent();
		model.addObject("jobs", list);
		return model;
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public ModelAndView update(@PathVariable("id") int id){
		ModelAndView model = new ModelAndView("form");
		Job job = jobService.getJob(id);
		model.addObject("jobForm", job);
		return model;
	}

	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView add(){
		ModelAndView model = new ModelAndView("form");
		Job job = new Job();
		model.addObject("jobForm", job);
		return model;
	}
	
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("jobForm") Job job){
		jobService.create(job);
		return new ModelAndView("redirect:/jobs");
	}
	
	
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView save(@PathVariable("id") int id){
		jobService.delete(id);
		return new ModelAndView("redirect:/jobs");
	}
	

}
