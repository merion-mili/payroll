package com.gardenline.spring.web.dao;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import com.gardenline.spring.web.service.JobService;

public class EmpJobCollectionEditor extends CustomCollectionEditor {
	
	@Autowired
	private JobService jobService;

	public EmpJobCollectionEditor(Class<? extends Collection> collectionType,
			JobService jobService) {
		super(collectionType);
		this.jobService = jobService;
		
	}
	
	@Override
    protected Object convertElement(Object element) {
		Integer jobId = Integer.parseInt(element.toString());

        Job job = jobService.getJob(jobId);
        EmployeeJob empJob = new EmployeeJob();

       
        Employee employee = new Employee();

        empJob.setEmployee(employee);
        empJob.setJob(job);
        empJob.setStartDate(new Date());
        empJob.setEndDate(new Date());
        

        return empJob;
    }

}
