package com.pageobjectmodel.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.tis.testcase.Init;

import SapDocuments.SapDocument;

public class TIS_eFLOW extends Init {
	public TIS_eFLOW(WebDriver driver) {
		TIS_eFLOW.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void approveWorkFlow(String eID) throws InterruptedException {
		try {
			WebDriverWait wait=new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Search']")));
			driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
			result.print("Clicked on Search", "Pass");
			Thread.sleep(10000);
			//WebElement eflowID = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/md-content[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[2]/input[1]"));
			WebElement eflowID = driver.findElement(By.xpath("(//md-content//div//input[@name='filter'])[12]"));
			eflowID.sendKeys(eID);
			Thread.sleep(1000);
			result.print("Entered eFLOW ID "+eID, "Pass");
			
			if (driver.getPageSource().contains("ng-scope layout-row flex clickablelink")) {
				result.print("successfully searched the eFLOW DI "+eID, "Pass");
				driver.findElement(By.xpath("//div[@class='ng-scope layout-row flex clickablelink']")).click();
				result.print("Clicked on Invoice Number", "Pass");
			}else {
				result.print("Searched eFLOW DI "+eID+" does not exist in My Workflow list", "Fail");
				System.out.println("tesPass");
			}
	
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[span[contains(text(),'Approve')]]")));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[span[contains(text(),'')]]")));
			Thread.sleep(10000);
			//driver.findElement(By.xpath("//div[span[contains(text(),'Approve')]]")).click();
			driver.findElement(By.xpath("//i[contains(text(),'check')]")).click();
			result.print("Clicked on Approve Workflow button on tool bar " + eID, "Pass");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Refresh']")));
			Thread.sleep(10000);
			result.print("Approved Workflow successfully for the invoice " + eID, "Pass");
		} catch (Exception e) {
			result.print("Failed to Approve WorkFlow", "Fail");
		}

	}
	
	

	public void rejectWorkFlow(String eID) throws InterruptedException {
		try {
			WebDriverWait wait=new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Search']")));
			driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
			result.print("Clicked on Search", "Pass");
			//WebElement eflowID = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/md-content[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[2]/input[1]"));
			WebElement eflowID = driver.findElement(By.xpath("(//md-content//div//input[@name='filter'])[12]"));
			eflowID.sendKeys(eID);	
			result.print("Entered eFLOW ID "+eID, "Pass");
			
			if (driver.getPageSource().contains("ng-scope layout-row flex clickablelink")) {
				result.print("successfully searched the eFLOW DI "+eID, "Pass");
				driver.findElement(By.xpath("//div[@class='ng-scope layout-row flex clickablelink']")).click();
				result.print("Clicked on Invoice Number", "Pass");
			}else {
				result.print("Searched eFLOW DI "+eID+" does not exist in My Workflow list", "Fail");
				System.out.println("tesPass");
			}
			
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[span[contains(text(),'')]]")));
			Thread.sleep(10000);
			
			//Click on Reject Workflow Button
			driver.findElement(By.xpath("//*[@id=\"validate-toolbar-left\"]/div/button[4]")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Refresh']")));
			Thread.sleep(10000);
			
			result.print("Workflow rejected - " + eID, "Pass");
			
			
		} catch (Exception e) {
			result.print("Failed to reject the workflow - " + eID,"Fail");
		}
	}
	
	public void sendInquiry(String eID, String Recipient_ID)throws InterruptedException{
		try {
			
			WebDriverWait wait=new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Search']")));
			driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
			result.print("Clicked on Search", "Pass");
			//WebElement eflowID = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/md-content[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[2]/input[1]"));
			WebElement eflowID = driver.findElement(By.xpath("(//md-content//div//input[@name='filter'])[12]"));
			eflowID.sendKeys(eID);	
			result.print("Entered eFLOW ID "+eID, "Pass");
			
			if (driver.getPageSource().contains("ng-scope layout-row flex clickablelink")) {
				result.print("successfully searched the eFLOW DI "+eID, "Pass");
				driver.findElement(By.xpath("//div[@class='ng-scope layout-row flex clickablelink']")).click();
				result.print("Clicked on Invoice Number", "Pass");
			}else {
				result.print("Searched eFLOW DI "+eID+" does not exist in My Workflow list", "Fail");
				System.out.println("tesPass");
			}			
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"validate-toolbar-left\"]/div/button[6]/div[1]/span[1]/i")));
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id=\"validate-toolbar-left\"]/div/button[6]/div[1]/span[1]/i")).click();
			result.print("Clicked on Workflow Send Inquiry button", "Pass");
			//*********************************************************************************
			//Click on History Button
			//driver.findElement(By.xpath("//div[span[contains(text(),'History')]]")).click();
			//driver.findElement(By.xpath("//div[span[contains(text(),'Notes')]]")).click();
			//driver.findElement(By.xpath("//div[span[contains(text(),'Save')]]")).click();
			//driver.findElement(By.xpath("//div[span[contains(text(),'Save')]]")).click();
			//div[span[i[contains(text(),'attachment')]]]
			//div[span[i[contains(text(),'insert_drive_file')]]]
			//*********************************************************************************
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//input[contains(@class,'select-dropdown')]")));
			driver.findElement(By.xpath("//div//input[contains(@class,'select-dropdown')]")).click();
			driver.findElement(By.xpath("//ul//li//span[text()='None']")).click();
			
			driver.findElement(By.xpath("//input[@id='autocomplete-input']")).sendKeys(Recipient_ID);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='highlight']")));
			driver.findElement(By.xpath("//span[@class='highlight']")).click();
			result.print("Entered/Selected Recipient "+Recipient_ID+" for Workflow inquiry", "Pass");
			
			driver.findElement(By.xpath("//textarea[@placeholder='Enter your query here']")).sendKeys("QaTest_Inquiry");
			result.print("Entered inquiry query successfully", "Pass");
			WebElement btn_status = driver.findElement(By.xpath("//button[contains(text(),'Send')]"));
			if (btn_status.isEnabled()){
				result.print("Workflow inquiry Send button is enabled", "Pass");
			}
			else {
				result.print("Workflow Send button is not enabled", "Fail");
			}
			driver.findElement(By.xpath("//button[contains(text(),'Send')]")).click();
			result.print("Clicked on Workflow inquiry Send button Successfully", "Pass");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Refresh']")));
			result.print("Workflow sent for inquiry Successfully", "Pass");
		
			
			} catch(Exception e) {
				result.print("Failed to send workflow for inquiry - "+eID, "Fail");
			}

	}
	
		public void forwardWorkFlow(String eID, String Recipient_ID)throws InterruptedException{
			try {
				WebDriverWait wait=new WebDriverWait(driver,90);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Search']")));
				driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
				//WebElement eflowID = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/md-content[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[2]/input[1]"));
				WebElement eflowID = driver.findElement(By.xpath("(//md-content//div//input[@name='filter'])[12]"));
				eflowID.sendKeys(eID);	
				result.print("Entered eFLOW ID", "Pass");
				driver.findElement(By.xpath("//div[@class='ng-scope layout-row flex clickablelink']")).click();
				result.print("Clicked on Invoice Number", "Pass");
				
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"validate-toolbar-left\"]/div/button[5]/div[1]/span[1]/i")));
				Thread.sleep(10000);
				driver.findElement(By.xpath("//*[@id=\"validate-toolbar-left\"]/div/button[5]/div[1]/span[1]/i")).click();
				result.print("Clicked on Forward Workflow", "Pass");
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//input[contains(@class,'select-dropdown')]")));
				driver.findElement(By.xpath("//div//input[contains(@class,'select-dropdown')]")).click();
				driver.findElement(By.xpath("//ul//li//span[text()='None']")).click();
			
				driver.findElement(By.xpath("//input[@id='autocomplete-input']")).sendKeys(Recipient_ID);
				result.print("Entered/Selected Recipient for Forward Workflow "+Recipient_ID, "Pass");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='highlight']")));
				driver.findElement(By.xpath("//span[@class='highlight']")).click();
				
				driver.findElement(By.xpath("//textarea[@ng-model='forwardData.note']")).sendKeys("QaTest_Forward");
				WebElement btn_status = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
				if (btn_status.isEnabled()){
					result.print("Workflow Forwarded OK button enabled", "Pass");
				}
				else {
					result.print("Workflow Forwarded OK button is not enabled", "Fail");
				}
				driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();	
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Refresh']")));
				result.print("Workflow Forwarded Successfully", "Pass");
			
			/*
			 * WebElement selectDropdown =
			 * driver.findElement(By.xpath("//select[@aria-label='RecipientTypeSelect']"));
			 * Select s = new Select(selectDropdown); s.selectByVisibleText("None");
			 */
			
			} catch(Exception e) {
				result.print("Failed to Forward the Workflow for the invoice- "+eID, "Fail");
				Assert.fail("Exception!");
			}

	}
		public void substitution_eFLOW(String userID, String Substitue_ID)throws InterruptedException{
			try {
				WebDriverWait wait=new WebDriverWait(driver,90);
				//ChangePassword
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[i[text()='person']]")));
				driver.findElement(By.xpath("//div[i[text()='person']]")).click();
				result.print("Clicked on profile icon on toolbar", "Pass");
				Thread.sleep(1000);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Change Password')]")));
				driver.findElement(By.xpath("//a[contains(text(),'Substitutions')]")).click();
				result.print("Clicked on Substitution link", "Pass");
				//driver.findElement(By.xpath("//input[@value='User']")).click();
				driver.findElement(By.xpath("//input[@id='autocomplete-input']")).sendKeys(Substitue_ID);
				result.print("Susccessfully Entered the Substitute user ID "+Substitue_ID, "Pass");
				driver.findElement(By.xpath("//span[@class='highlight']")).click();
				result.print("Susccessfully selected the Substitute user ID "+Substitue_ID, "Pass");
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(text(),'save')]")));
				 // Create object of SimpleDateFormat class and decide the format
				 DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				 //get current date time with Date()
				 Date date = new Date();
				 String frmDate = dateFormat.format(date);
				 Calendar c = Calendar.getInstance();
				 c.setTime(new Date()); 
				 c.add(Calendar.DATE, 5);
				 String toDate = dateFormat.format(c.getTime());
				 
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(text(),'save')]")));
				driver.findElement(By.xpath("//i[contains(text(),'save')]")).click();
				Thread.sleep(100);
				/*String textmsg = driver.findElement(By.className("toast-title")).getText();
				System.out.println("TextMessage "+textmsg);
				*/
				try 
				{ 
					
					driver.switchTo().alert();
					wait.until(ExpectedConditions.alertIsPresent());
				    System.out.println(" Alert Present");
				}  
				catch (NoAlertPresentException e) 
				{ 
				    System.out.println("No Alert Present");
				} 
				
				Thread.sleep(10000);

				WebElement fromDt = driver.findElement(By.xpath("//div[@class='subsitutionDialogtableRow ng-scope layout-row']//input[@placeholder='Enter start date']"));
				fromDt.clear();
				fromDt.sendKeys(frmDate);			
				result.print("Entered start date "+frmDate, "Pass");
				WebElement toDt = driver.findElement(By.xpath("//div[@class='subsitutionDialogtableRow ng-scope layout-row']//input[@placeholder='Enter End date']"));
				toDt.clear();
				toDt.sendKeys(toDate);				
				result.print("Entered End date "+toDate, "Pass");
				
				driver.findElement(By.xpath("//div[@class='subsitutionDialogtableRow ng-scope layout-row']//div[@class='md-thumb md-ink-ripple']")).click();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(text(),'save')]")));
				
				WebElement btn_status = driver.findElement(By.xpath("//i[contains(text(),'save')]"));
				if (btn_status.isEnabled()){
					result.print("Substitute user Save button is enabled", "Pass");
				}
				else {
					result.print("Substitute user Save button is Not enabled", "Fail");
				}
								
				driver.findElement(By.xpath("//i[contains(text(),'save')]")).click();
				result.print("Susccessfully asigned Substitute user "+Substitue_ID+" to user "+userID, "Pass");
				
				//a[contains(text(),'Substitutions')]
				//a[contains(text(),'Change Password')]
				//a[contains(text(),'Sign Out')]
				//p[contains(text(),'Help')]

			} catch(Exception e) {
				result.print("Failed to asign Substitute user- "+Substitue_ID+" to usr "+userID, "Fail");
				Assert.fail("Exception!");
			}

	}
		public void ChangePassword_eFLOW(String OldPassword, String NewPassword)throws InterruptedException{
			try {
				WebDriverWait wait=new WebDriverWait(driver,90);
				//
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[i[text()='person']]")));
				driver.findElement(By.xpath("//div[i[text()='person']]")).click();
				result.print("Clicked on profile icon on toolbar", "Pass");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Change Password')]")));
				Thread.sleep(1000);
				driver.findElement(By.xpath("//a[contains(text(),'Change Password')]")).click();
				result.print("Clicked on Change Password link", "Pass");
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='oldPassword']")));								
				driver.findElement(By.xpath("//input[@id='oldPassword']")).sendKeys(OldPassword);
				result.print("Susccessfully Entered the Old password ","Pass");
				driver.findElement(By.xpath("//input[@id='newPassword']")).sendKeys(NewPassword);
				result.print("Susccessfully Entered the new password ","Pass");
				driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys(NewPassword);
				result.print("Susccessfully Entered the confirmPassword","Pass");
								
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//i[contains(text(),'save')]")));
				
				WebElement btn_status = driver.findElement(By.xpath("//i[contains(text(),'save')]"));
				if (btn_status.isEnabled()){
					result.print("Substitute user Save button is enabled", "Pass");
				}
				else {
					result.print("Substitute user Save button is Not enabled", "Fail");
				}
								
				driver.findElement(By.xpath("//i[contains(text(),'save')]")).click();
				result.print("Susccessfully changed the password", "Pass");

			} catch(Exception e) {
				result.print("Failed to change the password","Fail");
				Assert.fail("Exception!");
			}

	}		

		public void emailNotification(String UserName, String pwd)throws InterruptedException{
			try {
				WebDriverWait wait=new WebDriverWait(driver,90);
				result.print("Launched webOutLook", "Pass");
				//driver.findElement(By.xpath("//div[text()='Use another account']")).click();
				Thread.sleep(10000);
				//WebElement userName = driver.findElement(By.xpath("//input[@id='i0116']"));
				//userName.sendKeys("terst123");
				
				DateTimeFormatter dformat = DateTimeFormatter.ofPattern("M/DD/YYYY");
				LocalDateTime now = LocalDateTime.now();
				System.out.println("Datelocaal "+dformat.format(now));
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='i0116']")));
				/*WebMail accountLogin = new WebMail(null);
						accountLogin.webMailLogin(UserName, pwd);
					*/
				
				driver.findElement(By.xpath("//input[@id='i0116']")).sendKeys("zhanna.khananueva@kofax.com");
				driver.findElement(By.xpath("//div[input[@value='Next']]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//input[@id='i0118']")).sendKeys("Iwonesty@064");
				driver.findElement(By.xpath("//div[input[@value='Sign in']]")).click();
				driver.findElement(By.xpath("//div[input[@value='Yes']]")).click();
				result.print("successfully logged into outlook webmail", "Pass");
							
				
				//input[@value='Back']
							    
				/*
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Search']")));
				driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
				//WebElement eflowID = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/md-content[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[2]/input[1]"));
				WebElement eflowID = driver.findElement(By.xpath("(//md-content//div//input[@name='filter'])[11]"));
				eflowID.sendKeys(eID);	
				result.print("Entered eFLOW ID", "Pass");
				driver.findElement(By.xpath("//div[@class='ng-scope layout-row flex clickablelink']")).click();
				result.print("Clicked on Invoice Number", "Pass");
				
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[span[contains(text(),'Forward workflow')]]")));
				Thread.sleep(10000);
				driver.findElement(By.xpath("//div[span[contains(text(),'Forward workflow')]]")).click();
				result.print("Clicked on Forward Workflow", "Pass");
				
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//input[contains(@class,'select-dropdown')]")));
				driver.findElement(By.xpath("//div//input[contains(@class,'select-dropdown')]")).click();
				driver.findElement(By.xpath("//ul//li//span[text()='None']")).click();
			
				driver.findElement(By.xpath("//input[@id='autocomplete-input']")).sendKeys(Recipient_ID);
				result.print("Entered/Selected Recipient for Forward Workflow "+Recipient_ID, "Pass");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='highlight']")));
				driver.findElement(By.xpath("//span[@class='highlight']")).click();
				
				driver.findElement(By.xpath("//textarea[@ng-model='forwardData.note']")).sendKeys("QaTest_Forward");
				WebElement btn_status = driver.findElement(By.xpath("//button[contains(text(),'OK')]"));
				if (btn_status.isEnabled()){
					result.print("Workflow Forwarded OK button enabled", "Pass");
				}
				else {
					result.print("Workflow Forwarded OK button is not enabled", "Fail");
				}
				driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();	
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Refresh']")));
				result.print("Workflow Forwarded Successfully", "Pass");
			//***********************
				*/
			
			/*
			 * WebElement selectDropdown =
			 * driver.findElement(By.xpath("//select[@aria-label='RecipientTypeSelect']"));
			 * Select s = new Select(selectDropdown); s.selectByVisibleText("None");
			 */
			
			} catch(Exception e) {
				result.print("Failed to login to webmail "+UserName, "Fail");
				Assert.fail("Exception!");
			}

	}

		public void approveWorkFlowToo(SapDocument eID) {
			
			try {
				WebDriverWait wait=new WebDriverWait(driver,60);
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Search']")));
				driver.findElement(By.xpath("//button[@aria-label='Search']")).click();
				result.print("Clicked on Search", "Pass");
				Thread.sleep(10000);
				//WebElement eflowID = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/md-content[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[11]/div[1]/div[2]/input[1]"));
				WebElement eflowID = driver.findElement(By.xpath("(//md-content//div//input[@name='filter'])[12]"));
				//eflowID.sendKeys(eID);
				Thread.sleep(1000);
				result.print("Entered eFLOW ID "+eID, "Pass");
				
				if (driver.getPageSource().contains("ng-scope layout-row flex clickablelink")) {
					result.print("successfully searched the eFLOW DI "+eID, "Pass");
					driver.findElement(By.xpath("//div[@class='ng-scope layout-row flex clickablelink']")).click();
					result.print("Clicked on Invoice Number", "Pass");
				}else {
					result.print("Searched eFLOW DI "+eID+" does not exist in My Workflow list", "Fail");
					System.out.println("tesPass");
				}
		
				//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[span[contains(text(),'Approve')]]")));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[span[contains(text(),'')]]")));
				Thread.sleep(10000);
				//driver.findElement(By.xpath("//div[span[contains(text(),'Approve')]]")).click();
				driver.findElement(By.xpath("//i[contains(text(),'check')]")).click();
				result.print("Clicked on Approve Workflow button on tool bar " + eID, "Pass");
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Refresh']")));
				Thread.sleep(10000);
				result.print("Approved Workflow successfully for the invoice " + eID, "Pass");
			} catch (Exception e) {
				result.print("Failed to Approve WorkFlow", "Fail");
			}

		}
			
		}

