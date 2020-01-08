package framework.driver;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Result {

	XSSFSheet oSheet;
	int result_pointer;
	String Scenario;
	String GroupHeader;
	String TestCaseID;
	XSSFCell testcase_result,group_result,scenario_result,snapshot;
	
	static int Passed = 0;
	static int Failed = 0;
	static int NotRun = 0;
	
	FrameworkWorksheet oFrmwrk;
	public static Result oResult = new Result();
	FileOutputStream fos = null;
	TestCaseInput tci = TestCaseInput.getInstance();
	ScenarioInput si =  ScenarioInput.getInstance();
	ExecData exStatus = ExecData.getInstance();
	XSSFColor BlueColor;
	XSSFCellStyle styleBlue;
	XSSFFont font;
	CreationHelper createHelper = oFrmwrk.oWB.getCreationHelper();
	FileOutputStream timeStampSave = null;
	
	
	 // Create object of SimpleDateFormat class and decide the format
	 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	 
	 //get current date time with Date()
	 Date date = new Date();
	 
	 // Now format the date
	 String date1= dateFormat.format(date);
	
	
	private Result()
	{
		try
		{
			oFrmwrk = FrameworkWorksheet.getInstance();
			result_pointer = 0;
			Scenario = "";
			GroupHeader = "";
			TestCaseID = "";
			oSheet = oFrmwrk.getWorkbook().getSheetAt(2);
			
			BlueColor = new XSSFColor(java.awt.Color.decode("#a9c4f5"));
			styleBlue = oFrmwrk.oWB.createCellStyle();
			
			
			font = oFrmwrk.oWB.createFont();
			
			
						
			try {
				if(fos == null)
					fos = new FileOutputStream(System.getProperty("user.dir") + "\\Input-Sheet.xlsx");
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Clearing the REsult sheet
			for (int i=1; i<=oSheet.getLastRowNum(); i++) {

					try
					{
						oSheet.removeRow(oSheet.getRow(i));
					}
					catch(NullPointerException npe)
					{}
					
				}
			
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			
			try
			{
				/*timeStampSave = new FileOutputStream(System.getProperty("user.dir") + "\\Input-Sheet"+date1+".xlsx");
				oFrmwrk.save(timeStampSave);
				timeStampSave.close();*/
				oFrmwrk.save(fos);
				fos.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	public static Result getInstance(){
		
		
		return oResult;
		
	}
	
	public void print(String message)
	{
		print(message,"");
	}
	
	public void ScenarioNotSelectedForExecution(String ScenarioName)
	{
		
		
		
	try {
			
			try {
				if(fos == null)
					fos = new FileOutputStream(System.getProperty("user.dir") + "\\Input-Sheet.xlsx");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			XSSFRow oRow;		
			XSSFCell oCell;
			
			++result_pointer;
			if(result_pointer !=  1)
			{
				
				oRow = oSheet.createRow(result_pointer);			
				oCell = oRow.createCell(0);//Sl.no. cell		
				oCell.setCellValue(result_pointer); //write sl. no
				++result_pointer;
			}
			
			
			oRow = oSheet.createRow(result_pointer);			
			oCell = oRow.createCell(0);//Sl.no. cell		
			oCell.setCellValue(result_pointer); //write sl. no
			
			oCell = oRow.createCell(1);
			styleBlue.setFillForegroundColor(BlueColor);	
			styleBlue.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			oCell.setCellStyle(styleBlue);			
			oCell.setCellValue(ScenarioName);
			
			oCell = oRow.createCell(4);//Not Run	
			styleYellow.setFillForegroundColor(YellowColor);
			styleYellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
			oCell.setCellStyle(styleYellow);
			oCell.setCellValue("NotRun");
			
			//write message
			oCell = oRow.createCell(5);
			oCell.setCellValue("Not selected for execution.");
			
			++result_pointer;
			oRow = oSheet.createRow(result_pointer);			
			oCell = oRow.createCell(0);//Sl.no. cell		
			oCell.setCellValue(result_pointer); //write sl. no
		
			exStatus.dict.put(si.Current_Scenario, "NotRun");
			
			
			
		//System.out.println(message);
		
		
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		finally{
			
			try
			{
				/*timeStampSave = new FileOutputStream(System.getProperty("user.dir") + "\\Input-Sheet"+date1+".xlsx");
				oFrmwrk.save(timeStampSave);
				timeStampSave.close();
				*/
				oFrmwrk.save(fos);
				fos.close();
			}
			catch(Exception e)
			{
				//e.printStackTrace();
			}
		}
	
	}
	
	public void print(String message,String status)
	{
		
		try {
			
			try {
				if(fos==null)
					fos = new FileOutputStream(System.getProperty("user.dir") + "\\Input-Sheet.xlsx");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			
			
			XSSFRow oRow;		
			XSSFCell oCell;
			
			if(Scenario.compareTo(si.Current_Scenario)!=0 && si.Current_Scenario.length()!=0)
			{
					if(Scenario.length()!=0)
					{
						++result_pointer;
						oRow = oSheet.createRow(result_pointer);
						oCell = oRow.createCell(0);//Sl.no. cell
						oCell.setCellValue(result_pointer); //write sl. no
						
					}
				
					Scenario = si.Current_Scenario;
					++result_pointer;
					oRow = oSheet.createRow(result_pointer);
					oCell = oRow.createCell(0);//Sl.no. cell
					oCell.setCellValue(result_pointer); //write sl. no
					oCell = oRow.createCell(1);//Scenario
					
					styleBlue.setFillForegroundColor(BlueColor);	
					styleBlue.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
					oCell.setCellStyle(styleBlue);
					
					
					oCell.setCellValue(Scenario); // write status if exists
					scenario_result = oRow.createCell(4);
					
					XSSFCellStyle stylePassedGreen = oFrmwrk.oWB.createCellStyle();
					fontBold.setBold(true);
					stylePassedGreen.setFont(fontBold);
					stylePassedGreen.setFillForegroundColor(GreenColor);
					stylePassedGreen.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
					
					scenario_result.setCellStyle(stylePassedGreen);
					scenario_result.setCellValue("Pass");
					
					//SetStatus(scenario_result,"Pass");
					exStatus.dict.put(si.Current_Scenario, "Pass");
					
			}
			
			if(tci.GroupHeaderID != null && tci.GroupHeaderID.trim().length() != 0)
			{
				if( GroupHeader.compareTo(tci.GroupHeaderID)!=0 && tci.GroupHeaderID.trim().length()!=0)
				{
//					if(GroupHeader.length()!=0)// && tci.GroupHeaderID.toUpperCase().contains("_END")==false)
//					{
//						++result_pointer;
//						oRow = oSheet.createRow(result_pointer);
//						oCell = oRow.createCell(0);//Sl.no. cell
//						oCell.setCellValue(result_pointer); //write sl. no
//						oCell = oRow.createCell(2);//Group Header
//						oCell.setCellValue(GroupHeader); // write status if exists
//						
//					}
				
					GroupHeader = tci.GroupHeaderID;
					++result_pointer;
					oRow = oSheet.createRow(result_pointer);
					oCell = oRow.createCell(0);//Sl.no. cell
					oCell.setCellValue(result_pointer); //write sl. no
//					oCell = oRow.createCell(1);//Scenario
					oCell = oRow.createCell(2);//Group Header
					
					styleBlue.setFillForegroundColor(BlueColor);	
					styleBlue.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
					oCell.setCellStyle(styleBlue);
					
					oCell.setCellValue(GroupHeader);
					group_result = oRow.createCell(4);
					//SetStatus(group_result,"Pass");
					TestCaseID = "";
				}
			}
			
			if(TestCaseID.compareTo(tci.TestCaseID)!=0)
			{
				
				
				if(TestCaseID.trim().length() != 0)
				{
					++result_pointer;
					oRow = oSheet.createRow(result_pointer);			
					oCell = oRow.createCell(0);//Sl.no. cell		
					oCell.setCellValue(result_pointer); //write sl. no
				}
				
				TestCaseID = tci.TestCaseID;
				
				++result_pointer;
				oRow = oSheet.createRow(result_pointer);			
				oCell = oRow.createCell(0);//Sl.no. cell		
				oCell.setCellValue(result_pointer); //write sl. no
				
				oCell = oRow.createCell(3);//TEstCase id
				oCell.setCellValue(tci.TestCaseID); 
				                                                                                                                                                        
				if(status.trim().length()==0)
				{
					testcase_result = oRow.createCell(4);
										
					//style.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 255, 0)));
					XSSFCellStyle stylePassedGreen = oFrmwrk.oWB.createCellStyle();
					stylePassedGreen.setFillForegroundColor(GreenColor);
					stylePassedGreen.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
					fontBold.setBold(true);
					stylePassedGreen.setFont(fontBold);
					testcase_result.setCellStyle(stylePassedGreen);
					testcase_result.setCellValue("Pass");
					
					//SetStatus(testcase_result,"Pass");
					Passed = Passed + 1;
					
					exStatus.dict.put(TestCaseID, "Pass");
					
				}
				else
				{
					testcase_result = oRow.createCell(4);
					
					
					SetStatus(testcase_result,status);// write status if exists
					if(status.trim().compareToIgnoreCase("Fail")==0)
					{
						SetStatus(testcase_result,status);
						//SetStatus(group_result,status);
						SetStatus(scenario_result,status);
						
//						//Attaching the screen shot
//						String fileName = ScreenShotCapture.TakeScreenShot();
//						HSSFHyperlink file_link=new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
//		                //file_link.setAddress("file:///c://test.csv");
//		                file_link.setAddress(fileName);
//		                snapshot = oRow.createCell(6);
//		                snapshot.setCellValue("Take me to Google");         
//		                snapshot.setHyperlink(file_link);
						
						
						
						if(Passed >= 1) Passed = Passed - 1;
						
						Failed = Failed + 1;
						
						
						exStatus.dict.remove(si.Current_Scenario);
						exStatus.dict.put(si.Current_Scenario, "Fail");
						
						exStatus.dict.remove(TestCaseID);					
						exStatus.dict.put(TestCaseID, "Fail");
					}
					
				}
				
				
			}
			else
			{
			
				++result_pointer;
				oRow = oSheet.createRow(result_pointer);			
				oCell = oRow.createCell(0);//Sl.no. cell		
				oCell.setCellValue(result_pointer); //write sl. no
				oCell = oRow.createCell(3);//TEstCase id
				oCell.setCellValue(tci.TestCaseID); 
				
				oCell = oRow.createCell(4);
				if(status.trim().length() != 0)
				{
					//oCell.setCellValue(status);
					SetStatus(oCell,status);
					if(status.trim().compareToIgnoreCase("Fail")==0)
					{
						
						
						SetStatus(oCell,status);
						
						XSSFCellStyle styleFailedRed = oFrmwrk.oWB.createCellStyle();
						fontWhite.setColor(WhiteColor);
						fontWhite.setBold(true);
						styleFailedRed.setFont(fontWhite);
						styleFailedRed.setFillForegroundColor(RedColor);
						styleFailedRed.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
						
						
						
						testcase_result.setCellStyle(styleFailedRed);
						testcase_result.setCellValue(status);
						
						scenario_result.setCellStyle(styleFailedRed);
						scenario_result.setCellValue(status);
						
						//SetStatus(testcase_result,status);
						//SetStatus(group_result,status);
						//SetStatus(scenario_result,status);
						
						//Attaching the screen shot
						String fileName = ScreenShotCapture.TakeScreenShot();
						XSSFHyperlink file_link = (XSSFHyperlink)createHelper.createHyperlink(Hyperlink.LINK_FILE);
						//file_link.setAddress("file:///c://test.csv");
						file_link.setAddress(fileName);
		                snapshot = oRow.createCell(7);
		                snapshot.setCellValue("SnapShot");         
		                snapshot.setHyperlink(file_link);
						
						if(Passed >= 1) Passed = Passed - 1;
						
						Failed = Failed + 1;
						
						
						exStatus.dict.remove(si.Current_Scenario);
						exStatus.dict.put(si.Current_Scenario, "Fail");
						
						exStatus.dict.remove(TestCaseID);					
						exStatus.dict.put(TestCaseID, "Fail");
					}
					
				}
			}
			
			
			
			//write message
			oCell = oRow.createCell(5);
			oCell.setCellValue(message);
			 // Create object of SimpleDateFormat class and decide the format
			 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			 //get current date time with Date()
			 Date date = new Date();
			 // Now format the date
			 String timeStamp= dateFormat.format(date);
			oCell = oRow.createCell(6);
			oCell.setCellValue(timeStamp);
			
		
		//System.out.println(message);
		
		
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			
			try
			{
				/*timeStampSave = new FileOutputStream(System.getProperty("user.dir") + "\\Input-Sheet"+date1+".xlsx");
				oFrmwrk.save(timeStampSave);
				timeStampSave.close();
				*/
				oFrmwrk.save(fos);
				fos.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	XSSFFont fontBold = oFrmwrk.oWB.createFont();
	
	XSSFFont fontWhite = oFrmwrk.oWB.createFont();
	XSSFColor WhiteColor = new XSSFColor(Color.WHITE);
	
	XSSFFont fontGreen = oFrmwrk.oWB.createFont();
	XSSFColor GreenColor = new XSSFColor(Color.GREEN);
	XSSFCellStyle styleGreen = oFrmwrk.oWB.createCellStyle();
	
	XSSFFont fontRed = oFrmwrk.oWB.createFont();
	XSSFColor RedColor = new XSSFColor(Color.RED);
	XSSFCellStyle styleRed = oFrmwrk.oWB.createCellStyle();
	
	XSSFColor YellowColor = new XSSFColor(Color.yellow);
	XSSFCellStyle styleYellow = oFrmwrk.oWB.createCellStyle();
	
	private void SetStatus(XSSFCell oCell, String Status)
	{
	
		try
		{
			
			
			if(Status.trim().compareToIgnoreCase("Pass")==0)
			{
				fontGreen.setColor(new XSSFColor(new java.awt.Color(102,163,13)));
				styleGreen.setFont(fontGreen);
//				styleGreen.setFillForegroundColor(GreenColor);	
//				styleGreen.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
				oCell.setCellStyle(styleGreen);
			}
			else if(Status.trim().compareToIgnoreCase("Fail")==0)
			{
				fontRed.setColor(RedColor);
				styleRed.setFont(fontRed);
//				styleRed.setFillForegroundColor(RedColor);
//				styleRed.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
				oCell.setCellStyle(styleRed);
			}
			else //if(Status.trim().compareToIgnoreCase("NoRun")==0)
			{
				styleYellow.setFillForegroundColor(YellowColor);
				styleYellow.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
				oCell.setCellStyle(styleYellow);
			}
			
			
			oCell.setCellValue(Status);
			
		}
		catch(Exception e)
		{
			
		}
	
	}
	
}
