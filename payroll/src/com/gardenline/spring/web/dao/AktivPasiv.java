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
@Table(name = "bank_cash")
public class AktivPasiv implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5058186760809218521L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bank_cash_id")
	private int id;

	private double gjendjeBank;

	private double gjendjeCash;

	@OneToOne
	@JoinColumn(name = "emp_id")
	@JsonIgnoreProperties({ "empJobs","empBanks"})
	private Employee employee;

	public AktivPasiv() {
		
	}

	public int getId() {
		return id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getGjendjeBank() {
		return gjendjeBank;
	}

	public void setGjendjeBank(double gjendjeBank) {
		this.gjendjeBank = gjendjeBank;
	}

	public double getGjendjeCash() {
		return gjendjeCash;
	}

	public void setGjendjeCash(double gjendjeCash) {
		this.gjendjeCash = gjendjeCash;
	}

}
