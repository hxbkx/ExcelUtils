/*
 * Copyright 2003-2005 ExcelUtils http://excelutils.sourceforge.net
 * Created on 2005-6-18
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.excelutils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <p>
 * <b>WorkbookUtils </b>is a helper of Microsoft Excel,it's based on POI project
 * </p>
 * 
 * @author rainsoft
 * @version $Revision: 1.10 $ $Date: 2005/10/28 00:54:01 $
 */
public class WorkbookUtils {

	public WorkbookUtils() {
	}

	/**
	 * Open Excel File
	 * 
	 * @param ctx ServletContext
	 * @param config Excel Template Name
	 * @throws ExcelException
	 * @return Workbook
	 */
	public static Workbook openWorkbook(ServletContext ctx, String config) throws ExcelException {

		InputStream in = null;
		Workbook wb = null;
		try {
			in = ctx.getResourceAsStream(config);
			wb = new XSSFWorkbook(in);
		} catch (Exception e) {
			throw new ExcelException("File" + config + "not found," + e.getMessage());
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
		return wb;
	}
	
	/**
	 * Open an excel file by real fileName
	 * @param fileName
	 * @return Workbook
	 * @throws ExcelException
	 */
	public static Workbook openWorkbook(String fileName) throws ExcelException {
		InputStream in = null;
		Workbook wb = null;
		try {
			in = new FileInputStream(fileName);
			wb = new XSSFWorkbook(in);
		} catch (Exception e) {
			throw new ExcelException("File" + fileName + "not found" + e.getMessage());
		} finally {
			try {
				in.close();
			} catch (Exception e) {				
			}
		}
		return wb;
	}
	
	/**
	 * Open an excel from InputStream
	 * @param in
	 * @return��Workbook
	 * @throws ExcelException
	 */
	public static Workbook openWorkbook(InputStream in) throws ExcelException {
		Workbook wb = null;
		try {
			wb = new XSSFWorkbook(in);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
		return wb;
	}

	/**
	 * Save the Excel to OutputStream
	 * 
	 * @param wb Workbook
	 * @param out OutputStream
	 * @throws ExcelException
	 */
	public static void SaveWorkbook(Workbook wb, OutputStream out) throws ExcelException {
		try {
			wb.write(out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * Set value of the cell
	 * 
	 * @param sheet Sheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value String
	 */
	public static void setCellValue(Sheet sheet, int rowNum, int colNum, String value) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		cell.setCellValue(value);
	}

	/**
	 * get value of the cell
	 * 
	 * @param sheet Sheet
	 * @param rowNum int
	 * @param colNum int
	 * @return String
	 */
	public static String getStringCellValue(Sheet sheet, int rowNum, int colNum) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		return cell.getStringCellValue();
	}

	/**
	 * set value of the cell
	 * 
	 * @param sheet Sheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value String
	 * @param encoding short
	 */
	public static void setCellValue(Sheet sheet, int rowNum, int colNum, String value, short encoding) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		cell.setCellValue(value);
	}

	/**
	 * set value of the cell
	 * 
	 * @param sheet Sheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value double
	 */
	public static void setCellValue(Sheet sheet, int rowNum, int colNum, double value) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		cell.setCellValue(value);
	}

	/**
	 * get value of the cell
	 * 
	 * @param sheet Sheet
	 * @param rowNum int
	 * @param colNum int
	 * @return double
	 */
	public static double getNumericCellValue(Sheet sheet, int rowNum, int colNum) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		return cell.getNumericCellValue();
	}

	/**
	 * set value of the cell
	 * 
	 * @param sheet Sheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value Date
	 */
	public static void setCellValue(Sheet sheet, int rowNum, int colNum, Date value) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		cell.setCellValue(value);
	}

	/**
	 * get value of the cell
	 * 
	 * @param sheet Sheet
	 * @param rowNum int
	 * @param colNum int
	 * @return Date
	 */
	public static Date getDateCellValue(Sheet sheet, int rowNum, int colNum) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		return cell.getDateCellValue();
	}

	/**
	 * set value of the cell
	 * 
	 * @param sheet Sheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value boolean
	 */
	public static void setCellValue(Sheet sheet, int rowNum, int colNum, boolean value) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		cell.setCellValue(value);
	}

	/**
	 * get value of the cell
	 * 
	 * @param sheet
	 * @param rowNum
	 * @param colNum
	 * @return boolean value
	 */
	public static boolean getBooleanCellValue(Sheet sheet, int rowNum, int colNum) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		return cell.getBooleanCellValue();
	}

	/**
	 * get Row, if not exists, create
	 * 
	 * @param rowCounter int
	 * @param sheet Sheet
	 * @return HSSFRow
	 */
	public static Row getRow(int rowCounter, Sheet sheet) {
		Row row = sheet.getRow((short) rowCounter);
		if (row == null) {
			row = sheet.createRow((short) rowCounter);
		}
		return row;
	}

	/**
	 * get Cell, if not exists, create
	 * 
	 * @param row HSSFRow
	 * @param column int
	 * @return Cell
	 */
	public static Cell getCell(Row row, int column) {
		Cell cell = row.getCell((short) column);

		if (cell == null) {
			cell = row.createCell((short) column);
		}
		return cell;
	}

	/**
	 * get cell, if not exists, create
	 * 
	 * @param sheet Sheet
	 * @param rowNum int
	 * @param colNum int
	 * @return Cell
	 */
	public static Cell getCell(Sheet sheet, int rowNum, int colNum) {
		Row row = getRow(rowNum, sheet);
		Cell cell = getCell(row, colNum);
		return cell;
	}

	/**
	 * copy row
	 * 
	 * @param sheet
	 * @param from begin of the row
	 * @param to destination fo the row
	 * @param count count of copy
	 */
	public static void copyRow(Sheet sheet, int from, int to, int count) {

		for (int rownum = from; rownum < from + count; rownum++) {
			Row fromRow = sheet.getRow(rownum);
			Row toRow = getRow(to + rownum - from, sheet);
			if (null == fromRow)
				return;
			toRow.setHeight(fromRow.getHeight());
			toRow.setHeightInPoints(fromRow.getHeightInPoints());
			for (int i = fromRow.getFirstCellNum(); i <= fromRow.getLastCellNum() && i >= 0; i++) {
				Cell fromCell = getCell(fromRow, i);
				Cell toCell = getCell(toRow, i);
				toCell.setCellStyle(fromCell.getCellStyle());
				toCell.setCellType(fromCell.getCellType());
				switch (fromCell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					toCell.setCellValue(fromCell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					toCell.setCellFormula(fromCell.getCellFormula());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					toCell.setCellValue(fromCell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:
					toCell.setCellValue(fromCell.getStringCellValue());
					break;
				default:
				}
			}
		}

		// copy merged region
		List shiftedRegions = new ArrayList();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			CellRangeAddress r = sheet.getMergedRegion(i);
			if (r.getFirstRow() >= from && r.getLastRow() < from + count) {
			    CellRangeAddress n_r = new CellRangeAddress(r.getFirstRow() + to - from,r.getLastRow() + to - from,r.getFirstColumn(),r.getLastColumn());
				shiftedRegions.add(n_r);				
			}
		}
		
		// readd so it doesn't get shifted again
		Iterator iterator = shiftedRegions.iterator();
		while (iterator.hasNext()) {
		    CellRangeAddress region = (CellRangeAddress) iterator.next();
			sheet.addMergedRegion(region);
		}		
	}

	public static void shiftCell(Sheet sheet, Row row, Cell beginCell, int shift, int rowCount) {

		if (shift == 0)
			return;

		// get the from & to row
		int fromRow = row.getRowNum();
		int toRow = row.getRowNum()+rowCount-1;
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
		    CellRangeAddress r = sheet.getMergedRegion(i);
			if (r.getFirstRow() == row.getRowNum()) {
				if (r.getLastRow() > toRow) {
					toRow = r.getLastRow();
				}
				if (r.getFirstRow() < fromRow) {
					fromRow = r.getFirstRow();
				}
			}
		}

		for (int rownum = fromRow; rownum <= toRow; rownum++) {
			Row curRow = WorkbookUtils.getRow(rownum, sheet);
			int lastCellNum = curRow.getLastCellNum();		
			for (int cellpos = lastCellNum; cellpos >= beginCell.getColumnIndex(); cellpos--) {
				Cell fromCell = WorkbookUtils.getCell(curRow, cellpos);
				Cell toCell = WorkbookUtils.getCell(curRow, cellpos + shift);
				toCell.setCellType(fromCell.getCellType());
				toCell.setCellStyle(fromCell.getCellStyle());
				switch (fromCell.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					toCell.setCellValue(fromCell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_FORMULA:
					toCell.setCellFormula(fromCell.getCellFormula());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					toCell.setCellValue(fromCell.getNumericCellValue());
					break;
				case Cell.CELL_TYPE_STRING:
					toCell.setCellValue(fromCell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_ERROR:
					toCell.setCellErrorValue(fromCell.getErrorCellValue());
					break;
				}
				fromCell.setCellValue("");
				fromCell.setCellType(Cell.CELL_TYPE_BLANK);
				Workbook wb = new XSSFWorkbook();
				CellStyle style = wb.createCellStyle();
				fromCell.setCellStyle(style);
			}
			
			// process merged region
			for (int cellpos = lastCellNum; cellpos >= beginCell.getColumnIndex(); cellpos--) {
				Cell fromCell = WorkbookUtils.getCell(curRow, cellpos);
				
				List shiftedRegions = new ArrayList();
				for (int i=0; i<sheet.getNumMergedRegions(); i++) {
				    CellRangeAddress r = sheet.getMergedRegion(i);
					if (r.getFirstRow()==curRow.getRowNum() && r.getFirstColumn() == fromCell.getColumnIndex()) {
						r.setFirstColumn((short) (r.getFirstColumn() + shift));
						r.setLastColumn((short) (r.getLastColumn() + shift));
						// have to remove/add it back
						shiftedRegions.add(r);
						sheet.removeMergedRegion(i);
						// we have to back up now since we removed one
						i = i - 1;
					}
				}
				
				// readd so it doesn't get shifted again
				Iterator iterator = shiftedRegions.iterator();
				while (iterator.hasNext()) {
				    CellRangeAddress region = (CellRangeAddress) iterator.next();
					sheet.addMergedRegion(region);
				}					
			}			
		}
	}
}
