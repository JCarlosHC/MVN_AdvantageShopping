package advantageShopping.support;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Copyright: Softtek. Description: In this file contains the utils function for
 * excel
 * 
 * @author Juan Carlos Hernández Castro<jc.hernandez>
 *
 */

public class ExcelPropertyLoader {
	private Dictionary<String, String> dic;

	// Read excel file and set it to dic variable
	public void LoadFile(String rutaArchivoExcel) {
		try {
			FileInputStream inputStream = new FileInputStream(new File(rutaArchivoExcel));
			Workbook workbook = new XSSFWorkbook(inputStream);
			Sheet firstSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = firstSheet.iterator();
			DataFormatter formatter = new DataFormatter();

			dic = new Hashtable<String, String>();
			String key = "";
			String value = "";
			while (iterator.hasNext()) {
				Row nextRow = (Row) iterator.next();
				key = nextRow.getCell(0).getStringCellValue();
				value = formatter.formatCellValue(nextRow.getCell(1));
				dic.put(key, value);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Read the specified sheet and return an object to use it as data provider
	public String[][] getExcelData(String fileName, String sheetName, int numberOfColumns) {

		String[][] data = null;
		try {
			DataFormatter formatter = new DataFormatter();
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			XSSFRow row = sh.getRow(0);
			int noOfRows = sh.getLastRowNum() - 1;
			Cell cell;
			data = new String[noOfRows - 1][numberOfColumns];
			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < numberOfColumns; j++) {
					row = sh.getRow(i);
					cell = row.getCell(j);
					data[i - 1][j] = formatter.formatCellValue(cell);
				}
			}
			wb.close();
			fis.close();
		} catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		return data;
	}

	public String getValue(String key) {
		return dic.get(key);
	}
}