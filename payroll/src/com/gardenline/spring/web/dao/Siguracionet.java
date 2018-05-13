package com.gardenline.spring.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "siguracionet")
public class Siguracionet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1391159820926093414L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "siguracion_id")
	private int siguaracionId;

	@Column(name = "year")
	private int year;

	@Column(name = "page_min")
	private double pageminimale;
	
	@Column(name = "page_max")
	private double pagemaksimale;

	@Column(name = "sig_shoq")
	private double kfsigshoq;

	@Column(name = "sig_shen")
	private double kfsigshen;

	public Siguracionet() {

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

	public double getKfsigshoq() {
		return kfsigshoq;
	}

	public void setKfsigshoq(double kfsigshoq) {
		this.kfsigshoq = kfsigshoq;
	}

	public double getKfsigshen() {
		return kfsigshen;
	}

	public void setKfsigshen(double kfsigshen) {
		this.kfsigshen = kfsigshen;
	}

	public int getSiguaracionId() {
		return siguaracionId;
	}

}
