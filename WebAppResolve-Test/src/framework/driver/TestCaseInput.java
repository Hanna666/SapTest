package framework.driver;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class TestCaseInput {

	private XSSFSheet oSheet;
	private static int testcase_pointer = -1;
	private int scenariostart_pointer = -1;
	private int scenarioend_pointer = -1;
	private String Current_Scenario=null;
	private String Current_TestcaseID=null;
	public String GroupHeaderID=null;
	public String TestCaseID=null;
	
	public static TestCaseInput oTesSht = new TestCaseInput();
	Log log = Log.getInstance();
	ExecData exStatus = ExecData.getInstance();
	ScenarioInput osIS = ScenarioInput.getInstance();	
	
	
	private TestCaseInput()
	{
		
		FrameworkWorksheet oFrmwrk = FrameworkWorksheet.getInstance();
		oSheet = oFrmwrk.getWorkbook().getSheetAt(1);		
		testcase_pointer = -1;
		
	}
	
	
	public static TestCaseInput getInstance()
	{
		return oTesSht;
	}
	
	
	
	
	public boolean GetTestCaseToExecute()
	{
		
		XSSFRow oRow;
		TestCaseID=null;
		int ExecutionFlag = 0;
		String scenarioToExecute  = osIS.Current_Scenario;
		//testcase_pointer = -1;
		//System.out.println("In GetTestCaseToExecute = " +  scenarioToExecute);
		
		
		// Check if scenario exists //Scenario init
		if(Current_Scenario == null || scenarioToExecute.compareToIgnoreCase(Current_Scenario)!=0)
		{
			boolean ScenarioFound = false;
			int temp_pointer = 1;
			String tempScenarioName = null;
			while(true)
			{
				
				try
				{					
					oRow = oSheet.getRow(temp_pointer);
					tempScenarioName = oRow.getCell(0).toString();
					
					if(tempScenarioName.trim().length() == 0) 
					{
						throw new NullPointerException();
					}
					
					
				}
				catch(NullPointerException npe)
				{
					tempScenarioName = null;
					if(temp_pointer>=500)
					{
						testcase_pointer = -1;
						log.print("Testcases for corresponding testcases are not available.","FrameworkConfig Error");
				    	return false;
						
					}
					
					temp_pointer++;
					continue;
				}
			    catch(Exception ex)
				{
			    	log.print("Unknown error");
			    	return false;
				}
				
				if(scenarioToExecute.compareToIgnoreCase(tempScenarioName)==0)
				{
					if(ScenarioFound == false)//finding start of scenario
					{
						Current_Scenario = scenarioToExecute;
						scenariostart_pointer = temp_pointer;
						testcase_pointer = scenariostart_pointer+1;
						ScenarioFound = true;
						scenarioToExecute = scenarioToExecute + "_End";
					}
					else // finding the end of scenario
					{
						scenarioend_pointer = temp_pointer;
						
						break;
					}
						
				}
				
				
				if(temp_pointer>=500)
				{
					testcase_pointer = -1;
					log.print("Testcases for corresponding testcases are not available.","FrameworkConfig Error");
			    	return false;
					
				}
				
				temp_pointer++;
			
			}
		}//end of scenario init.
		
		
		//Find the next testcase to execute
		//boolean GroupHeader = false;
		
		while(testcase_pointer < scenarioend_pointer)
		{
				//if(GroupHeader ==false)
				//{
				//If Group header exists
				try
				{
					oRow = oSheet.getRow(testcase_pointer);
					GroupHeaderID = oRow.getCell(1).toString();
					
					if(GroupHeaderID.trim().length() == 0) 
					{
						throw new NullPointerException();
					}
					
					//GroupHeader = true;
					++testcase_pointer;
					continue;
				}
				catch(NullPointerException npe)
				{
					//GroupHeaderID = null;
				}
			    catch(Exception ex)
				{
			    	log.print("Unknown error");
			    	return false;
				}
				//}
			
			
			//get test case id
			try
			{
				oRow = oSheet.getRow(testcase_pointer);
				Current_TestcaseID = oRow.getCell(2).toString();
				
				if(Current_TestcaseID.trim().length() == 0) 
				{
					throw new NullPointerException();
				}
				
				TestCaseID = Current_TestcaseID.trim();
				
				// Check execution flag
				try
				{
					ExecutionFlag = (int) oRow.getCell(3).getNumericCellValue();
				}
				catch(NullPointerException npe)
				{
					ExecutionFlag = 0;
				}
			    catch(Exception ex)
				{
			    	log.print(ex.getMessage());
			    	return false;
				}
				
				
				if(ExecutionFlag==0)
				{
					//System.out.println("executionflag = 0");
					Result oResult = Result.getInstance();
					oResult.print(Current_TestcaseID + " was not selected for execution.","NoRun");					
					oResult.NotRun = oResult.NotRun + 1;
					
					++testcase_pointer;
					continue;
				}
				else
				{
					//dependent scenario logic
				}
				
				++testcase_pointer;
			}
			catch(NullPointerException npe)
			{
				//log.print("No Test case id", "FrameworkConfig Error");
				//return false;
				Current_TestcaseID = null;
				continue;
			}
		    catch(Exception ex)
			{
		    	log.print("Unknown error");
		    	Current_TestcaseID = null;
		    	return false;
			}
			
			//System.out.println("Pointer = " +  testcase_pointer);
			
			TestCaseID = Current_TestcaseID;
			exStatus.dict.put(TestCaseID, "Pass");
			return true;
		}
		
		
		return false;
		
		
		//return TestCaseID;
		
//		while(true)
//		{
//				// Check if scenario exists
//				try
//				{
//					oRow = oSheet.getRow(testcase_pointer);
//					TestCaseID = oRow.getCell(0).toString();
//					
//				}
//				catch(NullPointerException npe)
//				{
//					log.print("End of TestCases");
//			    	return null;
//				}
//			    catch(Exception ex)
//				{
//			    	log.print("Unknown error");
//			    	return null;
//				}
//	
//				// Check execution flag
//				try
//				{
//					ExecutionFlag = (int) oRow.getCell(1).getNumericCellValue();
//				}
//				catch(NullPointerException npe)
//				{
//					ExecutionFlag = 0;
//				}
//			    catch(Exception ex)
//				{
//			    	log.print("Unknown error");
//			    	return null;
//				}
//				
//				
//				if(ExecutionFlag==0)
//				{
//					//System.out.println("executionflag = 0");
//					++testcase_pointer;	
//					continue;
//				}
//				else
//				{
//					//Handle dependent scenario
//					try
//					{
//						Dependant_Scenario = oRow.getCell(2).toString();
//					}
//					catch(NullPointerException npe)
//					{
//						  	++testcase_pointer;						    
//						    log.print("Scenario to execute: " + ScenarioName,"");
//						    Current_Scenario = ScenarioName;
//						    return ScenarioName; 
//					}
//					
//					if(scenario_pointer == 1 && Dependant_Scenario != null)
//				    {
//				    	log.print("First scenario cannot be dependant on the subsecquent scenarios","Framework Config Error");
//				    	return null;
//				    }
//				    else if(Dependant_Scenario != null)
//				    {
//				    	
//				    	//if dependent scenario passed
//					    ++scenario_pointer;					    
//					    log.print("Scenario to execute: " + ScenarioName,"");
//					    Current_Scenario = ScenarioName;
//					    return ScenarioName;   
//				    	
//					    
//					  //if dependent scenario failed
//					   // ++scenario_pointer;
//					   // continue;
//				    }
//					
//					
//				}//else
//				
//		}//End of while
			
	}
	
	
}
