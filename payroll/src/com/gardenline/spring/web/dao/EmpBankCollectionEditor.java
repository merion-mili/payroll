package com.gardenline.spring.web.dao;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import com.gardenline.spring.web.service.BankService;

public class EmpBankCollectionEditor extends CustomCollectionEditor {
	
	@Autowired
	private BankService bankService;

	public EmpBankCollectionEditor(Class<? extends Collection> collectionType,
			BankService bankService) {
		super(collectionType);
		this.bankService = bankService;
		
	}
	
	@Override
    protected Object convertElement(Object element) {
		Integer bankId = Integer.parseInt(element.toString());

        Bank bank = bankService.getBank(bankId);
        EmployeeBank empBank = new EmployeeBank();

       
        Employee employee = new Employee();

        empBank.setEmployee(employee);
        empBank.setBank(bank);
        return empBank;
    }

}
