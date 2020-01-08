package com.pageobjectmodel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.tis.testcase.Init;

public class ExperienceEditor extends Init {
	public ExperienceEditor(WebDriver driver) {
		ExperienceEditor.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickPageLayout() throws InterruptedException {
		try {
			Thread.sleep(5000);
			driver.findElement(
					By.xpath("(//div[@class='scEnabledChrome scEmptyPlaceholder'])[2]"))
					.click();
			driver.findElement(By.xpath("//span[text()='Add here']")).click();
			Thread.sleep(5000);
			result.print("Clicked on Add Here button", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Add Here button", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void selectSubPageLayout(int indexNumber)
			throws InterruptedException {
		Thread.sleep(3000);
		try {
			driver.findElement(
					By.xpath("(//div[@class='scImageContainer']//img)["
							+ indexNumber + "]")).click();
			result.print("Clicked on Sub Page Layout", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Sub Page Layout", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickCreateNewContent() throws InterruptedException {
		Thread.sleep(3000);
		try {
			driver.findElement(
					By.xpath("//a[@class='mode']//div//img[@id='CreateIcon']"))
					.click();
			result.print("Clicked on Create New Component button", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Create New Component button",
					"Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickOnSave() throws InterruptedException {
		try {
			Thread.sleep(8000);
			driver.findElement(
					By.xpath("//a//img[contains(@alt,'Save changes.')]"))
					.click();
			Thread.sleep(25000);
			result.print("Clicked on Save", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Save", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickForgotUserID() throws InterruptedException {
		try {
			Thread.sleep(8000);
			driver.findElement(By.xpath("//div//span[text()='Forgot User ID']"))
					.click();
			Thread.sleep(25000);
			result.print("Clicked on Forgot User ID", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Forgot User ID", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickOnAddHere() {
		try {
			Thread.sleep(8000);
			driver.findElement(
					By.xpath("(//img[@alt='Add a new component.'])[1]"))
					.click();
			Thread.sleep(25000);
			result.print("Clicked on Add here", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Add here", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickOk() {
		try {
			Thread.sleep(8000);
			driver.findElement(By.xpath("//input[@value='OK']")).click();
			Thread.sleep(25000);
			result.print("Clicked on Ok", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Ok", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clicAddNewComponent() throws InterruptedException {
		try {
			Thread.sleep(8000);
			driver.findElement(By.xpath("//img[@alt='Add a new component.']"))
					.click();
			Thread.sleep(25000);
			result.print("Clicked on Add a new component.", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Add a new component.", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clicAddhere(int index) throws InterruptedException {
		try {
			Thread.sleep(8000);
			driver.findElement(
					By.xpath("(//span[text()='Add here'])[" + index + "]"))
					.click();
			Thread.sleep(25000);
			result.print("Clicked on Add here", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Add here", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void selectControlPropertiesDrop(String txt, String value) {
		try {
			WebElement vc = driver
					.findElement(By.xpath("//td[div[contains(text(),'" + txt
							+ "')]]//select"));
			Select s = new Select(vc);
			s.selectByVisibleText(value);
			result.print("selected value in control properties", "Pass");

		} catch (Exception e) {
			result.print("Failed to select in control properties " + txt,
					"Fail");
			Assert.fail("Exception! ");
		}
	}

	public void InputControlPropertiesText(String txt, String value) {
		try {
			WebElement vc = driver.findElement(By
					.xpath("//td[div[contains(text(),'" + txt + "')]]//input"));
			vc.clear();
			vc.sendKeys(value);
			result.print("given input value in control properties", "Pass");

		} catch (Exception e) {
			result.print("Failed to give input in control properties " + txt,
					"Fail");
			Assert.fail("Exception! ");
		}
	}

	public void CheckControlPropertiesBox(String txt) {
		try {
			WebElement vc = driver.findElement(By
					.xpath("//td[div[contains(text(),'" + txt
							+ "')]]//input[@type='checkbox']"));
			vc.click();
			result.print("checkbox checked in control properties", "Pass");
		} catch (Exception e) {
			result.print("Failed to check box in control properties " + txt,
					"Fail");
			Assert.fail("Exception! ");
		}
	}

	public void clickImageIcon() {
		try {
			driver.findElement(
					By.xpath("//img[@class='scEmptyImage scEnabledChrome']"))
					.click();
			result.print("clicked image icon", "Pass");
		} catch (Exception e) {
			result.print("Failed to clicke image icon", "Fail");
			Assert.fail("Exception! ");
		}

	}

	public void chooseImageOperation(String imgtxt, int imgindex)
			throws InterruptedException {
		try {
			WebElement e1 = driver.findElement(By
					.xpath("(//div[@class='scChromeControls'])[2]"));
			Thread.sleep(5000);
			e1.findElement(
					By.xpath("(//img[@alt='" + imgtxt + "'])[" + imgindex + "]"))
					.click();
			result.print("clicked chooseImageOperation", "Pass");
		} catch (Exception e) {
			result.print("Failed to click chooseImageOperation", "Fail");
			Assert.fail("Exception! ");
		}

	}

	public String choosehighlightImge(String txt, int imgindex) {
		String src = "";

		try {
			WebElement m = driver.findElement(By
					.xpath("//section[@class='sc-dialogContent-main']"));
			System.out.println("element chosen");

			m.findElement(
					By.xpath("(//div[div[@class='sc-iconList-item-title'][span[text()='"
							+ txt + "']]])[" + imgindex + "]/a/img")).click();

			src = m.findElement(
					By.xpath("(//div[div[@class='sc-iconList-item-title'][span[text()='"
							+ txt + "']]])[" + imgindex + "]/a/img"))
					.getAttribute("src").toString();
			result.print("clicked choosehighlightImge", "Pass");

		} catch (Exception e) {
			result.print("Failed to click choosehighlightImge", "Fail");
			Assert.fail("Exception! ");

		}
		return src;

	}

	public void clickFooterSelect() throws InterruptedException {
		try {
			WebElement bt = driver.findElement(By.xpath("//footer"));
			bt.findElement(By.xpath("//footer//button/span[text()='Select']"))
					.click();
			Thread.sleep(5000);
			result.print("clicked select button", "Pass");
		} catch (Exception e) {
			result.print("Failed to click Select", "Fail");
			Assert.fail("Exception! ");

		}

	}

	public void clickCaptionArea(int index) {
		driver.findElement(By.xpath("//div[@class='caption']"));
		System.out.println("found caption");
		try {
			driver.findElement(
					By.xpath("(//div[@class='caption'])[" + index + "]/div[2]"))
					.click();
			result.print("clicked on blank area", "Pass");

		} catch (Exception e) {
			result.print("unable to click on blank", "Fail");
			Assert.fail("Exception!!");
		}
	}

	public void clickHighlightButton(int index) {
		try {
			driver.findElement(
					By.xpath("(//div[contains(@class,'button')])[" + index
							+ "]/span[2]")).click();
			result.print("clicked on Highlight button", "Pass");

		} catch (Exception e) {
			result.print("unable to click on Highlight button", "Fail");
			Assert.fail("Exception!!");
		}

	}

	public void chooseDestinationOption(String opt) throws InterruptedException {
		try {
			WebElement e12 = driver.findElement(By
					.xpath("//div[@class='left-column']"));
			System.out.println("entering img");
			Thread.sleep(5000);

			e12.findElement(By.xpath("//div[text()='" + opt + "']")).click();
			result.print("choosen Destination option ", "Pass");
		} catch (Exception e) {
			result.print("not choosen Destination option", "Fail");
			Assert.fail("Exception!!");
		}
	}

}
