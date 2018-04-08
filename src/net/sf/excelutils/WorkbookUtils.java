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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

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
	 * @return HSSFWorkbook
	 */
	public static HSSFWorkbook openWorkbook(ServletContext ctx, String config) throws ExcelException {

		InputStream in = null;
		HSSFWorkbook wb = null;
		try {
			in = ctx.getResourceAsStream(config);
			wb = new HSSFWorkbook(in);
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
	 * @return HSSFWorkbook
	 * @throws ExcelException
	 */
	public static HSSFWorkbook openWorkbook(String fileName) throws ExcelException {
		InputStream in = null;
		HSSFWorkbook wb = null;
		try {
			in = new FileInputStream(fileName);
			wb = new HSSFWorkbook(in);
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
	 * @return¡¡HSSFWorkbook
	 * @throws ExcelException
	 */
	public static HSSFWorkbook openWorkbook(InputStream in) throws ExcelException {
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(in);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
		return wb;
	}

	/**
	 * Save the Excel to OutputStream
	 * 
	 * @param wb HSSFWorkbook
	 * @param out OutputStream
	 * @throws ExcelException
	 */
	public static void SaveWorkbook(HSSFWorkbook wb, OutputStream out) throws ExcelException {
		try {
			wb.write(out);
		} catch (Exception e) {
			throw new ExcelException(e.getMessage());
		}
	}

	/**
	 * Set value of the cell
	 * 
	 * @param sheet HSSFSheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value String
	 */
	public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, String value) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
		cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
		cell.setCellValue(value);
	}

	/**
	 * get value of the cell
	 * 
	 * @param sheet HSSFSheet
	 * @param rowNum int
	 * @param colNum int
	 * @return String
	 */
	public static String getStringCellValue(HSSFSheet sheet, int rowNum, int colNum) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
		return cell.getStringCellValue();
	}

	/**
	 * set value of the cell
	 * 
	 * @param sheet HSSFSheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value String
	 * @param encoding short
	 */
	public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, String value, short encoding) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
		if (encoding >= 0) {
			cell.setEncoding(encoding);
		}
		cell.setCellValue(value);
	}

	/**
	 * set value of the cell
	 * 
	 * @param sheet HSSFSheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value double
	 */
	public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, double value) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
		cell.setCellValue(value);
	}

	/**
	 * get value of the cell
	 * 
	 * @param sheet HSSFSheet
	 * @param rowNum int
	 * @param colNum int
	 * @return double
	 */
	public static double getNumericCellValue(HSSFSheet sheet, int rowNum, int colNum) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
		return cell.getNumericCellValue();
	}

	/**
	 * set value of the cell
	 * 
	 * @param sheet HSSFSheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value Date
	 */
	public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, Date value) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
		cell.setCellValue(value);
	}

	/**
	 * get value of the cell
	 * 
	 * @param sheet HSSFSheet
	 * @param rowNum int
	 * @param colNum int
	 * @return Date
	 */
	public static Date getDateCellValue(HSSFSheet sheet, int rowNum, int colNum) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
		return cell.getDateCellValue();
	}

	/**
	 * set value of the cell
	 * 
	 * @param sheet HSSFSheet
	 * @param rowNum int
	 * @param colNum int
	 * @param value boolean
	 */
	public static void setCellValue(HSSFSheet sheet, int rowNum, int colNum, boolean value) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
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
	public static boolean getBooleanCellValue(HSSFSheet sheet, int rowNum, int colNum) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
		return cell.getBooleanCellValue();
	}

	/**
	 * get Row, if not exists, create
	 * 
	 * @param rowCounter int
	 * @param sheet HSSFSheet
	 * @return HSSFRow
	 */
	public static HSSFRow getRow(int rowCounter, HSSFSheet sheet) {
		HSSFRow row = sheet.getRow((short) rowCounter);
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
	 * @return HSSFCell
	 */
	public static HSSFCell getCell(HSSFRow row, int column) {
		HSSFCell cell = row.getCell((short) column);

		if (cell == null) {
			cell = row.createCell((short) column);
		}
		return cell;
	}

	/**
	 * get cell, if not exists, create
	 * 
	 * @param sheet HSSFSheet
	 * @param rowNum int
	 * @param colNum int
	 * @return HSSFCell
	 */
	public static HSSFCell getCell(HSSFSheet sheet, int rowNum, int colNum) {
		HSSFRow row = getRow(rowNum, sheet);
		HSSFCell cell = getCell(row, colNum);
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
	public static void copyRow(HSSFSheet sheet, int from, int to, int count) {

		for (int rownum = from; rownum < from + count; rownum++) {
			HSSFRow fromRow = sheet.getRow(rownum);
			HSSFRow toRow = getRow(to + rownum - from, sheet);
			if (null == fromRow)
				return;
			toRow.setHeight(fromRow.getHeight());
			toRow.setHeightInPoints(fromRow.getHeightInPoints());
			for (int i = fromRow.getFirstCellNum(); i <= fromRow.getLastCellNum() && i >= 0; i++) {
				HSSFCell fromCell = getCell(fromRow, i);
				HSSFCell toCell = getCell(toRow, i);
				toCell.setEncoding(fromCell.getEncoding());
				toCell.setCellStyle(fromCell.getCellStyle());
				toCell.setCellType(fromCell.getCellType());
				switch (fromCell.getCellType()) {
				case HSSFCell.CELL_TYPE_BOOLEAN:
					toCell.setCellValue(fromCell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					toCell.setCellFormula(fromCell.getCellFormula());
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					toCell.setCellValue(fromCell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					toCell.setCellValue(fromCell.getStringCellValue());
					break;
				default:
				}
			}
		}

		// copy merged region
		List shiftedRegions = new ArrayList();
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			Region r = sheet.getMergedRegionAt(i);
			if (r.getRowFrom() >= from && r.getRowTo() < from + count) {
				Region n_r = new Region();
				n_r.setRowFrom(r.getRowFrom() + to - from);
				n_r.setRowTo(r.getRowTo() + to - from);
				n_r.setColumnFrom(r.getColumnFrom());
				n_r.setColumnTo(r.getColumnTo());
				shiftedRegions.add(n_r);				
			}
		}
		
		// readd so it doesn't get shifted again
		Iterator iterator = shiftedRegions.iterator();
		while (iterator.hasNext()) {
			Region region = (Region) iterator.next();
			sheet.addMergedRegion(region);
		}		
	}

	public static void shiftCell(HSSFSheet sheet, HSSFRow row, HSSFCell beginCell, int shift, int rowCount) {

		if (shift == 0)
			return;

		// get the from & to row
		int fromRow = row.getRowNum();
		int toRow = row.getRowNum()+rowCount-1;
		for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
			Region r = sheet.getMergedRegionAt(i);
			if (r.getRowFrom() == row.getRowNum()) {
				if (r.getRowTo() > toRow) {
					toRow = r.getRowTo();
				}
				if (r.getRowFrom() < fromRow) {
					fromRow = r.getRowFrom();
				}
			}
		}

		for (int rownum = fromRow; rownum <= toRow; rownum++) {
			HSSFRow curRow = WorkbookUtils.getRow(rownum, sheet);
			int lastCellNum = curRow.getLastCellNum();		
			for (int cellpos = lastCellNum; cellpos >= beginCell.getCellNum(); cellpos--) {
				HSSFCell fromCell = WorkbookUtils.getCell(curRow, cellpos);
				HSSFCell toCell = WorkbookUtils.getCell(curRow, cellpos + shift);
				toCell.setCellType(fromCell.getCellType());
				toCell.setCellStyle(fromCell.getCellStyle());
				switch (fromCell.getCellType()) {
				case HSSFCell.CELL_TYPE_BOOLEAN:
					toCell.setCellValue(fromCell.getBooleanCellValue());
					break;
				case HSSFCell.CELL_TYPE_FORMULA:
					toCell.setCellFormula(fromCell.getCellFormula());
					break;
				case HSSFCell.CELL_TYPE_NUMERIC:
					toCell.setCellValue(fromCell.getNumericCellValue());
					break;
				case HSSFCell.CELL_TYPE_STRING:
					toCell.setCellValue(fromCell.getStringCellValue());
					break;
				case HSSFCell.CELL_TYPE_ERROR:
					toCell.setCellErrorValue(fromCell.getErrorCellValue());
					break;
				}
				fromCell.setCellValue("");
				fromCell.setCellType(HSSFCell.CELL_TYPE_BLANK);
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFCellStyle style = wb.createCellStyle();
				fromCell.setCellStyle(style);
			}
			
			// process merged region
			for (int cellpos = lastCellNum; cellpos >= beginCell.getCellNum(); cellpos--) {
				HSSFCell fromCell = WorkbookUtils.getCell(curRow, cellpos);
				
				List shiftedRegions = new ArrayList();
				for (int i=0; i<sheet.getNumMergedRegions(); i++) {
					Region r = sheet.getMergedRegionAt(i);
					if (r.getRowFrom()==curRow.getRowNum() && r.getColumnFrom() == fromCell.getCellNum()) {
						r.setColumnFrom((short) (r.getColumnFrom() + shift));
						r.setColumnTo((short) (r.getColumnTo() + shift));
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
					Region region = (Region) iterator.next();
					sheet.addMergedRegion(region);
				}					
			}			
		}
	}
}
