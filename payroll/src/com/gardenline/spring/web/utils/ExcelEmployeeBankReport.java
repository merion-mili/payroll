package com.gardenline.spring.web.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.gardenline.spring.web.dao.EmployeeBank;

public class ExcelEmployeeBankReport extends AbstractXlsView {

	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition",
				"attachment;filename=\"employeebank.xls\"");
		List<EmployeeBank> empBanks = (List<EmployeeBank>) model.get("empBanks");

		Sheet sheet = workbook.createSheet("EmployeeJob Banks");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("ID");
		header.createCell(1).setCellValue("Name");
		header.createCell(2).setCellValue("FatherName");
		header.createCell(3).setCellValue("LastName");
		header.createCell(4).setCellValue("SecurityNumber");
		header.createCell(5).setCellValue("Bank Name");
		header.createCell(6).setCellValue("Bank Account");
		
		int rowNum = 1;
		for (EmployeeBank empBank : empBanks) {
			Row row = sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(empBank.getId());
			row.createCell(1).setCellValue(empBank.getEmployee().getFirstName());
			row.createCell(2)
					.setCellValue(empBank.getEmployee().getFatherName());
			row.createCell(3).setCellValue(empBank.getEmployee().getLastName());
			row.createCell(4).setCellValue(
					empBank.getEmployee().getSecurityNumber());
			row.createCell(5).setCellValue(empBank.getBank().getBankName());
			row.createCell(6).setCellValue(empBank.getBankAccount());
			
		}
	}

}
