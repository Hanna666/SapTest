package framework.driver;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;


public class ScenarioInput {
	
	
	XSSFSheet oSheet;
	int scenario_pointer;
	public static ScenarioInput oSenSht = new ScenarioInput();
	static private TestCaseInput oTesSht = TestCaseInput.getInstance();
	
	static private Result result = Result.getInstance();
	
	Log log = Log.getInstance();
	ExecData exStatus = ExecData.getInstance();
	String Current_Scenario="";
	
	
	private ScenarioInput()
	{
		
		FrameworkWorksheet oFrmwrk = FrameworkWorksheet.getInstance();
		oSheet = oFrmwrk.getWorkbook().getSheetAt(0);	
		scenario_pointer = 1;
		
	}
	
	
	public static ScenarioInput getInstance(){
		
		return oSenSht;
	}
	
	
	
	public String GetScenarioToExecute()
	{
		XSSFRow oRow;
		String ScenarioName=null;
		int ExecutionFlag = 0;
		String Dependant_Scenario=null;
		
		while(true)
		{
				// Check if scenario exists
				try
				{
					oRow = oSheet.getRow(scenario_pointer);
					ScenarioName = oRow.getCell(0).toString();
					
					if(ScenarioName.trim().length() == 0) 
					{
						
						throw new NullPointerException();
					}
					
				}
				catch(NullPointerException npe)
				{
					ScenarioName = null;
					log.print("No Scenarios to execute");
			    	return null;
				}
			    catch(Exception ex)
				{
			    	log.print(ex.getMessage());
			    	return null;
				}
	
				// Check execution flag
				try
				{
					ExecutionFlag = (int) oRow.getCell(1).getNumericCellValue();
					
				}
				catch(NullPointerException npe)
				{
					ExecutionFlag = 0;
				}
			    catch(Exception ex)
				{
			    	log.print(ex.getMessage());
			    	return null;
				}
				
				
				if(ExecutionFlag==0)
				{
					//System.out.println(ScenarioName + " not selected for execution.");
					//log.print(ScenarioName + " not selected for execution.");
					
					Result oResult = Result.getInstance();
					oResult.ScenarioNotSelectedForExecution(ScenarioName);					
					oResult.NotRun = oResult.NotRun + 1;
					
					++scenario_pointer;	
					continue;
				}
				else if(ExecutionFlag==1)
				{
					//Handle dependent scenario
					try
					{
						Dependant_Scenario = oRow.getCell(2).toString();
						
						if(Dependant_Scenario.trim().length() == 0) 
						{
							Dependant_Scenario = null;
							throw new NullPointerException();
						}
					}
					catch(NullPointerException npe)
					{
						  	++scenario_pointer;						    
						    Current_Scenario = ScenarioName;
						    exStatus.dict.put(Current_Scenario, "Pass");
						    log.print("Current Scenario : " + ScenarioName);
						    return ScenarioName; 
					}
					
					
				    if(scenario_pointer == 1)
					{
						result.print("Framework Config Error - First scenario cannot have dependent scenarios" , "Fail");
						return null;
					}
					
					String tmp[] = Dependant_Scenario.split(",");
					boolean dependencyFailed = false;
					for(int i=0;i<tmp.length;i++)
					{
						String val = exStatus.dict.get(tmp[i]);
						if(val==null)
						{
							dependencyFailed = true;
							break;
						}
						if(val.compareTo("Fail")==0)
						{
							dependencyFailed = true;
							break;
						}
					}
					
					if(dependencyFailed == true)				    	
					{
							
						oSenSht.Current_Scenario = ScenarioName;
						oTesSht.GroupHeaderID = "";
						result.print(ScenarioName + " was not executed as one of the dependant scenario(s) has failed.", "Fail");
						++scenario_pointer;
						continue;
					}
					
					++scenario_pointer;						    
				    Current_Scenario = ScenarioName;
				    exStatus.dict.put(Current_Scenario, "Pass");
				    log.print("Current Scenario : " + ScenarioName);
				    return ScenarioName; 
				    
				}//else
				
		}//End of while
			
	}
	
	
	
}
