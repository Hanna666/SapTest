package com.pageobjectmodel.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.tis.testcase.Init;

public class FrontEnd extends Init {
	public FrontEnd(WebDriver driver) {
		FrontEnd.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyTextBox(String label, int i) {
		try {
			WebElement v = driver.findElement(By
					.xpath("(//label[contains(text(),'" + label
							+ "')]/ancestor::div/div/input)[" + i + "]"));
			if (v.isDisplayed()) {
				result.print("Text Box present - " + label, "Pass");
			}
		} catch (Exception e) {
			result.print("Not Present textbox - " + label, "Fail");
		}
	}

	public int returnSizeOfFinfElements(String name) {
		try {
			List<WebElement> v = driver.findElements(By.xpath("//input[@id='"
					+ name + "']"));
			int l = v.size();
			result.print("Text Box present - " + name, "Pass");
			return l;
		} catch (Exception e) {
			result.print("Not Present textbox - " + name, "Fail");
			return 0;
		}

	}

	public void verifyTextArea(String label, int i) {
		try {
			WebElement v = driver.findElement(By
					.xpath("(//label[contains(text(),'" + label
							+ "')]/ancestor::div/div/textarea)[" + i + "]"));
			if (v.isDisplayed()) {
				result.print("Text Area present - " + label, "Pass");
			}
		} catch (Exception e) {
			result.print("Not Present Text Area - " + label + "' textarea",
					"Fail");
		}
	}

	public void verifyDropDown(String label, int i) {
		try {
			WebElement v = driver.findElement(By
					.xpath("(//label[contains(text(),'" + label
							+ "')]/ancestor::div/div/select)[" + i + "]"));
			if (v.isDisplayed()) {
				result.print("Drop down present - " + label, "Pass");
			}
		} catch (Exception e) {
			result.print("Drop down Not Present  - " + label, "Fail");
		}
	}

	public void verifyButton(String btnName) {
		try {
			WebElement v = driver.findElement(By.xpath("//input[@value='"
					+ btnName + "']"));
			if (v.isDisplayed()) {
				result.print("Button present - " + btnName, "Pass");
			}
		} catch (Exception e) {
			result.print("Not Present Button - " + btnName, "Fail");
		}
	}

	public void EnterRegTextBox(String path1, String data) {
		try {
			driver.findElement(By.xpath(".//input[@id='txt" + path1 + "']"))
					.sendKeys(data);
			result.print("Value Entered - " + path1, "Pass");
		} catch (Exception e) {
			result.print("Value not entered - " + path1, "Fail");
			Assert.fail("Exception!");
		}

	}

	public void SelectDropDown(String name, String value)
			throws InterruptedException {
		try {
			WebElement vc = driver.findElement(By.xpath(".//select[@name='"
					+ name + "']"));
			Select s = new Select(vc);
			s.selectByVisibleText(value);
			result.print("Value Selected - " + value, "Pass");
		} catch (Exception e) {
			result.print("Value not selected - " + value, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void FillRegistrationForm(String reguser, String newPswd,
			String Fname, String Lname, String gmailLog, String compName,
			String jobTitle, String phn, String city, String state,
			String cntry, String postalcde, String q1, String a1, String q2,
			String a2, String q3, String a3) throws InterruptedException {
		try {
			EnterRegTextBox("Username", reguser);
			// password
			EnterRegTextBox("NewPassword", newPswd);
			// confirm password
			EnterRegTextBox("ConfirmationPassword", newPswd);
			// First name
			EnterRegTextBox("FirstName", Fname);
			// Last name
			EnterRegTextBox("LastName", Lname);
			// Email
			EnterRegTextBox("NewEmail", gmailLog);

			EnterRegTextBox("ConfirmEmail", gmailLog);
			/*
			 * // company EnterRegTextBox("CompanyName", compName); // job title
			 * EnterRegTextBox("JobTitle", jobTitle); // phone
			 * EnterRegTextBox("WorkPhoneNo", phn); // city
			 * EnterRegTextBox("City", city); // state EnterRegTextBox("State",
			 * state);
			 */// country
			SelectDropDown("Country", cntry);

			EnterRegTextBox("PostalCode", postalcde);

			SelectDropDown("SelectQuestion1", q1);
			EnterRegTextBox("Answer1", a1);
			EnterRegTextBox("ConfirmAnswer1", a1);

			SelectDropDown("SelectQuestion2", q2);
			EnterRegTextBox("Answer2", a2);
			EnterRegTextBox("ConfirmAnswer2", a2);

			SelectDropDown("SelectQuestion3", q3);
			EnterRegTextBox("Answer3", a3);
			EnterRegTextBox("ConfirmAnswer3", a3);
			Thread.sleep(50000);
			driver.findElement(By.xpath(".//*[@id='PrivacyAgree']")).click();
			driver.findElement(By.xpath(".//*[@id='btnRegisteration']"))
					.click();
			result.print("User Registered successfully.", "Pass");
		} catch (Exception e) {
			result.print("Registration Failed", "Fail");
			Assert.fail("Exception!");
		}

	}

	/*
	 * public void EnterFEUserName(String name) { try{
	 * driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys(name);
	 * result.print("UserName Entered - " + name, "Pass"); } catch (Exception e)
	 * { result.print("UserName not entered - " + name, "Fail");
	 * Assert.fail("Exception!"); }
	 * 
	 * }
	 */

	public void EnterFEPassword(String name) {
		try {
			driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(
					name);
			result.print("UserName Entered - " + name, "Pass");
		} catch (Exception e) {
			result.print("UserName not entered - " + name, "Fail");
			Assert.fail("Exception!");
		}

	}

	/*
	 * public void clickFpSubmit() { try{
	 * driver.findElement(By.xpath("//input[@type='Submit']")).click();
	 * result.print("Clicked on login", "Pass"); } catch (Exception e) {
	 * result.print("Failed to Clicked on login", "Fail");
	 * Assert.fail("Exception!"); } }
	 */

	public void activateUser(String pwd) throws InterruptedException {
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath("//input[@id='txtPassword']"))
					.sendKeys(pwd);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			result.print("User Activated", "Pass");
		} catch (Exception e) {
			result.print("Failed to Activate user", "Fail");
			Assert.fail("Exception!");
		}

	}

	public void EnterFEUserName(String name) {
		try {
			driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys(
					name);
			result.print("UserName Entered - " + name, "Pass");
		} catch (Exception e) {
			try {
				driver.findElement(By.xpath("//input[@id='txtUsername']"))
						.sendKeys(name);
				result.print("UserName Entered - " + name, "Pass");

			} catch (Exception ae) {
				result.print("UserName not entered - " + name, "Fail");
				Assert.fail("Exception!");
			}
		}

	}

	public void clickFpSubmit() {
		try {
			driver.findElement(By.xpath("//input[@type='Submit']")).click();
			result.print("Clicked on login", "Pass");
		}

		catch (Exception e) {
			try {
				driver.findElement(By.xpath("//input[@type='submit']")).click();
			} catch (Exception ae) {
				result.print("Failed to Clicked on login", "Fail");
				Assert.fail("Exception!");
			}
		}
	}

	public void FillrecoverPassword(String q1, String q2, String q3, String a1,
			String a2, String a3, String pswd1, String pswd2)
			throws InterruptedException {
		String v = driver.findElement(By.xpath("//input[@id='Question']"))
				.getAttribute("value").toString();
		String answer = "";
		if (v.equals(q1)) {
			answer = a1;
		}
		if (v.equals(q2)) {
			answer = a2;
		}
		if (v.equals(q3)) {
			answer = a3;
		}

		EnterRegTextBox("Answer", answer);
		EnterRegTextBox("NewPassword", pswd1);
		EnterRegTextBox("ConfirmationPassword", pswd2);

	}

	public void verifyLinkText(String text) {
		try {
			String v = driver.findElement(
					By.xpath("//a[contains(text(),'" + text + "')]")).getText();
			if (v.contains(text)) {
				result.print("component is present in link" + text, "Pass");
			}
		} catch (Exception e) {
			result.print("component is present in link - " + text, "Fail");
			Assert.fail("Exception--FrontEnd Images not present");
		}
	}

	public void clickSubmitButton(String val) {
		try {
			driver.findElement(
					By.xpath("//input[@type='submit' and @value='" + val + "']"))
					.click();
			result.print("clicked on submit button", "Pass");
		} catch (Exception e) {
			result.print("not clicked on submit button", "Fail");
			Assert.fail("Exception!");

		}
	}

	public void clickSendEmail() {
		try {
			driver.findElement(By.xpath("//input[@value='Send Email']"))
					.click();
			result.print("Clicked on Send email", "Pass");
		}

		catch (Exception e) {
			result.print("Failed to Clicked on Send email", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void inputUserAndCode(String name, String code) {
		try {
			EnterRegTextBox("Username2", name);
			EnterRegTextBox("UnlockCode", code);
			result.print("Entered user name and code", "Pass");
		} catch (Exception e) {
			result.print("not Entered user name and code", "Fail");
			Assert.fail("Exception!");

		}
	}

	public void verifyImages(int num) {
		try {
			for (int i = 1; i <= num; i++) {
				driver.findElement(By
						.xpath("(//div[@class='slick-track']//div[@class='score-highlight'])["
								+ i + "]/img"));
				result.print("images are available in front end", "Pass");
			}
		} catch (Exception e) {
			result.print("images not present ", "Fail");
			Assert.fail("Exception--FrontEnd Images not present");
		}

	}

	public void verifyHighlights(int num) {
		try {
			for (int i = 1; i <= num; i++) {
				driver.findElement(By
						.xpath("(//div[@class='slick-track']//div[@class='score-highlight'])["
								+ i + "]/div/h2[contains(text(),'HighLight')]"));
				result.print("Highlights are available in front end", "Pass");
			}
		} catch (Exception e) {
			result.print("Highlights not present ", "Fail");
			Assert.fail("Exception--FrontEnd Highlights not present");
		}

	}

	public void verifyHighlightButtons(int num) {
		try {
			for (int i = 1; i <= num; i++) {
				driver.findElement(By
						.xpath("(//div[@class='slick-track']//div[@class='score-highlight'])["
								+ i + "]/div/div/a[contains(@class,'button')]"));
				result.print("Buttons are available in front end", "Pass");
			}
		} catch (Exception e) {
			result.print("Buttons not present ", "Fail");
			Assert.fail("Exception--FrontEnd Buttons not present");
		}
	}

	public void verifyCrousels() {
		try {
			driver.findElement(By
					.xpath("//button[contains(text(),'Previous')]"));
			driver.findElement(By.xpath("//button[contains(text(),'Next')]"));

			result.print("crousels are available in front end", "Pass");
		} catch (Exception e) {
			result.print("crousels not present ", "Fail");
			Assert.fail("Exception--FrontEnd crousels not present");
		}
	}

	public void verifyBrowseOutDateMsg() {
		try {
			driver.findElement(By
					.xpath("//h1[contains(text(),'Did you know that your Internet Browser is out of date?')]"));
			result.print("Message is available", "Pass");
		} catch (Exception e) {
			result.print("Message not present ", "Fail");
			Assert.fail("Exception--message not present");
		}
	}

}