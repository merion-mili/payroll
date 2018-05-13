package com.gardenline.spring.web.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "employee", uniqueConstraints = { @UniqueConstraint(columnNames = { "sn" }) })
@NamedQuery(name = "querySn", query = "from Employee a where a.securityNumber=:securityNumber")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8216417497308437984L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_id")
	private int empId;

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 4, max = 60, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Column(name = "firstname")
	private String firstName;

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 4, max = 60, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Column(name = "fathername")
	private String fatherName;
	@Column(name = "lastname")
	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 4, max = 60, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	private String lastName;

	@Column(name = "telephone_number")
	private String telephone;

	@Column(name = "email")
	private String email;

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 6, max = 12, groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Column(name = "sn")
	private String securityNumber;

	@NotNull(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "dob")
	private Date birsthDate;

	@Column(name = "nat")
	private String nationality;

	@NotBlank(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Column(name = "address")
	private String address;

	@NotNull(groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "kontratedate")
	private Date kontrateDate;

	@Column(name = "pervoja")
	private double pervoja;

	@Transient
	private MultipartFile employeeCard;

	@SuppressWarnings("deprecation")
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<EmployeeJob> empJobs = new ArrayList<EmployeeJob>();

	@SuppressWarnings("deprecation")
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<EmployeeBank> empBanks = new ArrayList<EmployeeBank>();

	@SuppressWarnings("deprecation")
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<EmployeeEnabled> empEnabled = new ArrayList<EmployeeEnabled>();
	
	
	@SuppressWarnings("deprecation")
	@OneToMany(mappedBy = "employee", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<EmployeeSalary> empSalary = new ArrayList<EmployeeSalary>();

	public Employee() {

	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSecurityNumber() {
		return securityNumber;
	}

	public void setSecurityNumber(String securityNumber) {
		this.securityNumber = securityNumber;
	}

	public Date getBirsthDate() {
		return birsthDate;
	}

	public void setBirsthDate(Date birsthDate) {
		this.birsthDate = birsthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<EmployeeJob> getEmpJobs() {
		return empJobs;
	}

	public void setEmpJobs(List<EmployeeJob> empJobs) {
		this.empJobs = empJobs;
	}

	public MultipartFile getEmployeeCard() {
		return employeeCard;
	}

	public void setEmployeeCard(MultipartFile employeeCard) {
		this.employeeCard = employeeCard;
	}

	public List<EmployeeBank> getEmpBanks() {
		return empBanks;
	}

	public void setEmpBanks(List<EmployeeBank> empBanks) {
		this.empBanks = empBanks;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getKontrateDate() {
		return kontrateDate;
	}

	public void setKontrateDate(Date kontrateDate) {
		this.kontrateDate = kontrateDate;
	}

	public double getPervoja() {
		return pervoja;
	}

	public void setPervoja(double pervoja) {
		this.pervoja = pervoja;
	}

	public List<EmployeeEnabled> getEmpEnabled() {
		return empEnabled;
	}

	public void setEmpEnabled(List<EmployeeEnabled> empEnabled) {
		this.empEnabled = empEnabled;
	}

	public List<EmployeeSalary> getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(List<EmployeeSalary> empSalary) {
		this.empSalary = empSalary;
	}
	
	

}
