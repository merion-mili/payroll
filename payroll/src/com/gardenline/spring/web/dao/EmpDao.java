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
@Component("empDao")
public class EmpDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4040515957667721863L;

	@Autowired
	private JobDao jobDao;

	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEnabledEmployees(Date startDate, boolean enabled) {

		Query query = session()
				.createQuery(
						"select en.employee from EmployeeEnabled en WHERE en.startDate<=:startDate and en.enabled=:enabled");
		query.setParameter("startDate", startDate);
		// query.setParameter("endDate", endDate);
		query.setParameter("enabled", enabled);
		return query.list();
	}

	public boolean exists(String securityNumber) {
		Employee employee = getEmployee(securityNumber);
		return employee != null;
	}

	public Employee getEmployee(String securityNumber) {
		Criteria crit = session().createCriteria(Employee.class);
		crit.add(Restrictions.eq("securityNumber", securityNumber));
		return (Employee) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		Criteria crit = session().createCriteria(Employee.class);
		return crit.list();
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees(int empId) {
		Query query = session().createQuery(
				"from Employee e WHERE e.empId=:empId");
		query.setParameter("empId", empId);
		return query.list();
	}

	public void saveOrUpdate(Employee employee) {
		session().saveOrUpdate(employee);
	}

	public Employee getEmployee(int id) {
		Criteria crit = session().createCriteria(Employee.class);

		crit.add(Restrictions.idEq(id));

		return (Employee) crit.uniqueResult();
	}

	public void update(Employee employee) {
		session().saveOrUpdate(employee);
		session().flush();

	}

	public void updateEmployee(int empId, Employee employee) {
		Employee employee2 = getEmployee(empId);
		employee2.setFirstName(employee.getFirstName());
		employee2.setFatherName(employee.getFatherName());
		employee2.setLastName(employee.getLastName());
		employee2.setEmail(employee.getEmail());
		employee2.setBirsthDate(employee.getBirsthDate());
		employee2.setKontrateDate(employee.getKontrateDate());
		employee2.setAddress(employee.getAddress());
		employee2.setTelephone(employee.getTelephone());
		employee2.setSecurityNumber(employee.getSecurityNumber());
		employee2.setNationality(employee.getNationality());
		employee2.setPervoja(employee.getPervoja());
		session().save(employee2);

	}




	public void deleteEmployee(int empId) {
		Query query1 = session().createQuery(
				"delete EmployeeJob ej where ej.employee.empId=:empId");
		Query query2 = session().createQuery(
				"delete from Employee where empId=:empId");
		query1.setLong("empId", empId);
		query1.executeUpdate();
		query2.setLong("empId", empId);
		query2.executeUpdate();
	}

	public void deleteEmployeeJob(EmployeeJob employeeJob) {
		session().delete(employeeJob);

	}

	public void deleteEmployeeBank(EmployeeBank employeeBank) {
		session().delete(employeeBank);

	}

	public void process(List<String> filesPath) throws ParseException {
		List<String[]> readCsv1 =new ArrayList<String[]>();
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

	public void importData(List<String[]> readCsv) throws ParseException  {
		
		

		for (String[] rows : readCsv) {
			String sn = rows[0];
			String firstName=rows[1];
			String fatherName=rows[2];
			String lastName=rows[3];
			String dateOfBirthReaded = rows[4];
			String kontrateDateReaded = rows[5];
			String email = rows[6];
			String address = rows[7];
			String telephone = rows[8];
			String nationality = rows[9];
			String pervojaReaded = rows[10];
					
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date dateOfBirth =df.parse(dateOfBirthReaded);
			Date kontrateDate =df.parse(kontrateDateReaded);
			double pervoja = Double.parseDouble(pervojaReaded);
			
			
			Query namedQuery = session().getNamedQuery("queryBySn");

			namedQuery.setString("securityNumber", sn);
			
			Employee uniqueResult = (Employee) namedQuery.uniqueResult();
			
			if (uniqueResult == null) {
				Employee employee = new Employee();
				employee.setSecurityNumber(sn);
				employee.setFirstName(firstName);
				employee.setFatherName(fatherName);
				employee.setLastName(lastName);
				employee.setBirsthDate(dateOfBirth);
				employee.setKontrateDate(kontrateDate);
				employee.setEmail(email);
				employee.setAddress(address);
				employee.setTelephone(telephone);
				employee.setNationality(nationality);
				employee.setPervoja(pervoja);
				session().save(employee);
			}

		}
	}
}
