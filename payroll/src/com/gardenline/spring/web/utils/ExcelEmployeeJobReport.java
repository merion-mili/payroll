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

import com.gardenline.spring.web.dao.EmployeeJob;

public class ExcelEmployeeJobReport extends AbstractXlsView {

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
				"attachment;filename=\"employeejob.xls\"");
		List<EmployeeJob> empJobs = (List<EmployeeJob>) model.get("empJobs");

		Sheet sheet = workbook.createSheet("EmployeeJob Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Name");
		header.createCell(2).setCellValue("FatherName");
		header.createCell(3).setCellValue("LastName");
		header.createCell(4).setCellValue("SecurityNumber");
		header.createCell(5).setCellValue("Job");
		header.createCell(6).setCellValue("Start Date");
		header.createCell(7).setCellValue("End Date");

		int rowNum = 1;
		for (EmployeeJob empjob : empJobs) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(empjob.getId());
			row.createCell(1).setCellValue(empjob.getEmployee().getFirstName());
			row.createCell(2)
					.setCellValue(empjob.getEmployee().getFatherName());
			row.createCell(3).setCellValue(empjob.getEmployee().getLastName());
			row.createCell(4).setCellValue(
					empjob.getEmployee().getSecurityNumber());
			row.createCell(5).setCellValue(empjob.getJob().getJobDescription());
			row.createCell(6).setCellValue(formatDate(empjob.getStartDate()));
			row.createCell(7).setCellValue(formatDate(empjob.getEndDate()));

		}
	}

}
