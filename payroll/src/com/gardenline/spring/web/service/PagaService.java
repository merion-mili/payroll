package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Employee;
import com.gardenline.spring.web.dao.Paga;
import com.gardenline.spring.web.dao.PagaDao;
import com.gardenline.spring.web.dao.Recupero;

@Service("pagaService")
public class PagaService implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8299003062838534331L;
	@Autowired
	private PagaDao pagaDao;

	@Secured({ "ROLE_ADMIN", "ROLE_DBA" })
	public List<Paga> getCurrent() {
		return pagaDao.getPagat();
	}
	
	@Secured({"ROLE_ADMIN" })
	public List<Paga> getPagaMontYear(int year, int month) {
		return pagaDao.getPagatMonthYear(year, month);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DBA" })
	public void createPage(Paga paga) {
		pagaDao.saveOrUpdate(paga);
		
	}

	public Paga getPaga(int id) {

		return pagaDao.getPaga(id);
	}

	@Secured({ "ROLE_ADMIN", "ROLE_DBA" })
	public void saveOrUpdate(int id, Paga paga) {
		pagaDao.updatePaga(id, paga);

	}

	public void deletePaga(int id) {
		pagaDao.deleteEntry(id);
	}

	public Employee getEmployeeForPagId(int id) {
		return pagaDao.getEmployeeForPagId(id);
	}
	
	public List<Recupero> getRecuperotForPagId(int id) {
		return pagaDao.getRecuperotPerPageId(id);
	}

	
	
	public void process(List<String> filepath) throws Exception{
		pagaDao.process(filepath);
	}
	@Secured({"ROLE_ADMIN" })
	public List<Paga> getPagaMontYearPerEmployee(int year, int month, Recupero recuperoEmployee) {
		return pagaDao.getPagatMonthYearPerEmployee(year, month, recuperoEmployee);
	}

	

}
