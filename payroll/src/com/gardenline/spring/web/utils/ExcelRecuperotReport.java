package com.gardenline.spring.web.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.gardenline.spring.web.dao.Recupero;

public class ExcelRecuperotReport extends AbstractXlsView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition",
				"attachment;filename=\"recuperot.xls\"");
		List<Recupero> recuperot = (List<Recupero>) model.get("recuperot");

		Sheet sheet = workbook.createSheet("AktvPasiv Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Name");
		header.createCell(2).setCellValue("FatherName");
		header.createCell(3).setCellValue("LastName");
		header.createCell(4).setCellValue("SecurityNumber");
		header.createCell(5).setCellValue("Gjendje Recupero");

		int rowNum = 1;
		for (Recupero recupero : recuperot) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(recupero.getId());
			row.createCell(1).setCellValue(
					recupero.getEmployee().getFirstName());
			row.createCell(2).setCellValue(
					recupero.getEmployee().getFatherName());
			row.createCell(3)
					.setCellValue(recupero.getEmployee().getLastName());
			row.createCell(4).setCellValue(
					recupero.getEmployee().getSecurityNumber());
			row.createCell(5).setCellValue(recupero.getGjendjerecupero());

		}
	}

}
