package com.gardenline.spring.web.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class FileUpload {
	
	private CommonsMultipartFile[] files;
	
	public FileUpload() {
		// TODO Auto-generated constructor stub
	}

	public CommonsMultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(CommonsMultipartFile[] files) {
		this.files = files;
	}
	
	

}
