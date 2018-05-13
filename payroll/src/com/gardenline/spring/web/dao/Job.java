package com.gardenline.spring.web.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "job")
public class Job implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929659125565588275L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "job_id")
	private int jobId;

	@Column(name = "description")
	private String jobDescription;

	@SuppressWarnings("deprecation")
	@OneToMany(mappedBy = "job", cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
    org.hibernate.annotations.CascadeType.DELETE_ORPHAN})
	private List<EmployeeJob> empJobs = new ArrayList<EmployeeJob>();

	public Job() {

	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public List<EmployeeJob> getEmpJobs() {
		return empJobs;
	}

	public void setEmpJobs(List<EmployeeJob> empJobs) {
		this.empJobs = empJobs;
	}

}
