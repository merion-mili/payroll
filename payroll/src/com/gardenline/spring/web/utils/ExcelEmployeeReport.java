package com.gardenline.spring.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.gardenline.spring.web.dao.Employee;

public class ExcelEmployeeReport extends AbstractXlsView {

	private String formatDate(Date date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dateInString = "01/01/1000";

		Calendar calendar1 = Calendar.getInstance();

		if (date == null) {

			calendar1.setTime(sdf.parse(dateInString));

		} else {

			calendar1.setTime(date);
		}

		String dateFormated = sdf.format(calendar1.getTime());
		return dateFormated;
	}


	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition",
				"attachment;filename=\"employee.xls\"");
		List<Employee> employees = (List<Employee>) model.get("emps");
	

		Sheet sheet = workbook.createSheet("Employee Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Name");
		header.createCell(2).setCellValue("FatherName");
		header.createCell(3).setCellValue("LastName");
		header.createCell(4).setCellValue("SecurityNumber");
		header.createCell(5).setCellValue("DOB");
		header.createCell(6).setCellValue("Address");
		header.createCell(7).setCellValue("Telephone");
		header.createCell(8).setCellValue("Pervoja");
		header.createCell(9).setCellValue("Kontratedate");
	

		int rowNum = 1;
		for (Employee employee : employees) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(employee.getEmpId());
			row.createCell(1).setCellValue(employee.getFirstName());
			row.createCell(2)
					.setCellValue(employee.getFatherName());
			row.createCell(3).setCellValue(employee.getLastName());
			row.createCell(4).setCellValue(
					employee.getSecurityNumber());
			row.createCell(5).setCellValue(formatDate(employee.getBirsthDate()));
			row.createCell(6).setCellValue(employee.getAddress());
			row.createCell(7).setCellValue(employee.getTelephone());
			row.createCell(8).setCellValue(employee.getPervoja());
			row.createCell(9).setCellValue(formatDate(employee.getKontrateDate()));
			

			
		}
	}

}
