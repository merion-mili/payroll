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
@Component("recuperoDao")
public class RecuperoDao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7569611324476013291L;
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Recupero> getRecuperot() {
		Criteria crit = session().createCriteria(Recupero.class);
		return crit.list();
	}

	public void saveOrUpdate(Recupero recupero){
		session().saveOrUpdate(recupero);
	}

	public boolean deleteEntry(int id) {
		Query query = session().createQuery("delete from Recupero where id=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Recupero getRecupero(int id) {
		Criteria crit = session().createCriteria(Recupero.class);
		crit.add(Restrictions.idEq(id));
		return (Recupero) crit.uniqueResult();
	}
	
	public Employee getEmployeeForRecupero(int id){
		Query query = session().createQuery("select rc.employee from Recupero rc where rc.id=:id");
		query.setParameter("id", id);
		Employee employee = (Employee) query.uniqueResult();
		return employee;
	}

	public void updateRecupero(int id, Recupero recupero){
		Recupero recupero2 = getRecupero(id);
		recupero2.setEmployee(recupero.getEmployee());
		recupero2.setGjendjerecupero(recupero.getGjendjerecupero());
		session().save(recupero2);
	}

	@SuppressWarnings("unchecked")
	public List<Recupero> getRecuperoByLastName(String firstName) {
		Query query =session().createQuery("from Recupero rc where rc.employee.firstName=:firstName");
		query.setParameter("firstName", firstName);
		return query.list();
	}
	
	
}
