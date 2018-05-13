package com.gardenline.spring.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenline.spring.web.service.KantierService;

@Component
public class KantierEditor extends PropertyEditorSupport{
	
	 private @Autowired 
	 KantierService kantierService;

	@Override
	public void setAsText(String text) {
		Kantier kantier = this.kantierService.getKaniter(Integer.parseInt(text));
		 this.setValue(kantier);
	}
	
	
}
