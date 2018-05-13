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
@Component("bankDao")
public class BankDao {
		
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Bank> getAllBank() {
		Query query = session().createQuery("from Bank");
		return (List<Bank>)query.list();
	}

	public void saveOrUpdate(Bank bank){
		session().saveOrUpdate(bank);
	}

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Bank where bankId=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Bank getBank(int id) {
		Criteria crit = session().createCriteria(Bank.class);

		crit.add(Restrictions.idEq(id));

		return (Bank) crit.uniqueResult();
	}
	
	public void updateBank(int id, Bank bank) {
		Bank bank2 = getBank(id);
		bank2.setBankName(bank.getBankName());
		session().save(bank2);
	}


	

}
