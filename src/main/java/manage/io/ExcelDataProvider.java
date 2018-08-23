package manage.io;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utils.LogUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.*;

public class ExcelDataProvider {

	private XSSFWorkbook workBook = null;
	private List<XSSFSheet> sheet = new ArrayList<>();
	private XSSFCell cell = null;
	private File file = null;
	private FileInputStream fileStream = null;

	/*
	*  Get data by tab number
	* */
	public ExcelDataProvider(String filePath, int sheetNumber) {
        if (filePath == null && !filePath.equals(""))
            throw new RuntimeException("File Path is invalid!");

		this.file = new File(filePath);
		try {
			this.fileStream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.workBook = new XSSFWorkbook(fileStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.sheet.add(workBook.getSheetAt(sheetNumber));
	}

	/*
	*  Get data by tab name
	* */
	public ExcelDataProvider(String filePath, String... sheetTabName) {
		if (filePath == null && !filePath.equals(""))
			throw new RuntimeException("File Path is invalid!");

		this.file = new File(filePath);
		try {
			this.fileStream = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			this.workBook = new XSSFWorkbook(fileStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		*  Iterate through all provided tab names
		* */
		Map<String, String> tabMap = new HashMap<>();
		if(sheetTabName != null && !sheetTabName.equals(""))
			Arrays.asList(sheetTabName).stream().forEach(state -> {
				tabMap.put(state, state);
			});

		tabMap.forEach((tabName, value) -> {
			     if(tabName !=null && workBook.getSheet(tabName)!= null) {
			     	 this.sheet.add(workBook.getSheet(tabName));
				 }
		});

	}

	/*
	*   Reads test data from excel
	*   and maps the data to provided class
	*   Optionnaly we can provide the method name
	*   and it will return only this method data
	* */
	public <T> Object[][] readValues(Class<T> clazz, String... methodName) {

		Double id = null;
		String testCaseName = null;
		String testDescription = null;
		List<Object> list = new ArrayList<>();
		Map<Object, Object> mapParam = null;

		//try {
			/*
			* Iterate through all provided sheet tab names
			* */
			for (int tab = 0; tab < sheet.size(); tab++) {

				XSSFSheet currentSheet = sheet.get(tab);
				/*
				*  count the duplicate column names
				* */
				Map<String, Integer> duplicateColumnMap = new HashMap<String, Integer>();
				List<String> listString = new ArrayList<String>();
				for (int j = 0; j < currentSheet.getRow(0).getLastCellNum(); j++) {
					String columnName = currentSheet.getRow(0).getCell(j).getStringCellValue();
					if (duplicateColumnMap.get(columnName) == null) {
						duplicateColumnMap.put(columnName, 1);

					} else {
						int count = duplicateColumnMap.get(columnName);
						duplicateColumnMap.put(columnName, ++count);
					}
				}
				/*
				*  Iterate through all rows
				* */
				Row:
				for (int row = 1; row <= currentSheet.getLastRowNum(); row++) {
					// skip if no id
					Cell firstCell = null;
					try {
						firstCell = currentSheet.getRow(row).getCell(0);
						firstCell.getCellTypeEnum();
					} catch(NullPointerException npe) {
						continue Row;
					}

					switch(firstCell.getCellTypeEnum()) {
						case BLANK: continue Row;
						case STRING: if(firstCell.getStringCellValue() == null || firstCell.getStringCellValue().equals("")) continue Row;
					}

					// Filter by method name
					for (String name : methodName) {
						if (name != null && name.length() > 0) {
							if (!name.equalsIgnoreCase(currentSheet.getRow(row).getCell(1).getStringCellValue())) {
								// skip if method name not match
								continue Row;
							}
						}
					}

					// map column name/value
					mapParam = new HashMap<Object, Object>();

					for (int cell = 0; cell < currentSheet.getRow(row).getLastCellNum(); cell++) {
						 /*
						 *   Create cell in case of null value
						 * */
						 try {
							 if (currentSheet.getRow(row).getCell(cell) == null) {
								 currentSheet.getRow(row).createCell(cell, CellType.BLANK);
							 }
						 } catch (Exception e) {
						 	System.err.println("Row: " + row + "\tCell:" + cell + " error: " + e);
						 }

						/*
						*   Map the cell value based on cell type
						*   STRING, BOOLEAN, NUMERIC etc
						* */
						switch (currentSheet.getRow(row).getCell(cell).getCellTypeEnum()) {
							case STRING: {
								String value = currentSheet.getRow(row).getCell(cell).getStringCellValue();
								// null value logic
								if (value.equals("null")) {
									mapParam.put(currentSheet.getRow(0).getCell(cell).getStringCellValue(), null);
								}
								else {
									String columnName = currentSheet.getRow(0).getCell(cell).getStringCellValue().replaceAll(" ", "");
								/*
								* increment the list of duplicates
								* in case we have many coulmns of the same name but different values
								* */
									if (duplicateColumnMap.get(columnName.toLowerCase()) != null && duplicateColumnMap.get(columnName.toLowerCase()) > 1) {
										listString.add(value);
										mapParam.put(columnName.toLowerCase(), listString);
									} else {
										mapParam.put(columnName.toLowerCase(), value);
									}
								}
								break;
							}

							case NUMERIC: {
								try {
									Double value = currentSheet.getRow(row).getCell(cell).getNumericCellValue();
									String columnName = currentSheet.getRow(0).getCell(cell).getStringCellValue();
									mapParam.put(columnName.toLowerCase(), value);
								} catch (Exception e) {
									e.printStackTrace();
								}
								break;
							}

							case BLANK: {
								try {
									mapParam.put(currentSheet.getRow(0).getCell(cell).getStringCellValue().toLowerCase(), "");
								} catch (Exception e) {
									e.printStackTrace();
								}
								break;
							}

							case BOOLEAN: {
								try {
									Boolean value = new Boolean(currentSheet.getRow(row).getCell(cell).getBooleanCellValue());
									String columnName = currentSheet.getRow(0).getCell(cell).getStringCellValue();
									mapParam.put(columnName.toLowerCase(), value);
								} catch (Exception e) {
									e.printStackTrace();
								}
								break;
							}

							default:
								throw new RuntimeException("CellEnum is not handled: " + currentSheet.getRow(row).getCell(cell).getCellTypeEnum());
						}
					}
					/*
					*   instantiate the class
					*   and assign values to fields
					* */
					Object newInstance = null;
					try {
						newInstance = clazz.newInstance();
					} catch (InstantiationException e) {
						LogUtils.error(e.getMessage());
					} catch (IllegalAccessException e) {
						LogUtils.error(e.getMessage());
					}

					Field[] fields = clazz.getDeclaredFields();
					String fieldName = null;
					for (int i = 0; i < fields.length; i++) {
						fieldName = fields[i].getName();
						if (mapParam.get(fieldName.toLowerCase()) != null) {
							try {
								fields[i].setAccessible(true);
								Object cellValue = mapParam.get(fieldName.toLowerCase());

								// in case of data type mismatch e.g Numeric value is "" as string
								if (!fields[i].getType().equals(cellValue.getClass())) {
									if (fields[i].getType().equals(String.class) && mapParam.get(fieldName.toLowerCase()) instanceof Double) {
										cellValue = NumberToTextConverter.toText((Double) mapParam.get(fieldName.toLowerCase()));
									}
								}
								fields[i].set(newInstance, cellValue);
							} catch (Exception e) {
								LogUtils.error(e.getMessage());
							}
						}
					}
					list.add(newInstance);
				}
			}// end of iterate through tab names

		return toArray(list);
	}

	/*
	*  Reads column values as a string,
	*  @columnName
	*  @format - converts numeric value to string with format e.g "00000"
	*  returns List<String>
	* */
	public List<String> readColumnValues( String columnName, String format) {

		List<String> columnValues = new ArrayList<>();

		XSSFSheet currentSheet = sheet.get(0);
		Row currentRow = currentSheet.getRow(0);
		int lastCellNum = currentSheet.getRow(0).getLastCellNum();
		int lastRowNum = currentSheet.getLastRowNum();
		int expectedColumnNum = 0;

		/*
		 * Iterate through all provided sheet tab names
		 * */
		for (int tab = 0; tab < sheet.size(); tab++) {
			currentSheet = sheet.get(tab);
			currentRow = currentSheet.getRow(0);
			lastCellNum = currentSheet.getRow(0).getLastCellNum();
			lastRowNum = currentSheet.getLastRowNum();
			expectedColumnNum = 0;

			for (int i = 0; i < lastCellNum; i++) {
				if (currentRow.getCell(i).getStringCellValue().equalsIgnoreCase(columnName))
					expectedColumnNum = i;
			}
			// iterate through the rows
			for (int rowNum = 1; rowNum < lastRowNum; rowNum++) {
				currentRow = currentSheet.getRow(rowNum);
				Cell currentCell = currentRow.getCell(expectedColumnNum);
				String cellValue = null;
				switch (currentCell.getCellTypeEnum()) {
					case NUMERIC:
						cellValue = new DecimalFormat(format).format(currentCell.getNumericCellValue());
						break;
					case STRING:
						cellValue = ((String) currentCell.getStringCellValue());
						break;
					case BOOLEAN:
						cellValue = ((Boolean) currentCell.getBooleanCellValue()).toString();
						break;
				}

				columnValues.add(cellValue);
			}
		}
		return columnValues;
	}

	public <T>Object[][] toArray(List<T> list) {
		Object[][] objArray = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			objArray[i] = new Object[1];
			objArray[i][0] = list.get(i);
		}
		return objArray;
	}
}