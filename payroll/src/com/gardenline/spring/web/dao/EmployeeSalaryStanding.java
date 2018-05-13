package com.gardenline.spring.web.dao;

import java.io.Serializable;
import java.util.List;

public class EmployeeSalaryStanding implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5552299201223214000L;



	private int id;

	private String firstName;
	private String lastName;

	private String sn;

	private double totalhours;
	private double totalhoursSera;
	private double totalhoursKantier;
	private double totaloretshteseSera;
	private long ditetEPunuara;
	private long ditetEfestave;
	private long totalRaportet;
	private long ditetLejeVjetore;
	private double totalLejeDitore;
	private double totalRecupero;
	private double gjendjaRecupero;
	private double brutoSalary;
	private double pagabaze;
	private double cashAmount;
	private double bankAmount;
	private double sigurimet;
	private double taksat;
	private double paganeto;
	private double bonous;
	private double pagapalejeregjister;
	private double pagalejevjetore;
	private String intervallejedate;

	private double totalNetoCash;

	private List<EmployeeBank> employeeBanks;

	public EmployeeSalaryStanding() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public double getTotalRecupero() {
		return totalRecupero;
	}

	public void setTotalRecupero(double totalRecupero) {
		this.totalRecupero = totalRecupero;
	}

	public double getPagabaze() {
		return pagabaze;
	}

	public void setPagabaze(double pagabaze) {
		this.pagabaze = pagabaze;
	}

	public double getBrutoSalary() {
		return brutoSalary;
	}

	public void setBrutoSalary(double brutoSalary) {
		this.brutoSalary = brutoSalary;
	}

	public double getTotalhours() {
		return totalhours;
	}

	public void setTotalhours(double totalhours) {
		this.totalhours = totalhours;
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

	public double getSigurimet() {
		return sigurimet;
	}

	public void setSigurimet(double sigurimet) {
		this.sigurimet = sigurimet;
	}

	public double getTaksat() {
		return taksat;
	}

	public void setTaksat(double taksat) {
		this.taksat = taksat;
	}

	public double getPaganeto() {
		return paganeto;
	}

	public void setPaganeto(double paganeto) {
		this.paganeto = paganeto;
	}

	public long getDitetEPunuara() {
		return ditetEPunuara;
	}

	public void setDitetEPunuara(long ditetEPunuara) {
		this.ditetEPunuara = ditetEPunuara;
	}

	public void setDitetLejeVjetore(long ditetLejeVjetore) {
		this.ditetLejeVjetore = ditetLejeVjetore;
	}

	public double getTotalLejeDitore() {
		return totalLejeDitore;
	}

	public void setTotalLejeDitore(double totalLejeDitore) {
		this.totalLejeDitore = totalLejeDitore;
	}

	public long getDitetLejeVjetore() {
		return ditetLejeVjetore;
	}

	public double getGjendjaRecupero() {
		return gjendjaRecupero;
	}

	public void setGjendjaRecupero(double gjendjaRecupero) {
		this.gjendjaRecupero = gjendjaRecupero;
	}

	public long getTotalRaportet() {
		return totalRaportet;
	}

	public void setTotalRaportet(long totalRaportet) {
		this.totalRaportet = totalRaportet;
	}

	public List<EmployeeBank> getEmployeeBanks() {
		return employeeBanks;
	}

	public void setEmployeeBanks(List<EmployeeBank> employeeBanks) {
		this.employeeBanks = employeeBanks;
	}

	public long getDitetEfestave() {
		return ditetEfestave;
	}

	public void setDitetEfestave(long ditetEfestave) {
		this.ditetEfestave = ditetEfestave;
	}

	public double getBonous() {
		return bonous;
	}

	public void setBonous(double bonous) {
		this.bonous = bonous;
	}

	public double getTotalNetoCash() {
		return totalNetoCash;
	}

	public void setTotalNetoCash(double totalNetoCash) {
		this.totalNetoCash = totalNetoCash;
	}

	public double getTotalhoursSera() {
		return totalhoursSera;
	}

	public void setTotalhoursSera(double totalhoursSera) {
		this.totalhoursSera = totalhoursSera;
	}

	public double getTotalhoursKantier() {
		return totalhoursKantier;
	}

	public void setTotalhoursKantier(double totalhoursKantier) {
		this.totalhoursKantier = totalhoursKantier;
	}

	public double getTotaloretshteseSera() {
		return totaloretshteseSera;
	}

	public void setTotaloretshteseSera(double totaloretshteseSera) {
		this.totaloretshteseSera = totaloretshteseSera;
	}

	public double getPagapalejeregjister() {
		return pagapalejeregjister;
	}

	public void setPagapalejeregjister(double pagapalejeregjister) {
		this.pagapalejeregjister = pagapalejeregjister;
	}

	public double getPagalejevjetore() {
		return pagalejevjetore;
	}

	public void setPagalejevjetore(double pagalejevjetore) {
		this.pagalejevjetore = pagalejevjetore;
	}

	public String getIntervallejedate() {
		return intervallejedate;
	}

	public void setIntervallejedate(String intervallejedate) {
		this.intervallejedate = intervallejedate;
	}
	
	

}
