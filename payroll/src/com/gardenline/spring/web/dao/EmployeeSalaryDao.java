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
@Component("employeeSalaryDao")
public class EmployeeSalaryDao implements Serializable {

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
	public List<EmployeeSalary> getAllSalaryEmp() {
		Query query = session().createQuery("from EmployeeSalary");
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Employee> getEmployeeForId(int id) {
		Query query = session().createQuery(
				"select employee from EmployeeSalary es where es.id=:id");
		query.setParameter("id", id);
		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<EmployeeSalary> getEmployeesSalaries(int empId) {
		List<EmployeeSalary> empSalaries = new ArrayList<EmployeeSalary>();
		Query query = session().createQuery(
				"from EmployeeSalary es WHERE es.employee.empId=:empId");
		query.setParameter("empId", empId);

		empSalaries = (List<EmployeeSalary>) query.list();

		return empSalaries;
	}

	public void saveOrUpdate(EmployeeSalary employeeSalary) {
		double pagasera = employeeSalary.getPagasera();
		double pagakantier = employeeSalary.getPagakantier();
		double pagakontrate = employeeSalary.getPagakontrate();
		double pagahourkontrate = pagakontrate / 26 * 8;
		double totalsera = pagasera * 26 * 8;
		double totalkantier = pagakantier * 26 * 8;
		employeeSalary.setTotalsera(totalsera);
		employeeSalary.setTotalkantier(totalkantier);
		employeeSalary.setPagahourkontrate(pagahourkontrate);
		session().saveOrUpdate(employeeSalary);
	}

	public boolean deleteEntry(int id) {
		Query query = session().createQuery(
				"delete from EmployeeSalary where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public EmployeeSalary getEmployeeSalary(int id) {
		Criteria crit = session().createCriteria(EmployeeSalary.class);
		crit.add(Restrictions.idEq(id));
		return (EmployeeSalary) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeSalary> getEmployeeSalaries(String sn) {
		Query query = session().createQuery(
				"from EmployeeSalary es where es.employee.securityNumber=:sn");
		query.setParameter("sn", sn);
		return query.list();
	}

	public void updateEmployeeSalary(int id, EmployeeSalary employeeSalary) {
		double pagakantier = employeeSalary.getPagakantier();
		double pagasera = employeeSalary.getPagasera();
		double totalsera = pagasera * 26 * 8;
		double totalkantier = pagakantier * 26 * 8;
		double pagakontrate = employeeSalary.getPagakontrate();
		double pagahourkontrate = pagakontrate / 26 * 8;
		EmployeeSalary employeeSalary2 = getEmployeeSalary(id);
		employeeSalary2.setEmployee(employeeSalary.getEmployee());
		employeeSalary2.setPagasera(pagasera);
		employeeSalary2.setPagakantier(pagakantier);
		employeeSalary2.setTotalsera(totalsera);
		employeeSalary2.setTotalkantier(totalkantier);
		employeeSalary2.setPagakontrate(pagakontrate);
		employeeSalary2.setPagahourkontrate(pagahourkontrate);
		employeeSalary2.setStartDate(employeeSalary.getStartDate());
		employeeSalary2.setEndDate(employeeSalary.getEndDate());
		session().save(employeeSalary2);

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
			String pagaKontrateReaded = rows[3];
			String pagaSeraReaded = rows[4];
			String pagaKantierReaded = rows[5];

			Employee employee = getSnPerEmployee(sn, employees);

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = df.parse(startDateReaded);

			double pagaKontrate = Double.parseDouble(pagaKontrateReaded);
			double pagaHourKontrate = pagaKontrate / 8 * 26;
			double pagaSera = Double.parseDouble(pagaSeraReaded);
			double pagaKantier = Double.parseDouble(pagaKantierReaded);
			double totalSera = pagaSera * 8 * 26;
			double totalKantier = pagaKantier * 8 * 26;

			EmployeeSalary empSalary = new EmployeeSalary();
			empSalary.setEmployee(employee);
			empSalary.setStartDate(startDate);
			if (endDateReaded != null && !endDateReaded.isEmpty()) {
				Date endDate = df.parse(endDateReaded);
				empSalary.setEndDate(endDate);
			} else {
				empSalary.setEndDate(null);
			}

			empSalary.setPagakantier(pagaKantier);
			empSalary.setPagasera(pagaSera);
			empSalary.setTotalsera(totalSera);
			empSalary.setTotalkantier(totalKantier);
			empSalary.setPagakontrate(pagaKontrate);
			empSalary.setPagahourkontrate(pagaHourKontrate);
			session().save(empSalary);

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

	public EmployeeSalary getPaga(Date startDate, int empId) {

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

		boolean isFirstSearchCriterion = true;

		StringBuilder query = new StringBuilder("from EmployeeSalary ");

		if (startDate != null) {

			if (isFirstSearchCriterion) {

				query.append(" where startDate <= '" + date.format(startDate)
						+ "'");

				query.append(" and endDate >= '" + date.format(startDate) + "'");

			} else {

				query.append(" and startDate <= '" + date.format(startDate)
						+ "'");

				query.append(" and endDate >= '" + date.format(startDate) + "'");

			}

			isFirstSearchCriterion = false;

		}

		if (empId != 0) {

			if (isFirstSearchCriterion) {

				query.append(" where employee.empId = " + empId);

			} else {

				query.append(" and employee.empId = " + empId);

			}

			isFirstSearchCriterion = false;

		}

		Query result = session().createQuery(query.toString());

		EmployeeSalary empSalary = (EmployeeSalary) result.list().get(0);

		return empSalary;
	}

}
