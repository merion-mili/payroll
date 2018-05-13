package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Siguracionet;
import com.gardenline.spring.web.dao.SiguracionetDao;

@Service("siguracionetService")
public class SiguracionetService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8542108246954212901L;
	@Autowired
	private SiguracionetDao siguracionetDao;

	public List<Siguracionet> getCurrent() {
		return siguracionetDao.getAllSiguracionet();
	}

	public void createSiguracionin(Siguracionet siguracionet) {
		siguracionetDao.saveOrUpdate(siguracionet);

	}

	public Siguracionet getSiguracioninById(int id) {

		return siguracionetDao.getSiguracionin(id);
	}

	public void saveOrUpdate(int id, Siguracionet siguracionet) {
		siguracionetDao.updateSiguracionet(id, siguracionet);

	}

	public void deleteSiguracionin(int id) {
		siguracionetDao.deleteEntry(id);
	}

}
