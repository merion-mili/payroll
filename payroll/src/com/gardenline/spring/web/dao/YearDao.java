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
@Component("yearDao")
public class YearDao implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 2071762784462668806L;
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Year> getYears() {
		Query query = session().createQuery("from Year");
		return (List<Year>)query.list();
	}

	public void saveOrUpdate(Year year){
		session().saveOrUpdate(year);
	}

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Year where yearId=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Year getYear(int id) {
		Criteria crit = session().createCriteria(Year.class);

		crit.add(Restrictions.idEq(id));

		return (Year) crit.uniqueResult();
	}
	
	public void updateYear(int id, Year year) {
		Year year2 =getYear(id);
		year2.setYear(year.getYear());
		session().save(year2);
	}


	


}
