package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Taksat;
import com.gardenline.spring.web.dao.TaksatDao;

@Service("taksatService")
public class TaksatService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8542108246954212901L;
	@Autowired
	private TaksatDao taksatDao;

	public List<Taksat> getCurrent() {
		return taksatDao.getAllTaksat();
	}

	public void createTaksen(Taksat taksat) {
		taksatDao.saveOrUpdate(taksat);

	}

	public Taksat getTaksatById(int id) {

		return taksatDao.getTaksat(id);
	}

	public void saveOrUpdate(int id, Taksat taksat) {
		taksatDao.updateTaksat(id, taksat);

	}

	public void deleteTaksat(int id) {
		taksatDao.deleteEntry(id);
	}

}
