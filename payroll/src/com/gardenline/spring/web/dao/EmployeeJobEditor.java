package com.gardenline.spring.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenline.spring.web.service.EmployeeJobService;

@Component
public class EmployeeJobEditor extends PropertyEditorSupport{
	
	@Autowired 
	private EmployeeJobService employeeJobService;
	 

	@Override
	public void setAsText(String text) {
		EmployeeJob employeeJob= this.employeeJobService.getEmployeJob(Integer.parseInt(text));
		 this.setValue(employeeJob);
	}
	

}
