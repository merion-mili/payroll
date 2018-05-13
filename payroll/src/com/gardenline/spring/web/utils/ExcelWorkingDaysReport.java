package com.gardenline.spring.web.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.gardenline.spring.web.dao.EmployeeSalaryStanding;

public class ExcelWorkingDaysReport extends AbstractXlsView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition",
				"attachment;filename=\"workingdays.xls\"");
		List<EmployeeSalaryStanding> standings = (List<EmployeeSalaryStanding>) model
				.get("standing");

		Sheet sheet = workbook.createSheet("ListPresence Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Firstname");
		header.createCell(1).setCellValue("Lastname");
		header.createCell(2).setCellValue("SecurityNumber");
		header.createCell(3).setCellValue("Pune");
		header.createCell(4).setCellValue("Festat");
		header.createCell(5).setCellValue("Shtese Sera");
		header.createCell(6).setCellValue("Lejet Ditore");
		header.createCell(7).setCellValue("Raportet");
		header.createCell(8).setCellValue("Leja Vjetore");
		header.createCell(9).setCellValue("Total recupero");
		header.createCell(10).setCellValue("Gjendje Recupero");
		header.createCell(11).setCellValue("Neto Cash");

		int rowNum = 1;
		for (EmployeeSalaryStanding standing : standings) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(standing.getFirstName());
			row.createCell(1).setCellValue(standing.getLastName());
			row.createCell(2).setCellValue(standing.getSn());
			row.createCell(3).setCellValue(standing.getDitetEPunuara());
			row.createCell(4).setCellValue(standing.getDitetEfestave());
			row.createCell(5).setCellValue(standing.getTotaloretshteseSera());
			row.createCell(6).setCellValue(standing.getTotalLejeDitore());
			row.createCell(7).setCellValue(standing.getTotalRaportet());
			row.createCell(8).setCellValue(standing.getDitetLejeVjetore());
			row.createCell(9).setCellValue(standing.getTotalRecupero());
			row.createCell(10).setCellValue(standing.getGjendjaRecupero());
			row.createCell(11).setCellValue(standing.getTotalNetoCash());

		}
	}

}
