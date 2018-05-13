package com.gardenline.spring.web.utils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.gardenline.spring.web.dao.EmployeeBank;
import com.gardenline.spring.web.dao.EmployeeSalaryStanding;

public class ExcelMonthSalaryReport extends AbstractXlsView{
	
	

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setHeader("Content-Disposition",
				"attachment;filename=\"monthlysalary.xls\"");
		List<EmployeeSalaryStanding> standings = (List<EmployeeSalaryStanding>) model.get("standing");
		
		List<EmployeeBank> empbanks = (List<EmployeeBank>) model.get("empbanks");
		Sheet sheet = workbook.createSheet("Salary Data");
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Firstname");
		header.createCell(1).setCellValue("Lastname");
		header.createCell(2).setCellValue("SecurityNumber");
		header.createCell(3).setCellValue("Kontrata");
		header.createCell(4).setCellValue("Bruto");
		header.createCell(5).setCellValue("Bonus");
		header.createCell(6).setCellValue("BankAmount");
		header.createCell(7).setCellValue("CashAmount");
		header.createCell(8).setCellValue("Sigurimet");
		header.createCell(9).setCellValue("Taksat");
		header.createCell(10).setCellValue("Paganeto");
		header.createCell(11).setCellValue("Bank");
		header.createCell(12).setCellValue("Account");
		

		int rowNum = 1;
		for (EmployeeSalaryStanding standing : standings) {
			Row row = sheet.createRow(rowNum++);
			String value= "";
			String value1= "";
			
			row.createCell(0).setCellValue(standing.getFirstName());
			row.createCell(1).setCellValue(standing.getLastName());
			row.createCell(2).setCellValue(standing.getSn());
			row.createCell(3).setCellValue(standing.getPagabaze());
			row.createCell(4).setCellValue(standing.getBrutoSalary());
			row.createCell(5).setCellValue(standing.getBonous());
			row.createCell(6).setCellValue(standing.getBankAmount());
			row.createCell(7).setCellValue(standing.getCashAmount());
			row.createCell(8).setCellValue(standing.getSigurimet());
			row.createCell(9).setCellValue(standing.getTaksat());
			row.createCell(10).setCellValue(standing.getPaganeto());
		
		
			List<EmployeeBank> collects = empbanks.stream().filter(s->s.getEmployee().
					getSecurityNumber().equalsIgnoreCase(standing.getSn())).collect(Collectors.toList());
			for (EmployeeBank employeeBank : collects) {
				value = value +  employeeBank.getBank().getBankName()+" " ;
				value1 = value1+ employeeBank.getBankAccount()+ " " ;
			}
			
			row.createCell(11).setCellValue(value);
			row.createCell(12).setCellValue(value1);
		}
	}

}
