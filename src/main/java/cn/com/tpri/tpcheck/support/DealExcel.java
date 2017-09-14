package cn.com.tpri.tpcheck.support;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DealExcel {

	public static String getCellCotent(Cell cell) {
		try {
			return cell.getStringCellValue();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				Double v_int = cell.getNumericCellValue();
				return v_int.toString();
			} catch (Exception e1) {
				return "";
			}
		}
	}

	public static List<List> loadIn(String filepath) throws IOException {
		// TODO Auto-generated method stub
		InputStream stream = new FileInputStream(filepath);
		String filetype = filepath.split("\\.")[filepath.split("\\.").length - 1];
		Workbook wb = null;
		if (filetype.equals("xls")) {
			wb = new HSSFWorkbook(stream);
		} else if (filetype.equals("xlsx")) {
			wb = new XSSFWorkbook(stream);
		} else {
			return null;
		}

		List<List> info = new ArrayList();

		Sheet sheet = wb.getSheetAt(0);
		for (Row row : sheet) {
			List<String> r = new ArrayList();
			for( Cell cell : row){
				r.add(getCellCotent(cell));
			}
			info.add(r);
		}
		return info;
	}

}