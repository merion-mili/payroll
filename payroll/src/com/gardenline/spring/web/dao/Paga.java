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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "paga",uniqueConstraints = { @UniqueConstraint(columnNames = {
		"recupero_id", "date","oretepunuara"}) })
public class Paga implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3524129817003801006L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "page_id")
	private int id;

	@ManyToOne
	@Cascade({ CascadeType.SAVE_UPDATE })
	@JoinColumn(name = "recupero_id")
	private Recupero recuperoEmployee;

	@ManyToOne
	@JoinColumn(name = "kantier_id")
	private Kantier kantier;

	@Column(name = "oretepunuara")
	private double oretepunuara;

	private double oretshtese;

	@Column(name = "recupero")
	private double recupero;

	@Column(name = "raportet")
	private double raportet;

	private double lejeDitore;

	private double lejeVjetore;

	private double festa;

	private double bonous;

	@Column(name = "totalirecupero")
	private double totalirecupero;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="date")
	private Date date;

	@Column(name = "koment")
	private String comment;

	@Column(name = "year")
	private int year;

	@Column(name = "muaj")
	private int month;

	@CreationTimestamp
	@Column(name = "createDate")
	private Date createDateTime;

	@UpdateTimestamp
	@Column(name = "updateDate")
	private Date updateDateTime;

	public Paga() {

	}

	// public Employee getEmployee() {
	// return employee;
	// }
	//
	// public void setEmployee(Employee employee) {
	// this.employee = employee;
	// }

	public Kantier getKantier() {
		return kantier;
	}

	public void setKantier(Kantier kantier) {
		this.kantier = kantier;
	}

	public double getOretepunuara() {
		return oretepunuara;
	}

	public void setOretepunuara(double oretepunuara) {
		this.oretepunuara = oretepunuara;
	}

	public double getOretshtese() {
		return oretshtese;
	}

	public void setOretshtese(double oretshtese) {
		this.oretshtese = oretshtese;
	}

	public double getRecupero() {
		return recupero;
	}

	public void setRecupero(double recupero) {
		this.recupero = recupero;
	}

	public double getRaportet() {
		return raportet;
	}

	public void setRaportet(double raportet) {
		this.raportet = raportet;
	}

	public double getLejeVjetore() {
		return lejeVjetore;
	}

	public void setLejeVjetore(double lejeVjetore) {
		this.lejeVjetore = lejeVjetore;
	}

	public double getTotalirecupero() {
		return totalirecupero;
	}

	public void setTotalirecupero(double totalirecupero) {
		this.totalirecupero = totalirecupero;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getId() {
		return id;
	}

	public double getLejeDitore() {
		return lejeDitore;
	}

	public void setLejeDitore(double lejeDitore) {
		this.lejeDitore = lejeDitore;
	}

	public Recupero getRecuperoEmployee() {
		return recuperoEmployee;
	}

	public void setRecuperoEmployee(Recupero recuperoEmployee) {
		this.recuperoEmployee = recuperoEmployee;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(Date updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public double getFesta() {
		return festa;
	}

	public void setFesta(double festa) {
		this.festa = festa;
	}

	public double getBonous() {
		return bonous;
	}

	public void setBonous(double bonous) {
		this.bonous = bonous;
	}

}
