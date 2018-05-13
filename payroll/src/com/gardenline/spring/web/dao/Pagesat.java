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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pagesat")
public class Pagesat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4482936872066986230L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pagesat_id")
	private int id; 
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="pagesa_date")
	private Date date;
	
	@Column(name="month")
	private int month;
	@Column(name="year")
	private int year;
	@Column(name="rroga_cash")
	private double cashAmount;
	@Column(name="rroga_bank")
	private double bankAmount;
	@Column(name="paradhenie")
	private double paradhenie;
	@Column(name="pagesa_cash")
	private double pagesaCash;
	@Column(name="pagesa_bank")
	private double pagesaBank;
	@Column(name="tota_cash")
	private double totalCash;
	@Column(name="tota_bank")
	private double totalBank;

	@ManyToOne
	@JoinColumn(name="emp_bank_id")
	private EmployeeBank employeeBank;

	@ManyToOne
	@Cascade({ CascadeType.SAVE_UPDATE })
	@JoinColumn(name="bank_cash_id")
	private AktivPasiv aktivPasiv;
	
	
	
	public Pagesat() {
		
	}

	public int getId() {
		return id;
	}
	
	
	
	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public int getMonth() {
		return month;
	}


	public void setMonth(int month) {
		this.month = month;
	}


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public double getCashAmount() {
		return cashAmount;
	}


	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}


	public double getBankAmount() {
		return bankAmount;
	}


	public void setBankAmount(double bankAmount) {
		this.bankAmount = bankAmount;
	}


	public double getParadhenie() {
		return paradhenie;
	}


	public void setParadhenie(double paradhenie) {
		this.paradhenie = paradhenie;
	}


	public double getPagesaCash() {
		return pagesaCash;
	}


	public void setPagesaCash(double pagesaCash) {
		this.pagesaCash = pagesaCash;
	}


	public double getPagesaBank() {
		return pagesaBank;
	}


	public void setPagesaBank(double pagesaBank) {
		this.pagesaBank = pagesaBank;
	}


	public double getTotalCash() {
		return totalCash;
	}


	public void setTotalCash(double totalCash) {
		this.totalCash = totalCash;
	}


	public double getTotalBank() {
		return totalBank;
	}


	public void setTotalBank(double totalBank) {
		this.totalBank = totalBank;
	}


	public AktivPasiv getAktivPasiv() {
		return aktivPasiv;
	}


	public void setAktivPasiv(AktivPasiv aktivPasiv) {
		this.aktivPasiv = aktivPasiv;
	}

	public EmployeeBank getEmployeeBank() {
		return employeeBank;
	}

	public void setEmployeeBank(EmployeeBank employeeBank) {
		this.employeeBank = employeeBank;
	}
	
	

	/*public List<BankEmployee> getBankEmployee() {
		return bankEmployee;
	}

	public void setBankEmployee(List<BankEmployee> bankEmployee) {
		this.bankEmployee = bankEmployee;
	}
*/

	

	
	
}
