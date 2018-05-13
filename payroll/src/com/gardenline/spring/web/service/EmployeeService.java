package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.EmpDao;
import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeBank;
import com.gardenline.spring.web.dao.EmployeeJob;

@Service("employeeService")
public class EmployeeService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4788819096489654343L;
	@Autowired
	private EmpDao empDao;

	public List<Employee> getCurrent() {
		return empDao.getEmployees();
	}

	public void create(Employee employee) {
		empDao.saveOrUpdate(employee);

	}

	public Employee getEmployee(int id) {

		return empDao.getEmployee(id);
	}

	public List<Employee> getEmployees(int id) {

		return empDao.getEmployees(id);
	}

	public boolean exists(String securityNumber) {
		return empDao.exists(securityNumber);

	}

	public void update(Employee employee) {

		empDao.update(employee);
	}

	public List<Employee> getEnabledEmployees(Date startDate, boolean enabled) {
		return empDao.getEnabledEmployees(startDate, enabled);
	}



	public void updateEmployee(int empId, Employee employee) {
		empDao.updateEmployee(empId, employee);

	}

	public void deleteEmployee(int id) {
		empDao.deleteEmployee(id);
	}

	public void deleteEmployeeJob(EmployeeJob employeeJob) {
		empDao.deleteEmployeeJob(employeeJob);

	}

	public void deleteEmployeeBank(EmployeeBank employeeBank) {
		empDao.deleteEmployeeBank(employeeBank);
	

	}

	public void process(List<String> paths) throws ParseException{
		empDao.process(paths);
		
	}

}
