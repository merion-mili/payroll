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
@Component("employeeEnabledDao")
public class EmployeeEnabledDao implements Serializable {

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

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeEnabled> getAllEmpEnabled() {
		Query query = session().createQuery("from EmployeeEnabled");
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeForId(int id) {
		Query query = session().createQuery(
				"select employee from EmployeeEnabled en where en.id=:id");
		query.setParameter("id", id);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<EmployeeEnabled> getEmployeesEnabled(int empId) {
		List<EmployeeEnabled> empEnables = new ArrayList<EmployeeEnabled>();
		Query query = session().createQuery(
				"from EmployeeEnabled en WHERE en.employee.empId=:empId");
		query.setParameter("empId", empId);

		empEnables = (List<EmployeeEnabled>) query.list();

		return empEnables;
	}

	public void saveOrUpdate(EmployeeEnabled employeeEnabled) {
		session().saveOrUpdate(employeeEnabled);
	}

	public boolean deleteEntry(int id) {
		Query query = session().createQuery(
				"delete from EmployeeEnabled where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public EmployeeEnabled getEmployeeEnabled(int id) {
		Criteria crit = session().createCriteria(EmployeeEnabled.class);
		crit.add(Restrictions.idEq(id));
		return (EmployeeEnabled) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeJob> getEmployeeJobs(String sn) {
		Query query = session().createQuery(
				"from EmployeeJob ej where ej.employee.securityNumber=:sn");
		query.setParameter("sn", sn);
		return query.list();
	}

	public void updateEmployeeEnabled(int id, EmployeeEnabled employeeEnabled) {
		EmployeeEnabled employeeEnabled2 = getEmployeeEnabled(id);
		employeeEnabled2.setStartDate(employeeEnabled.getStartDate());
		employeeEnabled2.setEndDate(employeeEnabled.getEndDate());
		employeeEnabled2.setEnabled(employeeEnabled.isEnabled());
		session().save(employeeEnabled2);

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

		List<Employee> employees = empDao.getEmployees();

		for (String[] rows : readCsv) {
			String sn = rows[0];
			String startDateReaded = rows[1];
			String endDateReaded = rows[2];
			String pagaEnabledReaded = rows[3];

			Employee employee = getSnPerEmployee(sn, employees);

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = df.parse(startDateReaded);
			
			Boolean enabled = Boolean.valueOf(pagaEnabledReaded);

			EmployeeEnabled empEnabled = new EmployeeEnabled();
			empEnabled.setEmployee(employee);
			empEnabled.setStartDate(startDate);
			if(endDateReaded !=null && !endDateReaded.isEmpty()){
				Date endDate =df.parse(endDateReaded);
				empEnabled.setEndDate(endDate);
			}else{
				empEnabled.setEndDate(null);
			}
			
			empEnabled.setEnabled(enabled);
			session().save(empEnabled);

		}
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
