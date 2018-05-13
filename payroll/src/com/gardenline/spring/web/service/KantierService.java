package com.gardenline.spring.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Kantier;
import com.gardenline.spring.web.dao.KantierDao;

@Service("kantierService")
public class KantierService {
	@Autowired
	private KantierDao kantierDao;


	public List<Kantier> getCurrent() {
		return kantierDao.getKantiere();
	}


	public void createKantier(Kantier kantier) {
		kantierDao.saveOrUpdate(kantier);
		
	}

	public Kantier getKaniter(int id) {

		return kantierDao.getKantier(id);
	}

	public void saveOrUpdate(int id, Kantier kantier) {
		kantierDao.updateKantier(id, kantier);

	}

	public void deleteKantier(int id) {
		kantierDao.deleteKantier(id);
	}

	

}
