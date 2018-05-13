package com.gardenline.spring.web.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gardenline.spring.web.utils.CommonUtils;

@Repository
@Transactional
@Component("employeeJobDao")
public class EmployeeJobDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 65187939886788704L;
	/**
	 * 
	 */

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private EmpDao empDao;

	@Autowired
	private JobDao jobDao;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeJob> getAllJobEmp() {
		Query query = session().createQuery("from EmployeeJob");
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeForId(int id) {
		Query query = session().createQuery(
				"select employee from EmployeeJob ej where ej.id=:id");
		query.setParameter("id", id);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<EmployeeJob> getEmployeesJobs(int empId) {
		List<EmployeeJob> empJobs = new ArrayList<EmployeeJob>();
		Query query = session().createQuery(
				"from EmployeeJob ej WHERE ej.employee.empId=:empId");
		query.setParameter("empId", empId);

		empJobs = (List<EmployeeJob>) query.list();

		return empJobs;
	}

	public void saveOrUpdate(EmployeeJob employeeJob) {

		session().saveOrUpdate(employeeJob);
	}

	public boolean deleteEntry(int id) {
		Query query = session().createQuery(
				"delete from EmployeeJob where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public EmployeeJob getEmployeeJob(int id) {
		Criteria crit = session().createCriteria(EmployeeJob.class);
		crit.add(Restrictions.idEq(id));
		return (EmployeeJob) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeJob> getEmployeeJobs(String sn) {
		Query query = session().createQuery(
				"from EmployeeJob ej where ej.employee.securityNumber=:sn");
		query.setParameter("sn", sn);
		return query.list();
	}

	public void updateEmployeeJob(int id, EmployeeJob employeeJob) {
		EmployeeJob employeeJob2 = getEmployeeJob(id);
		employeeJob2.setEmployee(employeeJob.getEmployee());
		employeeJob2.setJob(employeeJob.getJob());
		employeeJob2.setStartDate(employeeJob.getStartDate());
		employeeJob2.setEndDate(employeeJob.getEndDate());
		session().save(employeeJob2);

	}

	@SuppressWarnings("unchecked")
	public List<Job> getJobsForEmployee(int empId) {
		Query query = session()
				.createQuery(
						"select job from EmployeeJob ej where ej.employee.empId=:empId");
		query.setParameter("empId", empId);
		return query.list();

	}

	public void process(List<String> filesPath) throws ParseException {
		List<String[]> readCsv1 = new ArrayList<String[]>();
		// read data
		for (String filePath : filesPath) {
			if (CommonUtils.getFileExtension(filePath).equals("csv")) {
				// read csv file
				List<String[]> readCsv = CommonUtils.readCsv(filePath);
				readCsv1.addAll(readCsv);
			}

		}
		// import
		importData(readCsv1);
	}

	public void importData(List<String[]> readCsv) throws ParseException {
		List<Job> jobs = jobDao.getJobs();
		List<Employee> employees = empDao.getEmployees();

		for (String[] rows : readCsv) {
			String sn = rows[0];
			String jobReaded = rows[1];
			String startDateReaded = rows[2];
			String endDateReaded = rows[3];

			Employee employee = getSnPerEmployee(sn, employees);
			Job job = getJobByDescription(jobReaded, jobs);

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = df.parse(startDateReaded);

			EmployeeJob empJob = new EmployeeJob();
			empJob.setEmployee(employee);
			empJob.setJob(job);
			empJob.setStartDate(startDate);
			if (endDateReaded != null && !endDateReaded.isEmpty()) {
				Date endDate = df.parse(endDateReaded);
				empJob.setEndDate(endDate);
			} else {
				empJob.setEndDate(null);
			}

			session().save(empJob);

		}
	}

	private Job getJobByDescription(String jobDescription, List<Job> jobs) {
		for (Job job : jobs) {
			if (job.getJobDescription().equalsIgnoreCase(jobDescription)) {
				return job;
			}
		}
		return null;
	}

	private Employee getSnPerEmployee(String sn, List<Employee> employees) {
		for (Employee employee : employees) {
			if (employee.getSecurityNumber().equalsIgnoreCase(sn)) {
				return employee;
			}

		}
		return null;
	}

}
