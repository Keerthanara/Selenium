package Selenium.SeleniumTA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static void ReadFileFromExcel() throws IOException {
	
		
		//Excel Reader
		
		FileInputStream Fread;
		XSSFWorkbook wb;
		XSSFSheet sh;
		String d1;
		Fread=new FileInputStream("C:\\Users\\Training_C2a.05.01\\Desktop\\SeleniumAutomation\\Input\\ReadFile.xlsx");
		wb=new XSSFWorkbook(Fread);
		sh=wb.getSheetAt(0);
		System.out.println(sh.getLastRowNum());
		for (int i=0;i<=sh.getLastRowNum();i++)
		{
		d1=sh.getRow(i).getCell(0).getStringCellValue();
		System.out.println("Cell Value:"+d1);
		}
	}
	
	public static void GetValue( int RowCount,String ColoumKey) throws IOException{
		FileInputStream Fread;
		XSSFWorkbook wb;
		XSSFSheet sh;
		String d1="";
		Fread=new FileInputStream("C:\\Users\\Training_C2a.05.01\\Desktop\\SeleniumAutomation\\Input\\ReadFile.xlsx");
		wb=new XSSFWorkbook(Fread);
		sh=wb.getSheetAt(0);
		System.out.println(sh.getLastRowNum());
		int i=0;
		for (i=0;i<=sh.getRow(0).getLastCellNum();i++)
		{	
			d1=sh.getRow(0).getCell(i).getStringCellValue();
			if(d1.equals(ColoumKey))
				break;
			else
				d1="";
		}
		System.out.println("Value:"+sh.getRow(RowCount).getCell(i).getStringCellValue());
	}
	
	
}
