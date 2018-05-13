package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeSalary;
import com.gardenline.spring.web.dao.EmployeeSalaryDao;

@Service("salaryService")
public class SalaryService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8299003062838534331L;
	@Autowired
	private EmployeeSalaryDao employeeSalaryDao;

	public List<EmployeeSalary> getCurrent() {
		return employeeSalaryDao.getAllSalaryEmp();
	}

	public void createEmpSalary(EmployeeSalary employeeSalary) {
		employeeSalaryDao.saveOrUpdate(employeeSalary);

	}

	public EmployeeSalary getEmployeeSalary(int id) {

		return employeeSalaryDao.getEmployeeSalary(id);
	}

	public void deleteEmployeeSalary(int id) {
		employeeSalaryDao.deleteEntry(id);
	}

	public void updateEmployeeSalary(int id, EmployeeSalary employeeSalary) {
		employeeSalaryDao.updateEmployeeSalary(id, employeeSalary);

	}

	public List<EmployeeSalary> getEmployeeSalaries(int empId) {
		return employeeSalaryDao.getEmployeesSalaries(empId);
	}

	public EmployeeSalary getEmployeSalary(int id) {
		return employeeSalaryDao.getEmployeeSalary(id);

	}

	public List<Employee> getEmployeeForId(int id) {
		return employeeSalaryDao.getEmployeeForId(id);
	}

	public void process(List<String> filepath) throws ParseException {
		employeeSalaryDao.process(filepath);

	}

}
