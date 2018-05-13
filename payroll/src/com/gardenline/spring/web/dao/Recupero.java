package com.gardenline.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name="recupero_employee")
public class Recupero implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5058186760809218521L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recupero_id")
	private int id;
	
	private double gjendjerecupero;
	@OneToOne
	@JoinColumn(name="emp_id")
	@JsonIgnoreProperties({"empJobs","empBanks"}) 
	private Employee employee;
	
	public Recupero() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}
	
	
	public double getGjendjerecupero() {
		return gjendjerecupero;
	}

	public void setGjendjerecupero(double gjendjerecupero) {
		this.gjendjerecupero = gjendjerecupero;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
}
