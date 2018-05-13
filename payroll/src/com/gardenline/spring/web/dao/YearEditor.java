package com.gardenline.spring.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenline.spring.web.service.YearService;

@Component
public class YearEditor extends PropertyEditorSupport {

	private @Autowired YearService yearService;

	@Override
	public void setAsText(String text) {
		Year year = this.yearService.getYear(Integer.parseInt(text));
		this.setValue(year);
	}

}
