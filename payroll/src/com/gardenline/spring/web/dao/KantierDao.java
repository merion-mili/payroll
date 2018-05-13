package com.gardenline.spring.web.dao;

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
@Component("kantierDao")
public class KantierDao {
		
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Kantier> getKantiere() {
		Query query = session().createQuery("from Kantier");
		return (List<Kantier>)query.list();
	}
	
	

	public void saveOrUpdate(Kantier kantier){
		session().saveOrUpdate(kantier);
	}

	public boolean deleteKantier(int id) {
		Query query = session().createQuery("delete from Kantier where kantierId=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Kantier getKantier(int id) {
		Criteria crit = session().createCriteria(Kantier.class);

		crit.add(Restrictions.idEq(id));

		return (Kantier) crit.uniqueResult();
	}
	
	public void updateKantier(int id, Kantier kantier) {
		Kantier kantier2 = getKantier(id);
		kantier2.setKantierName(kantier.getKantierName());
		kantier2.setSimbol(kantier.getSimbol());
		session().save(kantier2);
	}


	


}
