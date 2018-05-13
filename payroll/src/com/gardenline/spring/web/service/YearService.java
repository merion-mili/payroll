package com.gardenline.spring.web.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardenline.spring.web.dao.Year;
import com.gardenline.spring.web.dao.YearDao;

@Service("yearService")
public class YearService implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3611015038579331547L;
	@Autowired
	private YearDao yearDao;

	public List<Year> getCurrent() {
		return yearDao.getYears();
	}

	public void create(Year year) {
		yearDao.saveOrUpdate(year);

	}

	public Year getYear(int id) {

		return yearDao.getYear(id);
	}

	public void delete(int id) {
		yearDao.delete(id);
	}

	public void saveOrUpdate(int id, Year year) {
		yearDao.updateYear(id, year);

	}

}
