package com.gardenline.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employee_bank")
public class EmployeeBank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5499640315777376148L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_bank_id")
	private int id;
	@ManyToOne
	@JoinColumn(name = "emp_id")
	@JsonIgnoreProperties({"empJobs", "empBanks"}) 
	private Employee employee;
	@ManyToOne
	@JoinColumn(name = "bank_id")
	@JsonIgnoreProperties({"empJobs", "empBanks"}) 
	private Bank bank;

	private String bankAccount;

	public EmployeeBank() {

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

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

}
