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
@Component("taksatDao")
public class TaksatDao implements Serializable{

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
	public List<Taksat> getAllTaksat() {
		Criteria crit = session().createCriteria(Taksat.class);
		return crit.list();
	}

	public void saveOrUpdate(Taksat taksat){
		session().saveOrUpdate(taksat);
	}

	public boolean deleteEntry(int id) {
		Query query = session().createQuery("delete from Taksat where taksId=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Taksat getTaksat(int id) {
		Criteria crit = session().createCriteria(Taksat.class);
		crit.add(Restrictions.idEq(id));
		return (Taksat) crit.uniqueResult();
	}
	
	public Taksat getTaksatPerYear(int year) {
		Criteria crit = session().createCriteria(Taksat.class);
		crit.add(Restrictions.eq("year", year));
		return (Taksat) crit.uniqueResult();
	}
	
	

	public void updateTaksat(int id, Taksat taksat){
		Taksat taksat2 = getTaksat(id);
		taksat2.setPageminimale(taksat.getPageminimale());
		taksat2.setPagemaksimale(taksat.getPagemaksimale());
		taksat2.setKfpagmin(taksat.getKfpagmin());
		taksat2.setKfpagmax(taksat.getKfpagmax());
		taksat2.setYear(taksat.getYear());
		session().save(taksat2);
	}

	
}
