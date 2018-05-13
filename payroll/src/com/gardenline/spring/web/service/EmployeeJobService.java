package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.EmployeeJob;
import com.gardenline.spring.web.dao.EmployeeJobDao;
import com.gardenline.spring.web.dao.Job;

@Service("employeeJobService")
public class EmployeeJobService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8299003062838534331L;
	@Autowired
	private EmployeeJobDao employeeJobDao;

	public List<EmployeeJob> getCurrent() {
		return employeeJobDao.getAllJobEmp();
	}

	public void createJobEmp(EmployeeJob employeeJob) {
		employeeJobDao.saveOrUpdate(employeeJob);

	}

	public EmployeeJob getEmployeeJob(int id) {

		return employeeJobDao.getEmployeeJob(id);
	}

	public void deleteEmployeeJob(int id) {
		employeeJobDao.deleteEntry(id);
	}

	public void updateEmployeeJob(int id, EmployeeJob employeeJob) {
		employeeJobDao.updateEmployeeJob(id, employeeJob);

	}

	public List<EmployeeJob> getEmployeeJobs(int empId) {
		return employeeJobDao.getEmployeesJobs(empId);
	}

	public EmployeeJob getEmployeJob(int id) {
		return employeeJobDao.getEmployeeJob(id);

	}

	public List<Job> getJobsForEmployee(int empId) {
		return employeeJobDao.getJobsForEmployee(empId);
	}

	public List<Employee> getEmployeeForId(int id) {
		return employeeJobDao.getEmployeeForId(id);
	}

	public List<Job> getJobForEmployeeId(int id) {
		return employeeJobDao.getJobsForEmployee(id);
	}

	public void process(List<String> filepath) throws ParseException {
		employeeJobDao.process(filepath);

	}

}
