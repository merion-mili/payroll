package com.gardenline.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Bank;
import com.gardenline.spring.web.dao.BankDao;

@Service("bankService")
public class BankService {
	@Autowired
	private BankDao bankDao;


	public List<Bank> getCurrent() {
		return bankDao.getAllBank();
	}

	
	public void createBank(Bank bank) {
		bankDao.saveOrUpdate(bank);
		
	}

	public Bank getBank(int id) {

		return bankDao.getBank(id);
	}

	public void saveOrUpdate(int id, Bank bank) {
		bankDao.updateBank(id, bank);

	}

	public void deleteBank(int id) {
		bankDao.delete(id);
	}


	


	

}
