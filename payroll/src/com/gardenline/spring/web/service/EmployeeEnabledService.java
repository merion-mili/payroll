package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeEnabled;
import com.gardenline.spring.web.dao.EmployeeEnabledDao;

@Service("employeeEnabledService")
public class EmployeeEnabledService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8299003062838534331L;
	@Autowired
	private EmployeeEnabledDao employeeEnabledDao;

	public List<EmployeeEnabled> getCurrent() {
		return employeeEnabledDao.getAllEmpEnabled();
	}

	public void createEmpEnabled(EmployeeEnabled employeeEnabled) {
		employeeEnabledDao.saveOrUpdate(employeeEnabled);

	}

	public EmployeeEnabled getEmployeeEnabled(int id) {

		return employeeEnabledDao.getEmployeeEnabled(id);
	}

	public void deleteEmployeeEnabled(int id) {
		employeeEnabledDao.deleteEntry(id);
	}

	public void updateEmployeeEnabled(int id, EmployeeEnabled employeeEnabled) {
		employeeEnabledDao.updateEmployeeEnabled(id, employeeEnabled);

	}

	public List<EmployeeEnabled> getEmployeesEnabled(int empId) {
		return employeeEnabledDao.getEmployeesEnabled(empId);
	}

	public List<Employee> getEmployeeForId(int id) {
		return employeeEnabledDao.getEmployeeForId(id);
	}

	public void process(List<String> filepath) throws ParseException {
		employeeEnabledDao.process(filepath);

	}

}
