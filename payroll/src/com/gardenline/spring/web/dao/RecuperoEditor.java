package com.gardenline.spring.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenline.spring.web.service.RecuperoService;

@Component
public class RecuperoEditor extends PropertyEditorSupport{
	
	 private @Autowired 
	 RecuperoService recuperoService;

	@Override
	public void setAsText(String text) {
		Recupero recupero = this.recuperoService.getRecupero(Integer.parseInt(text));
		 this.setValue(recupero);
	}
	
	
}
