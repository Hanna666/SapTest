      package framework.driver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.tis.testcase.Init;

public class ScreenShotCapture
{

	   static TestCaseInput Tc = TestCaseInput.getInstance();
       
       public static String TakeScreenShot()
       {
    	   String FilePath =null;
   	   try
    	   {
             String FunName=Thread.currentThread().getStackTrace()[1].getMethodName();
             ScreenShotCapture ss =new ScreenShotCapture();
             String timeStamp=ss.getCurrentSysTime();
             File scrFile = ((TakesScreenshot)Init.driver).getScreenshotAs(OutputType.FILE);           
             Calendar cal = Calendar.getInstance();
             String date = new SimpleDateFormat("E_MMM_d_yyyy").format(cal.getTime());
      		 FilePath = "TIS_ScreenShots/"+date+"/" +Tc.TestCaseID+"_"+timeStamp+".png";
             FileUtils.copyFile(scrFile, new File(FilePath));             
    	   }
    	   catch(Exception e)
    	   {
    		   
    	   }
    	   return FilePath;
              
       }
       
       public String getCurrentSysTime()
       {
              String Time=null;
              Time =new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

              return Time;
       }

}
