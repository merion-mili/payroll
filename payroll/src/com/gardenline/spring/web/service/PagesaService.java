package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.AktivPasiv;
import com.gardenline.spring.web.dao.Paga;
import com.gardenline.spring.web.dao.PagesaDao;
import com.gardenline.spring.web.dao.Pagesat;

@Service("pagesaService")
public class PagesaService implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8299003062838534331L;
	@Autowired
	private PagesaDao pagesaDao;

	@Secured({ "ROLE_ADMIN", "ROLE_DBA" })
	public List<Pagesat> getCurrent() {
		return pagesaDao.getPagesat();
	}
	
	@Secured({"ROLE_ADMIN" })
	public List<Paga> getPagaMontYear(int year, int month) {
		return pagesaDao.getPagatMonthYear(year, month);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DBA" })
	public void createPagesen(Pagesat pagesat) {
		pagesaDao.saveOrUpdate(pagesat);
		
	}

	public Pagesat getPagesen(int id) {

		return pagesaDao.getPagesat(id);
	}

	
	public void saveOrUpdate(int id, Pagesat pagesat) {
		pagesaDao.updatePagesen(id, pagesat);

	}

	public void deletePagesen(int id) {
		pagesaDao.deletePagesen(id);
	}
	
	public AktivPasiv getAktivPasivPagesat(int id){
		return pagesaDao.getAktivPasiv(id);
	}

	public void process(List<String> filepath) throws ParseException{
		pagesaDao.process(filepath);
	}

/*
	@Secured({"ROLE_ADMIN" })
	public List<Paga> getPagaMontYearPerEmployee(int year, int month, Recupero recuperoEmployee) {
		return pagesaDao.getPagatMonthYearPerEmployee(year, month, recuperoEmployee);
	}*/

	

}
