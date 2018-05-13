package com.gardenline.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "kantier")
public class Kantier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929659125565588275L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "kantier_id")
	private int kantierId;

	@Column(name = "kantier")
	private String kantierName;


	@Column(name = "simbol")
	private String simbol;
	
	public Kantier() {

	}


	public String getKantierName() {
		return kantierName;
	}


	public void setKantierName(String kantierName) {
		this.kantierName = kantierName;
	}


	public int getKantierId() {
		return kantierId;
	}


	public String getSimbol() {
		return simbol;
	}


	public void setSimbol(String simbol) {
		this.simbol = simbol;
	}

	
}
