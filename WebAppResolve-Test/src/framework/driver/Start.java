package framework.driver;

import com.tis.testcase.Init;

public class Start {

	public static void main(String[] args) {
		ScenarioInput osIS = ScenarioInput.getInstance();
		TestCaseInput otIS = TestCaseInput.getInstance();		
	
		while(osIS.GetScenarioToExecute() != null ){
		    while(otIS.GetTestCaseToExecute())
		    {
		    	System.out.println("Current TestcaseID : " + otIS.TestCaseID);
				Init.TestCaseFunction(otIS.TestCaseID);
		    }
		}
	}
}
	