package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.AktivPasiv;
import com.gardenline.spring.web.dao.AktivPasivDao;
import com.gardenline.spring.web.dao.Employee;

@Service("aktivPasivService")
public class AktivPasivService implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8542108246954212901L;
	@Autowired
	private AktivPasivDao aktivPasivDao;

	
	public List<AktivPasiv> getCurrent() {
		return aktivPasivDao.getAllAktivPasivSituacionet();
	}

	
	public void createAktivPasiv(AktivPasiv aktivpasiv) {
		aktivPasivDao.saveOrUpdate(aktivpasiv);
		
	}

	public AktivPasiv getAktivPasiv(int id) {

		return aktivPasivDao.getAktivPasiv(id);
	}

	public void saveOrUpdate(int id, AktivPasiv aktivpasiv) {
		aktivPasivDao.updateAktivPasiv(id, aktivpasiv);

	}

	public void deleteAktivPasiv(int id) {
		aktivPasivDao.deleteEntry(id);
	}

	public Employee getEmployeeForAktivPasiv(int id) {
		return aktivPasivDao.getEmployeeForAktivPasiv(id);
	}

	public List<AktivPasiv> getAktivPasivByFirstName(String firstName) {
		
		return aktivPasivDao.getAktivPasivByFirstName(firstName);
	}




}
