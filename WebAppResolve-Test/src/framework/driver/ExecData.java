package framework.driver;

import java.util.*;

public class ExecData {
	

	public static ExecData  execData= new ExecData();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Dictionary<String,String> dict = new Hashtable();
		
	private ExecData()
	{
		
	}
		
	public static ExecData getInstance(){
		
		return execData;
		
	}
	
	
}
