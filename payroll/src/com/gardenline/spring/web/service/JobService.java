package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Job;
import com.gardenline.spring.web.dao.JobDao;

@Service("jobService")
public class JobService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 871695072653038641L;
	@Autowired
	private JobDao jobDao;


	public List<Job> getCurrent() {
		return jobDao.getJobs();
	}


	public void create(Job job) {
		jobDao.saveOrUpdate(job);
		
	}

	public Job getJob(int id) {

		return jobDao.getJob(id);
	}

/*	public void saveOrUpdate(int id, Job job) {
		jobDao.updateJob(id, job);

	}*/

	public void delete(int id) {
		jobDao.delete(id);
	}

}
