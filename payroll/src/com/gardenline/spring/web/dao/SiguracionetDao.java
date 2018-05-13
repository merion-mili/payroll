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
@Component("siguracionetDao")
public class SiguracionetDao implements Serializable{

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
	public List<Siguracionet> getAllSiguracionet() {
		Criteria crit = session().createCriteria(Siguracionet.class);
		return crit.list();
	}

	public void saveOrUpdate(Siguracionet siguracionet){
		session().saveOrUpdate(siguracionet);
	}

	public boolean deleteEntry(int id) {
		Query query = session().createQuery("delete from Siguracionet where siguaracionId=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Siguracionet getSiguracionin(int id) {
		Criteria crit = session().createCriteria(Siguracionet.class);
		crit.add(Restrictions.idEq(id));
		return (Siguracionet) crit.uniqueResult();
	}
	
	public Siguracionet getSiguracioninPerYear(int year) {
		Criteria crit = session().createCriteria(Siguracionet.class);
		crit.add(Restrictions.eq("year", year));
		return (Siguracionet) crit.uniqueResult();
	}
	

	public void updateSiguracionet(int id, Siguracionet siguracionet){
		Siguracionet siguracionet2 = getSiguracionin(id);
		siguracionet2.setPageminimale(siguracionet.getPageminimale());
		siguracionet2.setPagemaksimale(siguracionet.getPagemaksimale());
		siguracionet2.setKfsigshoq(siguracionet.getKfsigshoq());
		siguracionet2.setKfsigshen(siguracionet.getKfsigshen());
		siguracionet2.setYear(siguracionet.getYear());
		session().save(siguracionet2);
	}

}
