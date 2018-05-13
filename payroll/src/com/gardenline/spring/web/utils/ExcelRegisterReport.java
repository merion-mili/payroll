package com.gardenline.spring.web.utils;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.gardenline.spring.web.dao.Muajt;
import com.gardenline.spring.web.service.EmployeeRegister;
import com.gardenline.spring.web.service.MonthsEmployeeRegister;

public class ExcelRegisterReport extends AbstractXlsView {
	
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition",
				"attachment;filename=\"monthlysalary.xls\"");
		EmployeeRegister employeeRegister = (EmployeeRegister) model
				.get("standingPerEmployee");

		
		HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Register Report");

		HSSFRow header = sheet.createRow(0);
		 
		header.createCell(0).setCellValue("Regjistri i punonjesit:");
		header.createCell(1).setCellValue("Viti" + " " + employeeRegister.getYear());
		header.createCell(2).setCellValue(employeeRegister.getFirstName()+" "+employeeRegister.getLastName());
		header = sheet.createRow(2);
		header.createCell(0).setCellValue("Dita e lindjes: ");
		header.createCell(1).setCellValue(sdf.format(employeeRegister.getBirthDate()));
		
		header = sheet.createRow(3);
		header.createCell(0).setCellValue("Profesioni: ");
		header.createCell(1).setCellValue(employeeRegister.getJobName());
		
		header = sheet.createRow(4);
		header.createCell(0).setCellValue("Paga: ");
		header.createCell(1).setCellValue(employeeRegister.getPagaKontrate());
		
		header = sheet.createRow(6);
		
		header.createCell(0).setCellValue("Month");
		header.createCell(1).setCellValue("Paga");
		header.createCell(2).setCellValue("Paga me leje");

		int rowNum = 7;
		Map<Muajt, MonthsEmployeeRegister> empregisterpermuaj = employeeRegister.getEmpregisterpermuaj();
		for (Map.Entry<Muajt, MonthsEmployeeRegister> entry : empregisterpermuaj.entrySet()) {

			HSSFRow row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(entry.getKey().name());
			row.createCell(1).setCellValue(entry.getValue().getPagapaleje());
			row.createCell(2).setCellValue(entry.getValue().getPagaleje());

		}

	}

}
