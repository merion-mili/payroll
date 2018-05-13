package com.gardenline.spring.web.dao;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "employee_salary")
public class EmployeeSalary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2369046730677994187L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_salary_id")
	private int id;
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
	@Column(name = "pagasera")
	private double pagasera;

	@Column(name = "pagakantier")
	private double pagakantier;

	@Column(name = "totalsera")
	private double totalsera;

	@Column(name = "totalkantier")
	private double totalkantier;

	@Column(name = "paga_kontrate")
	private double pagakontrate;
	
	@Column(name = "paga_hour_kontrate")
	private double pagahourkontrate;

	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Temporal(TemporalType.DATE)
	private Date endDate;

	public EmployeeSalary() {

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


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getPagasera() {
		return pagasera;
	}

	public void setPagasera(double pagasera) {
		this.pagasera = pagasera;
	}

	public double getPagakantier() {
		return pagakantier;
	}

	public void setPagakantier(double pagakantier) {
		this.pagakantier = pagakantier;
	}

	public double getTotalsera() {

		return totalsera;
	}

	public void setTotalsera(double totalsera) {
		this.totalsera = totalsera;
	}

	public double getTotalkantier() {

		return totalkantier;
	}

	public void setTotalkantier(double totalkantier) {
		this.totalkantier = totalkantier;
	}

	public double getPagakontrate() {
		return pagakontrate;
	}

	public void setPagakontrate(double pagakontrate) {
		this.pagakontrate = pagakontrate;
	}

	public double getPagahourkontrate() {
		return pagahourkontrate;
	}

	public void setPagahourkontrate(double pagahourkontrate) {
		this.pagahourkontrate = pagahourkontrate;
	}

	
}
