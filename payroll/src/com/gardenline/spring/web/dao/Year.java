package com.gardenline.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "year_table")
public class Year implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8929659125565588275L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "year_id")
	private int yearId;

	@Column(name = "year")
	private int year;

	
	public Year() {

	}


	


	public int getYear() {
		return year;
	}





	public void setYear(int year) {
		this.year = year;
	}





	public int getYearId() {
		return yearId;
	}

	

}
