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
@Component("jobDao")
public class JobDao implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -8178963100519803990L;
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Job> getJobs() {
		Query query = session().createQuery("from Job");
		return (List<Job>)query.list();
	}

	public void saveOrUpdate(Job job){
		session().saveOrUpdate(job);
	}

	public boolean delete(int id) {
		Query query = session().createQuery("delete from Job where jobId=:id");
		query.setLong("id", id);
		return query.executeUpdate() == 1;
	}

	public Job getJob(int id) {
		Criteria crit = session().createCriteria(Job.class);

		crit.add(Restrictions.idEq(id));

		return (Job) crit.uniqueResult();
	}
	
	public void updateJob(int id, Job job) {
		Job job2 = getJob(id);
		job2.setJobDescription(job.getJobDescription());
		session().save(job2);
	}


	


}
