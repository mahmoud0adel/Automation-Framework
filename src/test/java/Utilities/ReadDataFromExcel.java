package Utilities;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromExcel {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFRow row;
	private XSSFCell cell;
	private String testDataFulePath;
	private String sheetNameOfTheData;

	// Excel sheet name
	public String TestDataFileName;

	// ##First Constructor Without Sheet name ##if the sheet name not provided the
	// sheet will be first sheet always
	public ReadDataFromExcel(String TestDataFileName) {
		// Test Data file path
		testDataFulePath = System.getProperty("user.dir"); // get the project pathx

		try {
			// if we gonna change the folder or the test data file name we have to change it
			// here also /Excel/TestData.xlsx
			workbook = new XSSFWorkbook(testDataFulePath + "/" + TestDataFileName + ".xlsx");// "/TestData.xlsx"
			sheetNameOfTheData = workbook.getSheetName(0);
			sheet = workbook.getSheet(sheetNameOfTheData);
			// here we try to geet the sheet name from code in constructor
			System.out.println("you are getting data from sheet  : " + sheetNameOfTheData);
			// now when we create new object the sheet name will come from the index 0 first
			// sheet name !!
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

	// main ok method with TestDataName And columnName
	public String getExcelDataByeRowNOAndColumnNO(int rowNo, int columnNo) {// String TestDataName,String columnName
																			// String
		// will get by column number first then convert to row name
		try {
			// ## here we try to get the sheet name from code in constructor look at the
			// constructor !! ##
			
			row = sheet.getRow(rowNo);// here will get the row number by number
			// the column number maybe device data name or environment name
			cell = row.getCell(columnNo);// here will provide the column number by name

			// this the step where we get the specific test data we need from the excel
			// file
			String testDataNeeded = getTheDataFromTheExcelByColumn(cell);
			return testDataNeeded;
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
			return "No value Matched from you data file";
		}

	}

	// getting back restul with test data needed depend on it's type
	private String getTheDataFromTheExcelByColumn(XSSFCell Cell) {

		if (Cell.getCellType() == CellType.STRING) {
			// return cell.getStringCellValue();
		// System.out.println(Cell.getStringCellValue());//we use when the method is
			// void
			return cell.getStringCellValue();
		} else if (Cell.getCellType() == CellType.NUMERIC || Cell.getCellType() == CellType.FORMULA) {
			// convert the double to int to remove .0
			int convert = (int) Cell.getNumericCellValue();
			String cellValueToString = String.valueOf(convert);
			// String cellValueToString = String.valueOf(Cell.getNumericCellValue());
			if (HSSFDateUtil.isCellDateFormatted(Cell)) {
				DateFormat dt = new SimpleDateFormat("dd/MM/yy");
				Date date = (Date) Cell.getDateCellValue();
				cellValueToString = dt.format(date);
			}

			// return cellValueToString;
			return cellValueToString;
		}

		else if (Cell.getCellType() == CellType.BLANK) {

			return "Blank";
		} else {
			// we use when the method is void
			return String.valueOf(cell.getBooleanCellValue());
		}

	}

}
