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
				String tmp = v_int.toString();
				if(tmp.length()>=2 && tmp.charAt(tmp.length()-1)=='0' && tmp.charAt(tmp.length()-2)=='.' )
					return tmp.substring(0,tmp.length()-2);
				else
					return tmp;
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
				String cc = getCellCotent(cell);
				if (cc == null){
					r.add("");
				}else{
					r.add(cc);
				}
			}
			info.add(r);
		}
		return info;
	}

}