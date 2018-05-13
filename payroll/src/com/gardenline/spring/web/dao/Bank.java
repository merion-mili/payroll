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
@Table(name = "bank")
public class Bank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7165095947145498365L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bank_id")
	private int bankId;

	@Column(name = "description")
	private String bankName;

	@SuppressWarnings("deprecation")
	@OneToMany(mappedBy = "bank", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE })
	@Cascade({ org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
	private List<EmployeeBank> empBanks = new ArrayList<EmployeeBank>();

	public Bank() {

	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getBankId() {
		return bankId;
	}

	public List<EmployeeBank> getEmpBanks() {
		return empBanks;
	}

	public void setEmpBanks(List<EmployeeBank> empBanks) {
		this.empBanks = empBanks;
	}

	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	
	

}
