package framework.driver;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Input {
	
	XSSFSheet oSheet;
	int Input_pointer;
	FrameworkWorksheet oFrmwrk;
	public static Input oInput = new Input();
	static private Result result = Result.getInstance();
	private Input()
	{
		try
		{
			oFrmwrk = FrameworkWorksheet.getInstance();
			Input_pointer = 2;
		
			oSheet = oFrmwrk.getWorkbook().getSheetAt(3);
			
			//print("Execution Log..","");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}
	
	public static Input getInstance(){
		
		return oInput;
		
	}
	
	public String ReadGlobal(String varName)
	{
		XSSFRow oRow;
		int pointer = 1;
		String varCurrent="";
		
		while(varCurrent.compareTo("Local Variables")!=0)
		{
				
				
				try
				{
					oRow = oSheet.getRow(pointer++);
					varCurrent = oRow.getCell(0).toString().trim();
					if(varCurrent.compareTo(varName.trim())==0)
					{
						String val = oRow.getCell(1).toString();
						return val;
					}
					
					
				}
				catch(NullPointerException npe)
				{
					//return null;
				}
			    
				
		}//End of while
		return "";
	}
	
	public String Read(String testCase,String varName, int dataSet)
	{
		XSSFRow oRow;
		int pointer = 1;
		String varCurrent="";
		
		while(pointer<900)
		{
				
				try
				{
					oRow = oSheet.getRow(pointer++);
					varCurrent = oRow.getCell(0).toString().trim();
					if(varCurrent.compareTo(testCase.trim())==0)
					{
						//int new_pointer = pointer + 16;
						try
						{
							while((oRow = oSheet.getRow(pointer++))!=null)
							{
								
									//oRow = oSheet.getRow(pointer++);
									varCurrent = oRow.getCell(1).toString();
									if(varCurrent.trim().length()==0) 
									{
										result.print("Variable not found in input sheet","Fail");
										return "";
										
									}
									
									if(varCurrent.compareTo(varName.trim())==0)
									{
										
										String val = oRow.getCell(dataSet + 1).toString();
										return val;
									}
							}
						}
						catch(NullPointerException npe)
						{
							return "";
						}
						
					}
					
					
				}
				catch(NullPointerException npe)
				{
					//return null;
				}
			    
				
		}//End of while
		return "";
	}
	
	
	
	
}
