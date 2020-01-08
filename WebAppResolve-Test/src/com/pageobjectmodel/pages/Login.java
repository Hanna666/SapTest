package com.pageobjectmodel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.tis.testcase.Init;

public class Login extends Init{
	public Login(WebDriver driver) {
		Login.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void login(String un,String pwd)
	{
		try{
			WebDriverWait wait=new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userName']")));
		    
			driver.findElement(By.xpath("//input[@id='userName']")).clear();
			driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(un);
			driver.findElement(By.xpath("//input[@id='password']")).clear();
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pwd);
			result.print("Username and Password entered! "+un+"/"+"*****", "Pass");
			driver.findElement(By.xpath("//button[text()='Login']")).click();

			result.print("Login to WebApp Resolve is successful ! "+un+"/"+"*****", "Pass");
		}
		catch (Exception e) {
			result.print("Username/Password is incorrect.", "Fail");
			Assert.fail("Exception!");
		}
		
	}
	
  public void logout() throws InterruptedException {

		try{
			WebDriverWait wait=new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[i[text()='person']]")));
			driver.findElement(By.xpath("//div[i[text()='person']]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			result.print("Clicked on Logout", "Pass");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userName']")));
			cleanup();
			result.print("Closed the Browser", "Pass");
			}
			catch (Exception e) {
				result.print("Failed to click on Logout", "Fail");
				Assert.fail("Exception!");
			}
		}



public void login_invalidCredentials(String un,String pwd) {
		try{
			WebDriverWait wait=new WebDriverWait(driver,60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userName']")));
		    
			driver.findElement(By.xpath("//input[@id='userName']")).clear();
			driver.findElement(By.xpath("//input[@id='userName']")).sendKeys(un);
			driver.findElement(By.xpath("//input[@id='password']")).clear();
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pwd+"1");
			result.print("Username and Password entered! "+un+"/"+"*****1", "Pass");
			driver.findElement(By.xpath("//button[text()='Login']")).click();
			Thread.sleep(1000);
			result.print("Name or password is incorrect(repeat logon) "+un+"/"+"*****1", "Pass");
			cleanup();
		}
		catch (Exception e) {
			result.print("Username/Password is incorrect.", "Fail");
			Assert.fail("Exception!");
		}
	
}	

}
