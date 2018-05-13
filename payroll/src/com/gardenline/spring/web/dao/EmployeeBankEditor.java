package com.gardenline.spring.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenline.spring.web.service.EmployeeBankService;

@Component
public class EmployeeBankEditor extends PropertyEditorSupport{
	
	@Autowired 
	private EmployeeBankService employeeBankSerivce;
	 

	@Override
	public void setAsText(String text) {
		EmployeeBank employeeBank= this.employeeBankSerivce.getEmployeeBank(Integer.parseInt(text));
		 this.setValue(employeeBank);
	}
	

}
