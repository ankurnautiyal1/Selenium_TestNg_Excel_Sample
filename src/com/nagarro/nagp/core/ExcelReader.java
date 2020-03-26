package com.nagarro.nagp.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nagarro.nagp.enums.WebElements;

/* ExcelReader class will fetch the element information
 * from the excel file named "WebElement.xlsx"
 */

public class ExcelReader {

	static Row row;
	static Workbook workbook;
	static Sheet sheet;
	static FileInputStream fileInputStream;
	
	public static void readExcel() throws IOException {
		
		String cwd = System.getProperty("user.dir");
		String excelFile = "\\src\\com\\nagarro\\nagp\\resources\\WebElements.xlsx";
		fileInputStream = new FileInputStream(new File(cwd+excelFile));
		
		workbook = new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheetAt(0);
		
		
	}
	
	/* getXpath method returns the xpath fetched from the WebElement.xlsx file
	 * and return as a string.
	 */
	public static String getXpath(WebElements element) throws IOException {
		readExcel();
		
		int rowCount = sheet.getLastRowNum();
		
		for(int rowIndexer = 0 ;rowIndexer < rowCount;++rowIndexer) {
			row = sheet.getRow(rowIndexer+1);
			
			if(row.getCell(0).getStringCellValue().compareTo(element.toString())==0) {
				return row.getCell(1).getStringCellValue();
			}
		}
		
		
		return null;
	}
	
	protected void finalize() throws IOException{
		row = null;
		workbook.close();
		sheet = null;
		fileInputStream.close();;
	}
	
}
