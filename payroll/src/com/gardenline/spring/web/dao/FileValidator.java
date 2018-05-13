package com.gardenline.spring.web.dao;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gardenline.spring.web.service.FileUpload;
import com.gardenline.spring.web.utils.CommonUtils;

@Component("fileValidator")
public class FileValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return FileUpload.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FileUpload fileUpload = (FileUpload) target;
		CommonsMultipartFile[] multipartFiles = fileUpload.getFiles();
		
		for (CommonsMultipartFile multipartFile : multipartFiles) {
			
			//check file size
			if(multipartFile.getSize()==0){
				errors.rejectValue("files", "required.file");
				break;
			}
			
			//check file extension
			String fileExtension  = CommonUtils.getFileExtension(multipartFile.getOriginalFilename().toLowerCase());
			if(!fileExtension.equals("csv")){
				errors.rejectValue("files", "file.extension.allowed");
				break;
			}
			
		}
	}

}
