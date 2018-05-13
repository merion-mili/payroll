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

import com.gardenline.spring.web.dao.EmployeeSalary;

public class ExcelEmployeeSalariesReport extends AbstractXlsView {

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
				"attachment;filename=\"employeesalaries.xls\"");
		List<EmployeeSalary> empSalaries = (List<EmployeeSalary>) model.get("empSalaries");

		Sheet sheet = workbook.createSheet("EmployeeJob Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Name");
		header.createCell(2).setCellValue("FatherName");
		header.createCell(3).setCellValue("LastName");
		header.createCell(4).setCellValue("SecurityNumber");
		header.createCell(5).setCellValue("PagaKontrate");
		header.createCell(6).setCellValue("PagahourKontrate");
		header.createCell(7).setCellValue("PagaKantier");
		header.createCell(8).setCellValue("Total Kantier");
		header.createCell(9).setCellValue("Paga Sera");
		header.createCell(10).setCellValue("Total Sera");
		header.createCell(11).setCellValue("Start Date");
		header.createCell(12).setCellValue("End Date");
		
		int rowNum = 1;
		for (EmployeeSalary empSalary : empSalaries) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(empSalary.getId());
			row.createCell(1).setCellValue(empSalary.getEmployee().getFirstName());
			row.createCell(2)
					.setCellValue(empSalary.getEmployee().getFatherName());
			row.createCell(3).setCellValue(empSalary.getEmployee().getLastName());
			row.createCell(4).setCellValue(
					empSalary.getEmployee().getSecurityNumber());
			
			row.createCell(5).setCellValue(empSalary.getPagakontrate());
			row.createCell(6).setCellValue(empSalary.getPagahourkontrate());
			row.createCell(7).setCellValue(empSalary.getPagakantier());
			row.createCell(8).setCellValue(empSalary.getTotalkantier());
			row.createCell(9).setCellValue(empSalary.getPagasera());
			row.createCell(10).setCellValue(empSalary.getTotalsera());
			row.createCell(11).setCellValue(formatDate(empSalary.getStartDate()));
			row.createCell(12).setCellValue(formatDate(empSalary.getEndDate()));
			

		}
	}

}
