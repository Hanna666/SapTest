package com.pageobjectmodel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tis.testcase.Init;

public class WebMail extends Init {

	public WebMail(WebDriver driver) {
		WebMail.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void webMailLogin(String user, String psd) {

		try {
			driver.findElement(By.xpath("//input[@id='i0116']")).sendKeys("ramesh.nayak@topimagesystems.com");
			driver.findElement(By.xpath("//div[input[@value='Next']]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@id='i0118']")).sendKeys("Iwonesty@064");
			driver.findElement(By.xpath("//div[input[@value='Sign in']]")).click();
			driver.findElement(By.xpath("//div[input[@value='Yes']]")).click();
			result.print("successfully logged into outlook webmail", "Pass");
			
		} catch (Exception e) {
			result.print("Failed to Login Mail Account", "Fail");
			Assert.fail("Exception!");
		}

	}

	public void searchEmail(String from, String reguser)
			throws InterruptedException {
		while (true) {
			try {
				WebElement m = driver.findElement(By
						.xpath("//tr[td[div[span[@email='" + from
								+ "']]]]/td//span[contains(text(),'" + reguser
								+ "')]"));
WebElement m1=driver.findElement(By.xpath("//tr[td[div[span[@email='" + from + "']]]]/td//span[contains(text(),'" + reguser+ "')]"));
				Actions action1 = new Actions(driver);
				action1.moveToElement(m);
				System.out.println("move successfull");
				action1.doubleClick(m).build().perform();
				result.print("Clicked on Mail", "Pass");
				break;
			} catch (Exception e) {
				result.print("Failed to Click on Mail", "Fail");
				Assert.fail("Exception!");
			}
		}
	}

	public void clickUserActivationLink() {
		try {
			driver.findElement(By.xpath("//a[contains(@href,'cloudapp.net')]"))
					.click();
			result.print("Clicked on UserActivationLink", "Pass");
		} catch (Exception e) {
			result.print("Failed to Click on UserActivationLink", "Fail");
			Assert.fail("Exception!");
		}

	}

	public void VerifyUserLoggedInSiteInMail(String userID) {
		try {
			driver.findElement(By
					.xpath("//div[contains(text(),'A new user extranet\\"
							+ userID
							+ " has logged into the website tenantone.')]"));
			result.print("First time login verified", "Pass");
		} catch (Exception e) {
			try {
				driver.findElement(By
						.xpath("//div[contains(text(),'A new user Employee\\"
								+ userID
								+ " has logged into the website tenantone.')]"));
				result.print("First time login verified", "Pass");
			} catch (Exception e1) {
				result.print("Failed to verify First time login ", "Fail");
				Assert.fail("Exception!");
			}
		}
	}

	public void gmailSingout() {
		try {
			driver.findElement(
					By.xpath("//a[contains(@href,'SignOutOptions')]")).click();
			driver.findElement(By.xpath("//a[contains(@href,'Logout')]"))
					.click();
			result.print("user singout from gamil", "Pass");
		} catch (Exception e) {
			result.print("Failed to singout from gamil", "Fail");
			Assert.fail("Exception!");
		}

	}
}
