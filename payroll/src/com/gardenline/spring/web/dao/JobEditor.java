package com.gardenline.spring.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenline.spring.web.service.JobService;

@Component
public class JobEditor extends PropertyEditorSupport{
	
	 private @Autowired 
	 JobService jobService;

	@Override
	public void setAsText(String text) {
		Job job = this.jobService.getJob(Integer.parseInt(text));
		 this.setValue(job);
	}
	
	
}
