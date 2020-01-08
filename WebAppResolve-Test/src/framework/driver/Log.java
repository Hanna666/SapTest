package framework.driver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

//public class Log {
//
//	XSSFSheet oSheet;
//	int Log_pointer;
//	public static Log oLog = new Log();
//	
//	private Log()
//	{
//		
//		exFramwork oFrmwrk = exFramwork.getInstance();
//		try
//		{
//			oSheet = oFrmwrk.getWorkbook().getSheet("Log");		
//		}
//		catch(Exception e)
//		{
//			System.out.println(e.getMessage());
//		}
//	}
//	
//	
//}


public class Log {

	XSSFSheet oSheet;
	int Log_pointer;
	FrameworkWorksheet oFrmwrk;
	public static Log oLog = new Log();
	
	private Log()
	{
		try
		{
			oFrmwrk = FrameworkWorksheet.getInstance();
			Log_pointer = 2;
		
			oSheet = oFrmwrk.getWorkbook().getSheetAt(4);
			
			//print("Execution Log..","");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public static Log getInstance(){
		
		return oLog;
		
	}
	
	public void print(String message)
	{
		print(message,"");
	}
	
	public void print(String message,String status)
	{
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(System.getProperty("user.dir") + "\\Input-Sheet.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			XSSFRow oRow = oSheet.createRow(Log_pointer);
			
			XSSFCell oCell = oRow.createCell(0);//Sl.no. cell		
			oCell.setCellValue(Log_pointer++ - 1); //write sl. no
			
			oCell = oRow.createCell(1);//status cell
			if(status.trim().length() != 0)
				oCell.setCellValue(status); // write status if exists
			
			
			oCell = oRow.createCell(2);//message cell
			oCell.setCellValue(message);
			
			//System.out.println(message);
			
			oFrmwrk.save(fos);
			
			fos.close();
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			try
			{
				fos.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
}



