package com.gardenline.spring.web.dao;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gardenline.spring.web.service.AktivPasivService;

@Component
public class AktivPasivEditor extends PropertyEditorSupport{
	
	@Autowired 
	private AktivPasivService aktivPasivSerivce;
	 

	@Override
	public void setAsText(String text) {
		AktivPasiv aktivPasiv = this.aktivPasivSerivce.getAktivPasiv(Integer.parseInt(text));
		 this.setValue(aktivPasiv);
	}
	

}
