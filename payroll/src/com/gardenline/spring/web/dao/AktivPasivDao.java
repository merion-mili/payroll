package com.gardenline.spring.web.dao;

import java.io.Serializable;
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



@Repository
@Transactional
@Component("aktivPasivDao")
public class AktivPasivDao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2855804015261599682L;
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<AktivPasiv> getAllAktivPasivSituacionet() {
		Criteria crit = session().createCriteria(AktivPasiv.class);
		return crit.list();
	}

	public void saveOrUpdate(AktivPasiv aktivPasiv){
		session().saveOrUpdate(aktivPasiv);
	}

	public boolean deleteEntry(int id) {
		Query query = session().createQuery("delete from AktivPasiv where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public AktivPasiv getAktivPasiv(int id) {
		Criteria crit = session().createCriteria(AktivPasiv.class);
		crit.add(Restrictions.idEq(id));
		return (AktivPasiv) crit.uniqueResult();
	}
	
	public Employee getEmployeeForAktivPasiv(int id){
		Query query = session().createQuery("select employee from AktivPasiv ap where ap.id=:id");
		query.setParameter("id", id);
		Employee employee = (Employee) query.uniqueResult();
		return employee;
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees(){
		Query query = session().createQuery("select ap.employee from AktivPasiv ap");
		return query.list();
	}

	public void updateAktivPasiv(int id, AktivPasiv aktivpasiv){
		AktivPasiv aktivpasiv2 = getAktivPasiv(id);
		aktivpasiv2.setEmployee(aktivpasiv.getEmployee());
		aktivpasiv2.setGjendjeBank(aktivpasiv.getGjendjeBank());
		aktivpasiv2.setGjendjeCash(aktivpasiv.getGjendjeCash());
		session().save(aktivpasiv2);
	}

	@SuppressWarnings("unchecked")
	public List<AktivPasiv> getAktivPasivByFirstName(String firstName) {
		Query query =session().createQuery("from AktivPasiv ap where ap.employee.firstName=:firstName");
		query.setParameter("firstName", firstName);
		return query.list();
	}
	
	
	
	
	
}
