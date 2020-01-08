package com.tis.testcase;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Library extends Init{
	
	
	public static WebDriverWait wait = new WebDriverWait(Init.driver, 10);
	
	public static class Interaction
	{
		
		public static void HideTimeElapseInfo()
		{
			//Hide Element
			try
			{
				WebElement oWE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[contains(@class,'tTimeRemaining')]")));
				if(oWE == null)
				{
					result.print("Unable to hide the time tracker", "Fail");
					return;
				}
				//Click Add Button
				try {Thread.sleep(1000);} catch (InterruptedException e) {}		
				JavascriptExecutor jse = (JavascriptExecutor)Init.driver;
				jse.executeScript("arguments[0].style.visibility='hidden'", oWE);
				result.print("Hiding the Time Elapse window", "Pass");
			}
			catch(Exception e)
			{
				result.print("Error while clicking on mileage info - Add Button", "Fail");
				return;
			}	
		}
		
		public static boolean ClickJavaButton(String Label,String id)
		{
			
	    	//String ButtonID = JavaButton.getAttribute("id").trim();
			try
			{
				//Click Edit Button
				try {Thread.sleep(1000);} catch (InterruptedException e) {}		
				JavascriptExecutor jse = (JavascriptExecutor)Init.driver;
				//jse.executeScript("arguments[0].scrollIntoView(true);", JavaButton);
				jse.executeScript("document.getElementById('" + id + "').click();");
				result.print(Label + " Button was clicked", "Pass");
			}
			catch(Exception e)
			{
				result.print("Error while clicking " + Label + " Button", "Fail");
				return false;
			}
			
			return true;
		}
		
		public static boolean ClickJavaButton(String Label,WebElement JavaButton)
		{
			
	    	String ButtonID = JavaButton.getAttribute("id").trim();
			try
			{
				//Click Edit Button
				try {Thread.sleep(1000);} catch (InterruptedException e) {}		
				JavascriptExecutor jse = (JavascriptExecutor)Init.driver;
				jse.executeScript("arguments[0].scrollIntoView(true);", JavaButton);
				jse.executeScript("document.getElementById('" + ButtonID + "').click();");
				result.print(Label + " Button was clicked", "Pass");
			}
			catch(Exception e)
			{
				result.print("Error while clicking " + Label + " Button", "Fail");
				return false;
			}
			
			return true;
		}
		
		
		
		

		
		//Click Web Element
		public static boolean ClickElement(String  lable, By oby)
		{
			try
			
			{
				//driver.findElement(By.xpath("")).click();
				driver.findElement(oby).click();
				
			}
			catch(Exception e)
			{
				result.print(lable + " was not clicked :"+e.getMessage(), "Fail");
				return false;
			}
			
			result.print(lable + " was clicked", "Pass");
			return true;
		}
		
		
		// edit box 
		public static boolean SetEditBox(String Label,By oBy,String newValue)
		{
			
			WebElement oWE = null;
			try
			{	
				//Edit Box, Date
//				fluentWait = new FluentWait<WebDriver>(driver)
//			            .withTimeout(10, TimeUnit.SECONDS)
//			            .pollingEvery(500, TimeUnit.MILLISECONDS)
//			            .ignoring(NoSuchElementException.class);
//				
//				oWE = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
				
				oWE = driver.findElement(oBy);
				if(oWE == null)
				{
					result.print(Label + " was not found on the Page", "Fail");
					return false;
				}
				
				boolean value = oWE.getAttribute("disabled") != null;
				
				if(value == true )
				{
					result.print(Label + " was found on the Page, but disabled", "Fail");
					return false;

				}
				else
				{
					oWE.clear();
					oWE.sendKeys(newValue);
					result.print(newValue + " was set for " + Label + " field found on the Page.", "Pass");
					return true;
				}
				
				
			}
			catch(Exception e)
			{
				result.print(e.getMessage(), "Fail");
				return false;
			}
			
			
			
		
		}
		
		
		public static boolean SelectBoxOptions(String Label,By oBy,String newValue)
		{
			//.//*[@class='ddlPurpose']//select
			WebElement oWE = null;
			List<WebElement> oWEM = null;
			try
			{	
				oWE = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
				//oWE = wait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
				if(oWE == null)
				{
					result.print(Label + " was not found on the Page", "Fail");
					return false;
				}
				
				String value = null;
				value = oWE.getAttribute("class");
				
				if(value != null && value.trim().contains("dropdownHide") == true)
				{
					result.print(Label + " was found on the Page, but disabled", "Fail");
					return false;

				}
				else
				{
					WebElement oSelect = driver.findElement(oBy);
				
					if(oSelect!=null)
					{
						Select oSel = new Select(oSelect);
						oWEM = oSel.getOptions();
						
//						if(oWEM.size() > 1)
//							oSel.selectByIndex(oWEM.size()-1);
						
						result.print("Following are the options for " + Label, "Pass");
						
						if(oWEM!=null)
						{
							for(WebElement oWE1:oWEM)
							{
								String s = oWE1.getText();
								
								if(s.trim().length() != 0)
								{
									result.print(s, "Pass");	
								}
								
							}
						}
						
						return true;
						
						
					}
					
					
				}
				
			}
			catch(Exception e)
			{
				result.print(e.getMessage(), "Fail");
				return false;
			}
			return false;
			
		}
		
		
		public static boolean SelectBox(String Label,By oBy,int index)
		{
			WebElement oSelect = null;
			try
			{	
				oSelect = Init.driver.findElement(oBy).findElement(By.xpath("select")); //oWE = fluentWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
				//oWE = wait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
				if(oSelect == null)
				{
					result.print(Label + " was not found on the Page", "Fail");
					return false;
				}
				
				String value = null;
				value = oSelect.getAttribute("class");
				
				if(value != null && value.trim().contains("dropdownHide") == true)
				{
					result.print(Label + " was found on the Page, but disabled", "Fail");
					return false;

				}
				else
				{
						Select oSel = new Select(oSelect);
						List<WebElement> oWEM = oSel.getOptions();
						
						int indx = 1;
						for(WebElement oWE1:oWEM)
						{
							String s = oWE1.getText();
							
							if(s.trim().length() != 0)
							{
								if(index == indx )
								{
									
									if(index >2) oSel.selectByIndex(index - 1);
									try {Thread.sleep(200);} catch (InterruptedException e) {}
									Init.driver.findElement(oBy).findElement(By.xpath("div[1]/a/div")).click();
									try {Thread.sleep(600);} catch (InterruptedException e) {}	
									// Configure the Action
									Actions action = new Actions(driver);
									WebElement oWE2 = Init.driver.findElement(By.xpath(".//*[contains(@class,'result-selectable')]//*[contains(text(),'" + s + "')]"));
									Point p = oWE2.getLocation();
									// To click on the element
									action.moveToElement(oWE2).click().perform();
									
									result.print("Selected the option " + s, "Pass");
									return true;
								}

							}
							
						}
						
				}
				
			}
			catch(Exception e)
			{
				result.print(e.getMessage(), "Fail");
				return false;
			}
			return false;
			
			
		
		}
		
		public static boolean SelectBox(String Label,By oBy,String newValue)
		{
			
			WebElement oSelect = null;
			try
			{	
				oSelect = Init.driver.findElement(oBy).findElement(By.xpath("select"));//fluentWait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
				//oWE = wait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
				if(oSelect == null)
				{
					result.print(Label + " was not found on the Page", "Fail");
					return false;
				}
				
				String value = null;
				value = oSelect.getAttribute("class");
				
				if(value != null && value.trim().contains("dropdownHide") == true)
				{
					result.print(Label + " was found on the Page, but disabled", "Fail");
					return false;

				}
				else
				{
					
				
					if(oSelect!=null)
					{
						Select oSel = new Select(oSelect);
						List<WebElement> oWEM = oSel.getOptions();
						
						int nCnt = 1;
						for(WebElement oWE1:oWEM)
						{							
							String s = oWE1.getText();
							
						
							
							if(s.trim().contains(newValue) == true)
							{
								
						    if(nCnt >2) oSel.selectByIndex(nCnt - 1);
								try {Thread.sleep(200);} catch (InterruptedException e) {}
								Init.driver.findElement(oBy).findElement(By.xpath("div[1]/a/div")).click();
								try {Thread.sleep(600);} catch (InterruptedException e) {}	
								
								// Configure the Action
								Actions action = new Actions(driver);
								WebElement oWE2 = Init.driver.findElement(By.xpath(".//*[contains(@class,'result-selectable')]//*[contains(text(),'" + s + "')]"));
								Point p = oWE2.getLocation();
								action.moveToElement(oWE2).click().perform();
								
								break;

							}
							nCnt++;
						}
						
						
					}
					result.print(newValue + " was set for " + Label + " field.", "Pass");
					return true;
				}
				
			}
			catch(Exception e)
			{
				result.print(e.getMessage(), "Fail");
				return false;
			}
			
		}

		public static void ClickLinks(String linkName) {
			try{
				driver.findElement(By.xpath("//a[contains(text(),'"+linkName+"')]")).click();
				result.print("Clicked on '"+linkName+"' link", "Pass");
			}
			catch (Exception e) {
				result.print("Not clicked on '"+linkName+"' link", "Fail");
			}	
		}
		
		public static void verifyBreadcrumb(String text){
			WebElement breadcrumb = driver.findElement(By.xpath("//li[@class='score-breadcrumb-item active']"));
			if(breadcrumb.getText().toLowerCase().contains(text.toLowerCase()))
				result.print("Displaying valid breadcrumb in "+text+" page","Pass");
			else
				result.print("Displaying invalid breadcrumb in "+text+" page", "Fail");
		}
		
		public static void verifyPageHeading(String heading) {
			try{
				if(driver.findElement(By.xpath("//h1[contains(text(),'"+heading+"')]")).isDisplayed())
					result.print("Displaying the heading '"+heading+"'", "Pass");
				else
					result.print("Not displaying the heading '"+heading+"'", "Fail");
			}
			catch (Exception e) {
				result.print("Not displaying the heading '"+heading+"'", "Fail");
			}
		}
		
		public static void verifyLabels(String label, String pageName) {
			try{
				if(driver.findElement(By.xpath("//div[@class='form-group']/label[text()='"+label+"']")).isDisplayed())
					result.print("Displaying the label '"+label+"' in '"+pageName+"' page", "Pass");
				else
					result.print("Not displaying the label '"+label+"' in '"+pageName+"' page", "Fail");
			}
			catch (Exception e) {
				result.print("Not displaying the label '"+label+"' in '"+pageName+"' page", "Fail");
			}
		}
		
		public static void verifyLabels(String label, String pageName, int i) {
			try{
				if(driver.findElement(By.xpath("(//div[@class='form-group']/label[text()='"+label+"'])["+i+"]")).isDisplayed())
					result.print("Displaying the label '"+label+"' in '"+pageName+"' page", "Pass");
				else
					result.print("Not displaying the label '"+label+"' in '"+pageName+"' page", "Fail");
			}
			catch (Exception e) {
				result.print("Not displaying the label '"+label+"' in '"+pageName+"' page", "Fail");
			}
		}
		
		public static void selectDropdown(String Label,String txt, String id){
			try{
			WebElement dropdown = driver.findElement(By.id(id));
			Select dropdownSel = new Select(dropdown);
			dropdownSel.selectByVisibleText(txt);
			result.print("Selected '"+txt+"' in '"+Label+"' field", "Pass");
			}
			catch (Exception e) {
				result.print("Not selected '"+txt+"' in '"+Label+"' field", "Fail");
			}
		}

		public static void ClickButton(String label) {
			try{
				driver.findElement(By.xpath("//input[@value='"+label+"']")).click();
				result.print("Clicked on '"+label+"' button", "Pass");
			}
			catch (Exception e) {
				result.print("Not clicked on '"+label+"' button", "Fail");
			}
			
		}

		public static void verifyButton(String label, String pageName) {
			try{
				if(driver.findElement(By.xpath("//input[@value='"+label+"']")).isDisplayed())
					result.print("Displaying '"+label+"' button in '"+pageName+"' page", "Pass");
				else
					result.print("Not displaying '"+label+"' button in '"+pageName+"' page", "Fail");
			}
			catch (Exception e) {
				result.print("Not displaying '"+label+"' button in '"+pageName+"' page", "Fail");
			}
		}
		
		public static String getTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
        String formattedDate = dateFormat.format(new Date()).toString();
		return formattedDate;
		}

		public static void EnterData(String label, String txt) {
			try{
				driver.findElement(By.xpath("(//label[contains(text(),'"+label+"')]/ancestor::div/div/input)[2]")).clear();
				driver.findElement(By.xpath("(//label[contains(text(),'"+label+"')]/ancestor::div/div/input)[2]")).sendKeys(txt);
				result.print("Entered '"+txt+"' in '"+label+"' textfield", "Pass");
			}
			catch (Exception e) {
				result.print("Not entered '"+txt+"' in '"+label+"' textfield", "Fail");
			}
		}
		
		public static void EnterData(String label, String txt, int i) {
			try{
				driver.findElement(By.xpath("(//label[contains(text(),'"+label+"')]/ancestor::div/div/input)["+i+"]")).clear();
				driver.findElement(By.xpath("(//label[contains(text(),'"+label+"')]/ancestor::div/div/input)["+i+"]")).sendKeys(txt);
				result.print("Entered '"+txt+"' in '"+label+"' textfield", "Pass");
			}
			catch (Exception e) {
				result.print("Not entered '"+txt+"' in '"+label+"' textfield", "Fail");
			}
		}
		

		public static void switchWindow() throws InterruptedException {
		Iterator<String> wh = driver.getWindowHandles().iterator();
		Thread.sleep(5000);
		String parent = wh.next();
		String child = wh.next();

		driver.switchTo().window(child);
		Thread.sleep(3000);
		String title = driver.getTitle();
		System.out.println("Title : " + title);
	}
		
		
	}
	
	public static class SiteHeader
	{
		
		public static boolean ClickMenu(String text)
		{
			try
			{
				wait = new WebDriverWait(Init.driver, 10);
				WebElement oWE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='UlMenus']//a/span[text()='" + text + "']")));
						
						
				oWE.click();
				
			}
			catch(Exception e)
			{
				result.print(e.getMessage(), "Fail");
				return false;
			}
			
			result.print(text + " was clicked in the Site Header", "Pass");
			return true;
		}
		
	}//End of Site Header Class
	
	public static class Validation
	{
		
		
		
		
		public static boolean CheckForError(By oBy)
		{
				try
				{
					
					List<WebElement>oWEs = Init.driver.findElements(oBy);	
		 			if(oWEs.size()!=0)
		 			{
		 				
		 				for(WebElement oWE:oWEs)
		 				{
		 					result.print(oWE.getAttribute("id") + " is highlighted for errors", "Pass");
		 					return true;
		 				}
		 			}// delete if exists
				}
				catch(Exception e)
				{
					result.print(e.getMessage(), "Fail");
					return false;
				}
				result.print("No errors was found on the Page", "Pass");
				
			
			return false;
		}
		//Check for an Element on the page 
		//Label - Lable or Name of the Element
		//oBy - locator
		public static boolean CheckForElement(String Label, By oBy)
		{
			try
			{
				wait = new WebDriverWait(Init.driver, 10);
				WebElement oWE = wait.until(ExpectedConditions.visibilityOfElementLocated(oBy));
				if(oWE == null)
				{
					result.print(Label + " was not found on the Page", "Fail");
					return false;
				}
			}
			catch(Exception e)
			{
				result.print(Label + " WebElement is not found:" +e.getMessage(), "Fail");
				return false;
			}
			result.print(Label + " was found on the Page", "Pass");
			return true;
		}
		
		
		
	}
}
