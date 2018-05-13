package com.gardenline.spring.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenline.spring.web.service.BankService;

@Component
public class BankEditor extends PropertyEditorSupport{
	
	 private @Autowired 
	 BankService bankService;

	@Override
	public void setAsText(String text) {
		Bank bank = this.bankService.getBank(Integer.parseInt(text));
		 this.setValue(bank);
	}
	
	
}
