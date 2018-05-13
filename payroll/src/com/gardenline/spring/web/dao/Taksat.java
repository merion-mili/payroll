package com.gardenline.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taksat")
public class Taksat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1391159820926093414L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "siguracion_id")
	private int taksId;

	@Column(name = "year")
	private int year;

	@Column(name = "page_min")
	private double pageminimale;

	@Column(name = "page_max")
	private double pagemaksimale;

	@Column(name = "kf_min")
	private double kfpagmin;

	@Column(name = "kf_max")
	private double kfpagmax;

	public Taksat() {

	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPageminimale() {
		return pageminimale;
	}

	public void setPageminimale(double pageminimale) {
		this.pageminimale = pageminimale;
	}

	public double getPagemaksimale() {
		return pagemaksimale;
	}

	public void setPagemaksimale(double pagemaksimale) {
		this.pagemaksimale = pagemaksimale;
	}

	
	public int getTaksId() {
		return taksId;
	}

	public double getKfpagmin() {
		return kfpagmin;
	}

	public void setKfpagmin(double kfpagmin) {
		this.kfpagmin = kfpagmin;
	}

	public double getKfpagmax() {
		return kfpagmax;
	}

	public void setKfpagmax(double kfpagmax) {
		this.kfpagmax = kfpagmax;
	}

}
