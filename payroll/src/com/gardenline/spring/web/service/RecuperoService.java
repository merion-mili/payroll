package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.Recupero;
import com.gardenline.spring.web.dao.RecuperoDao;

@Service("recuperoService")
public class RecuperoService implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8542108246954212901L;
	@Autowired
	private RecuperoDao recuperoDao;

	
	public List<Recupero> getCurrent() {
		return recuperoDao.getRecuperot();
	}

	
	public void createRecupero(Recupero recupero) {
		recuperoDao.saveOrUpdate(recupero);
		
	}

	public Recupero getRecupero(int id) {

		return recuperoDao.getRecupero(id);
	}

	public void saveOrUpdate(int id, Recupero recupero) {
		recuperoDao.updateRecupero(id, recupero);

	}

	public void deleteRecupero(int id) {
		recuperoDao.deleteEntry(id);
	}

	public Employee getEmployeeForRecupero(int id) {
		return recuperoDao.getEmployeeForRecupero(id);
	}

	public List<Recupero> getRecuperotByLastName(String firstName) {
		
		return recuperoDao.getRecuperoByLastName(firstName);
	}




}
