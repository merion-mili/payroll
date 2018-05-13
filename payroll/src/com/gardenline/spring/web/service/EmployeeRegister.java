package com.gardenline.spring.web.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.gardenline.spring.web.dao.Muajt;

public class EmployeeRegister {

	private int id;
	private int year;
	private String firstName;
	private String lastName;
	private String sn;
	private Date birthDate;
	private String jobName;

	private String pagaKontrate;

	Map<Muajt, MonthsEmployeeRegister> empregisterpermuaj = new HashMap<Muajt, MonthsEmployeeRegister>();

	public EmployeeRegister() {

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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Map<Muajt, MonthsEmployeeRegister> getEmpregisterpermuaj() {
		return empregisterpermuaj;
	}

	public void setEmpregisterpermuaj(
			Map<Muajt, MonthsEmployeeRegister> empregisterpermuaj) {
		this.empregisterpermuaj = empregisterpermuaj;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getPagaKontrate() {
		return pagaKontrate;
	}

	public void setPagaKontrate(String pagaKontrate) {
		this.pagaKontrate = pagaKontrate;
	}

}
