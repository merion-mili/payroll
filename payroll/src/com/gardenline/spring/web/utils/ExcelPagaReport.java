package com.gardenline.spring.web.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.gardenline.spring.web.dao.Paga;

public class ExcelPagaReport extends AbstractXlsView {

	

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition",
				"attachment;filename=\"presenca.xls\"");
		List<Paga> pagat = (List<Paga>) model.get("pagat");

		Sheet sheet = workbook.createSheet("ListPresence Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Date");
		header.createCell(2).setCellValue("Name");
		header.createCell(3).setCellValue("FatherName");
		header.createCell(4).setCellValue("LastName");
		header.createCell(5).setCellValue("SecurityNumber");
		header.createCell(6).setCellValue("Kantier");
		header.createCell(7).setCellValue("Pune");
		header.createCell(8).setCellValue("Oret Shtese");
		header.createCell(9).setCellValue("Raportet");
		header.createCell(10).setCellValue("Lejet Ditore");
		header.createCell(11).setCellValue("Festat");
		header.createCell(12).setCellValue("Leja Vjetore");
		header.createCell(13).setCellValue("Recupero");
		header.createCell(14).setCellValue("Total recupero");
		header.createCell(15).setCellValue("Gjendje Recupero");
		header.createCell(16).setCellValue("Comment");

		int rowNum = 1;
		for (Paga paga : pagat) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(paga.getId());
			row.createCell(1).setCellValue(paga.getDate());
			row.createCell(2).setCellValue(paga.getRecuperoEmployee().getEmployee().getFirstName());
			row.createCell(3).setCellValue(paga.getRecuperoEmployee().getEmployee().getFatherName());
			row.createCell(4).setCellValue(paga.getRecuperoEmployee().getEmployee().getLastName());
			row.createCell(5).setCellValue(paga.getRecuperoEmployee().getEmployee().getSecurityNumber());
			row.createCell(6).setCellValue(paga.getKantier().getKantierName());
			row.createCell(7).setCellValue(paga.getOretepunuara());
			row.createCell(8).setCellValue(paga.getOretshtese());
			row.createCell(9).setCellValue(paga.getRaportet());
			row.createCell(10).setCellValue(paga.getLejeDitore());
			row.createCell(11).setCellValue(paga.getFesta());
			row.createCell(12).setCellValue(paga.getLejeVjetore());
			row.createCell(13).setCellValue(paga.getRecupero());
			row.createCell(14).setCellValue(paga.getTotalirecupero());
			row.createCell(15).setCellValue(paga.getRecuperoEmployee().getGjendjerecupero());
			row.createCell(16).setCellValue(paga.getComment());
			
			
		}
	}

}
