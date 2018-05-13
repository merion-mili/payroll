package com.gardenline.spring.web.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CommonUtils {

	
	public static String getFileExtension(String name){
		if(name.lastIndexOf(".") !=-1 && name.lastIndexOf(".") !=0 ){
			return name.substring(name.lastIndexOf(".")+1);
		}else{
			return "";
		}
	}
	
	
	@SuppressWarnings("resource")
	public static List<String[]> readCsv(String filePath){
		CSVReader dataReader = null;
		List<String[]> datarecords = null;
		
		/*String line = "";
		String splitBy = ",";*/

		
		
		try {
			//buff = new BufferedReader(new FileReader(filePath));
			Reader reader = new BufferedReader(new FileReader(filePath));
			dataReader = new CSVReader(reader, ',', '"', 0);
			datarecords=dataReader.readAll();
			/*while((line = buff.readLine())!=null){
				String[] data = line.split(splitBy);
				datarecords.add(data);
				
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return datarecords;
	}
}
