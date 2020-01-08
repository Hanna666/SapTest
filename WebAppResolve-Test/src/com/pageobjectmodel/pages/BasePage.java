package com.pageobjectmodel.pages;

import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tis.testcase.Init;



public class BasePage extends Init {
	public BasePage(WebDriver driver) {
		BasePage.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Get Text
	public void getTextLi(String name) throws InterruptedException {
		try {
			String v = driver.findElement(
					By.xpath("//li[contains(text(),'" + name + "')]"))
					.getText();
			if (v.contains(name)) {
				result.print("Verified Name - " + name, "Pass");
			}
		} catch (Exception e) {
			result.print("Name not Present - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void getTextLabel(String name) throws InterruptedException {
		try {
			Thread.sleep(3000);
			String v = driver.findElement(
					By.xpath("//label[contains(text(),'" + name + "')]"))
					.getText();
			if (v.equalsIgnoreCase(name)) {
				result.print("Verified text - " + name, "Pass");
			}
		} catch (Exception e) {
			result.print("Text not Present - " + name, "Fail");
			//Assert.fail("Exception!");
		}
	}

	public void getTextH1Div(String name) throws InterruptedException {
		try {
			Thread.sleep(3000);
			String v = driver.findElement(
					By.xpath("////div//h1[contains(text(),'" + name + "')]"))
					.getText();
			if (v.equalsIgnoreCase(name)) {
				result.print("Verified text - " + name, "Pass");
			}
		} catch (Exception e) {
			result.print("Text not Present - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void getTextA(String name) throws InterruptedException {
		try {
			Thread.sleep(3000);
			String v = driver.findElement(
					By.xpath("//a[contains(text(),'" + name + "')]")).getText();
			if (v.equalsIgnoreCase(name)) {
				result.print("Verified text - " + name, "Pass");
			}
		} catch (Exception e) {
			result.print("Text not Present - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}
	
	public void getTextSpan(String name) throws InterruptedException {
		try {
			Thread.sleep(3000);
			String v = driver.findElement(
					By.xpath("//span[contains(text(),'" + name + "')]")).getText();
			if (v.equalsIgnoreCase(name)) {
				result.print("Verified text - " + name, "Pass");
			}
		} catch (Exception e) {
			result.print("Text not Present - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	// Set Value

	public void setName(String name) throws InterruptedException {
		try {

			driver.findElement(By.xpath("//input[@id='ItemName']")).clear();
			driver.findElement(By.xpath("//input[@id='ItemName']")).sendKeys(
					name);
			result.print("Value Entered - " + name, "Pass");

		} catch (Exception e) {
			result.print("Value not entered - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void setName_01(String name, String value)
			throws InterruptedException {
		try {

			driver.findElement(By.xpath("//input[@id='" + name + "']")).clear();
			driver.findElement(By.xpath("//input[@id='" + name + "']"))
					.sendKeys(value);
			result.print("Value Entered - " + name, "Pass");

		} catch (Exception e) {
			result.print("Value not entered - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void setText(String id, String name) throws InterruptedException {
		try {

			driver.findElement(By.xpath("//input[@id='" + id + "']")).clear();
			driver.findElement(By.xpath("//input[@id='" + id + "']")).sendKeys(
					name);
			result.print("Value Entered - " + name, "Pass");

		} catch (Exception e) {
			result.print("Value not entered - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	// Switching Frames
	public void switchFrame() throws InterruptedException {

		try {
			driver.switchTo().frame("jqueryModalDialogsFrame");
			driver.switchTo().frame("scContentIframeId0");
			result.print("Switched to Frame", "Pass");
		} catch (Exception e) {
			result.print("Failed to switch Frame", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void switchOutOfFrame() {
		try {
			driver.switchTo().defaultContent();
			result.print("Switched out of Frame", "Pass");
		} catch (Exception e) {
			result.print("Failed to sitch Frame", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void switchFrame1() throws InterruptedException {
		Thread.sleep(3000);
		try {
			driver.switchTo().frame("scWebEditRibbon");
			result.print("Switched to Frame", "Pass");
		} catch (Exception e) {
			result.print("Failed to sitch Frame", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void switchFrame2() throws InterruptedException {
		try {
			Thread.sleep(3000);
			driver.switchTo().frame("jqueryModalDialogsFrame");
			driver.switchTo().frame("scContentIframeId1");
			result.print("Switched to Frame", "Pass");
		} catch (Exception e) {
			result.print("Failed to sitch Frame", "Fail");
			Assert.fail("Exception!");
		}
	}
	
	public void switchFrame3() throws InterruptedException {
		try {
		Thread.sleep(3000);
		driver.switchTo().frame("jqueryModalDialogsFrame");
		driver.switchTo().frame("scContentIframeId1");
		result.print("Switched to Frame", "Pass");
		} catch (Exception e) {
			result.print("Failed to sitch Frame", "Fail");
			Assert.fail("Exception!");
		}

	}

	// Click Functions
	public void clickSpan(String name) throws InterruptedException {
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//span[text()='" + name + "']"))
					.click();
			result.print("Clicked on Element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickSpan_01(String name, int index)
			throws InterruptedException {
		Thread.sleep(5000);
		try {
			driver.findElement(
					By.xpath("(//span[text()='" + name + "'])[" + index + "]"))
					.click();
			result.print("Clicked on Element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickInput_01(String name) throws InterruptedException {
		Thread.sleep(5000);
		try {
			driver.findElement(By.xpath("(//input[@value='" + name + "'])"))
					.click();
			result.print("Clicked on Element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickYes() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//button[text()='Yes']")).click();
			Thread.sleep(3000);
			result.print("Clicked on Element - Yes", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - Yes", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickInputButton(String val) {
		try {
			driver.findElement(
					By.xpath("//input[@type='button' and @value='" + val + "']"))
					.click();
			result.print("Clicked on Input button ", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Input button ", "Fail");
			Assert.fail("Exception!");
		}
	}

	// Buttons

	public void clickButtonInsert() throws InterruptedException {

		try {
			driver.findElement(By.xpath("//button[text()='Insert']")).click();
			result.print("Clicked on Element - Insert", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - Insert", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickSelect() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//button[text()='Select']")).click();
			result.print("Clicked on Element - Select", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - Select", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickOK() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//button[text()='OK']")).click();
			Thread.sleep(3000);
			result.print("Clicked on Element - OK", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - OK", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickClose() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//button[text()='Close']")).click();
			result.print("Clicked on Element - Close", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - Close", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickPublishButton() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//button[text()='Publish']")).click();
			Thread.sleep(3000);
			result.print("Clicked on Element - Publish", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - Publish", "Fail");
			Assert.fail("Exception!");
		}
	}
	
	public void clickCancelButton() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//button[text()='Cancel']")).click();
			result.print("Clicked on Element - Select", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - Cancel", "Fail");
			Assert.fail("Exception!");
		}
	}


	// Action class
	public void actionMethodToRtClick(String name, int index) {
		try {
			result.print("Rt Clicked on Element - Insert", "Pass");
			WebElement element;
			if (index == 1) {
				element = driver.findElement(By.xpath("//span[text()='" + name
						+ "']"));
			} else {
				element = driver.findElement(By.xpath("(//span[text()='" + name
						+ "'])[3]"));
			}
			Actions action = new Actions(driver);
			action.contextClick(element).build().perform();
			result.print("Click on Element - Insert", "Pass");
		} catch (Exception e) {
			result.print("Failed to rt click on Element - Insert", "Fail");
			Assert.fail("Exception!");
		}
	}

	// Unlock Component
	public void unlockTemplate() throws InterruptedException {
		Thread.sleep(4000);
		try {
			driver.findElement(
					By.xpath("//img[contains(@title,'Locked by admin')]"))
					.click();
			result.print("Unlocked the Component", "Pass");
		} catch (Exception e) {
			result.print("Failed to unlock the component", "Fail");
			Assert.fail("Exception!");
		}
	}

	// Switch Window
	public void switchWindow() throws InterruptedException {
		try {
			Iterator<String> wh = driver.getWindowHandles().iterator();
			Thread.sleep(5000);
			String parent = wh.next();
			String child = wh.next();

			driver.switchTo().window(child);
			Thread.sleep(3000);
			String title = driver.getTitle();
			System.out.println("Title : " + title);
			result.print("Switched Window", "Pass");
		} catch (Exception e) {
			result.print("Failed to Switch Window", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void switchBackToParentWindow() throws InterruptedException {
		try {
			Iterator<String> wh = driver.getWindowHandles().iterator();
			String parent = wh.next();
			// String child = wh.next();
			Thread.sleep(5000);
			driver.switchTo().window(parent);
			Thread.sleep(5000);
			String title = driver.getTitle();
			System.out.println("Title : " + title);
			result.print("Switched Window", "Pass");
		} catch (Exception e) {
			result.print("Failed to Switch Window", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void switchWindowChild2() throws InterruptedException {
		try {
			Iterator<String> wh = driver.getWindowHandles().iterator();
			Thread.sleep(5000);
			String parent = wh.next();
			String child = wh.next();
			String child1 = wh.next();

			driver.switchTo().window(child1);
			Thread.sleep(5000);
			String title = driver.getTitle();
			System.out.println("Title : " + title);
			result.print("Switched Window", "Pass");
		} catch (Exception e) {
			result.print("Failed to Switch Window", "Fail");
			Assert.fail("Exception!");
		}
	}

	// Refresh

	public void clickRefresh(String folderName, String text) {
		try {
			WebElement v = driver.findElement(By.xpath("//span[text()='"
					+ folderName + "']"));
			Actions action = new Actions(driver);
			action.contextClick(v).build().perform();

			driver.findElement(By.xpath("//td[text()='" + text + "']")).click();
			result.print("Refreshed Window", "Pass");
		} catch (Exception e) {
			result.print("Failed to Refresh", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clearBrowserCache() {
		try {
			driver.manage().deleteAllCookies();
			driver.navigate().refresh();
			result.print("Cleared browser cache", "Pass");
		} catch (Exception e) {
			result.print("Failed to clear browser cache", "Fail");
			Assert.fail("Exception!");
		}
	}

	// Xpath
	public void getXpath(String xpath, String name) throws InterruptedException {
		try {
			Thread.sleep(3000);
			System.out.println(name);
			String v = driver.findElement(By.xpath("+xpath+")).getText();
			System.out.println(v);
			if (v.equalsIgnoreCase(name)) {
				result.print("Verified text - " + name, "Pass");
			}
		} catch (Exception e) {
			result.print("Text not Present - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	// refresh
	public void browserRefresh() {
		driver.navigate().refresh();
	}

	public void clickCancel() throws InterruptedException {
		try {
			driver.findElement(By.xpath("//button[text()='Cancel']")).click();
			result.print("Clicked on Element - Cancel", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - Cancel", "Fail");
			Assert.fail("Exception!");
		}
	}

}
