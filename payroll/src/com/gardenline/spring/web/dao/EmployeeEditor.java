package com.gardenline.spring.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenline.spring.web.service.EmployeeService;

@Component
public class EmployeeEditor extends PropertyEditorSupport{
	
	 private @Autowired 
	 EmployeeService employeeService;

	@Override
	public void setAsText(String text) {
		Employee emp = this.employeeService.getEmployee(Integer.parseInt(text));
		 this.setValue(emp);
	}
	
	
}
