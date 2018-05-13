package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.EmployeeBank;
import com.gardenline.spring.web.dao.EmployeeBankDao;

@Service("employeeBankService")
public class EmployeeBankService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8299003062838534331L;
	@Autowired
	private EmployeeBankDao employeeBankDao;

	
	public List<EmployeeBank> getCurrent() {
		return employeeBankDao.getAllBankEmp();
	}

	public void createBankEmp(EmployeeBank employeeBank) {
		employeeBankDao.saveOrUpdate(employeeBank);

	}

	public EmployeeBank getEmployeeBank(int id) {

		return employeeBankDao.getEmployeeBank(id);
	}

	
	public List<EmployeeBank> getEmployeeBanks(int empId) {
		return employeeBankDao.getEmployeesBanks(empId);
	}
	
	
	public void deleteBankEmployee(int id) {
		employeeBankDao.deleteEntry(id);
	}
	
	public void updateEmployeeBank(int id, EmployeeBank employeeBank) {
		employeeBankDao.updateEmployeeBank(id, employeeBank);
		
	}

	public void process(List<String> filepath) throws ParseException{
		employeeBankDao.process(filepath);
		
	
	}
	

	
}
