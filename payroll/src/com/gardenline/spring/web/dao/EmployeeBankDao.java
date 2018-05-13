package com.gardenline.spring.web.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
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
@Component("employeeBankDao")
public class EmployeeBankDao implements Serializable {

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
	private BankDao bankDao;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeBank> getAllBankEmp() {
		Query query = session().createQuery("from EmployeeBank");
		return query.list();

	}

	public void saveOrUpdate(EmployeeBank employeeBank) {
		session().saveOrUpdate(employeeBank);
	}

	public boolean deleteEntry(int id) {
		Query query = session().createQuery(
				"delete from EmployeeBank where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public EmployeeBank getEmployeeBank(int id) {
		Criteria crit = session().createCriteria(EmployeeBank.class);
		crit.add(Restrictions.idEq(id));
		return (EmployeeBank) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeBank> getEmployeeBanks(String sn) {
		Query query = session().createQuery(
				"from EmployeeBank eb where eb.employee.securityNumber=:sn");
		query.setParameter("sn", sn);
		return query.list();
	}

	public void updateEmployeeBank(int id, EmployeeBank employeeBank) {
		EmployeeBank employeeBank2 = getEmployeeBank(id);
		employeeBank2.setEmployee(employeeBank.getEmployee());
		employeeBank2.setBank(employeeBank.getBank());
		employeeBank2.setBankAccount(employeeBank.getBankAccount());
		session().save(employeeBank2);

	}

	public void updateBank(int id, EmployeeBank employeeBank) {
		EmployeeBank employeeBank2 = getEmployeeBank(id);
		employeeBank2.setEmployee(employeeBank.getEmployee());
		employeeBank2.setBank(employeeBank.getBank());
		employeeBank2.setBankAccount(employeeBank.getBankAccount());

		session().save(employeeBank2);
	}

	@SuppressWarnings("unchecked")
	public List<EmployeeBank> getEmployeesBanks(int empId) {
		List<EmployeeBank> empBanks = new ArrayList<EmployeeBank>();
		Query query = session().createQuery(
				"from EmployeeBank eb where eb.employee.empId=:empId");
		query.setParameter("empId", empId);

		empBanks = (List<EmployeeBank>) query.list();

		return empBanks;
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
		List<Bank> banks = bankDao.getAllBank();
		List<Employee> employees = empDao.getEmployees();

		for (String[] rows : readCsv) {
			String sn = rows[0];
			String bankNameReaded = rows[1];
			String bankAccount = rows[2];

			Employee employee = getSnPerEmployee(sn, employees);
			Bank bank = getBankByName(bankNameReaded, banks);

			EmployeeBank empBank = new EmployeeBank();
			empBank.setEmployee(employee);
			empBank.setBank(bank);
			empBank.setBankAccount(bankAccount);
			;
			session().save(empBank);

		}
	}

	private Bank getBankByName(String bankName, List<Bank> banks) {
		for (Bank bank : banks) {
			if (bank.getBankName().equalsIgnoreCase(bankName)) {
				return bank;
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
