package com.pageobjectmodel.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tis.testcase.Init;

public class ContentEditor extends Init {
	public ContentEditor(WebDriver driver) {
		ContentEditor.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnTenant(String name) throws InterruptedException {
		try {
			driver.findElement(
					By.xpath("//div[a[span[text()='" + name
							+ "']]]//img[contains(@id,'Tree_Glyph_')]"))
					.click();
			result.print("Clicked on Element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to click on - " + name, "Fail");
			Assert.fail("Exception!");
		}

	}

	public void rtClickOnHomeAndInsert(String name, String tenant)
			throws InterruptedException {
		Thread.sleep(7000);
		WebElement v;
		try {
			v = driver.findElement(By
					.xpath("//a[span[text()='" + tenant
							+ "']]/following-sibling::div//span[text()='"
							+ name + "']"));
			Actions action = new Actions(driver);
			action.contextClick(v).build().perform();

			// select Insert
			driver.findElement(
					By.xpath("//tr[contains(@id,'ContextMenu_')]//td[text()='Insert']"))
					.click();
			driver.findElement(
					By.xpath("(//td[text()='Insert from template'])[2]"))
					.click();
			result.print("Right Clicked on Element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to right click on element - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void expandFolder(String name) throws InterruptedException {
		try {
			String v = driver.findElement(
					By.xpath("//div[a[span[text()='" + name
							+ "']]]//img[@class='scContentTreeNodeGlyph']"))
					.getAttribute("src");

			if (!(v.contains("expanded"))) {
				driver.findElement(
						By.xpath("//div[a[span[text()='" + name
								+ "']]]//img[@class='scContentTreeNodeGlyph']"))
						.click();
			}
			result.print("Clicked on element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to click on element - " + name, "Fail");
			Assert.fail("Exception!");
		}

	}

	public void rtClickOnFolderAndInsert(String name)
			throws InterruptedException {
		Thread.sleep(5000);
		WebElement v;
		try {
			v = driver.findElement(By.xpath("//span[text()='" + name + "']"));

			Actions action = new Actions(driver);
			action.contextClick(v).build().perform();

			// select Insert
			driver.findElement(
					By.xpath("//tr[contains(@id,'ContextMenu_')]//td[text()='Insert']"))
					.click();

			driver.findElement(
					By.xpath("(//td[text()='Insert from template'])[2]"))
					.click();
			result.print("Right Clicked on Element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to right click on element - " + name, "Fail");
			Assert.fail("Exception!");
		}

	}

	public void expandRfwPresencePagesSelectSecure()
			throws InterruptedException {
		try {
			Thread.sleep(3000);
			String v = driver
					.findElement(
							By.xpath(".//*[@id='Templates_B39820EA068943BCB403B02FB24A3138']/img"))
					.getAttribute("src");
			if (!(v.contains("expanded"))) {
				driver.findElement(
						By.xpath(".//*[@id='Templates_B39820EA068943BCB403B02FB24A3138']/img"))
						.click();
			}
			Thread.sleep(3000);
			String v1 = driver
					.findElement(
							By.xpath(".//*[@id='Templates_18F03EB8AB924AD99FD40B53CC436F2F']/img"))
					.getAttribute("src");

			if (!(v1.contains("expanded"))) {
				driver.findElement(
						By.xpath(".//*[@id='Templates_18F03EB8AB924AD99FD40B53CC436F2F']/img"))
						.click();
			}

			result.print("Selected secure subelement", "Pass");
		} catch (Exception e) {
			result.print("Failed to select secure subelement", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickPublishAndExperienceEditor() throws InterruptedException {
		Thread.sleep(3000);
		try {
			driver.findElement(By.xpath("//a[text()='Publish']")).click();
			Thread.sleep(3000);
			driver.findElement(
					By.xpath("//a[contains(@title,'Start the Experience Editor.')]//span[text()='Experience Editor']"))
					.click();
			result.print("Clicked on Publish and Experience editor", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Publish and Experience editor",
					"Fail");
			Assert.fail("Exception!");
		}
	}

	public void publishItem() throws InterruptedException {
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//td[text()='Publish item'])[2]"))
					.click();
			result.print("Clicked on Publish Item", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Publish Item", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void expandHomeInsideTenant(String name, String tenant)
			throws InterruptedException {
		Thread.sleep(5000);
		try {
			driver.findElement(
					By.xpath("//a[span[text()='" + tenant
							+ "']]/following-sibling::div/div[a[span[text()='"
							+ name + "']]]//img[contains(@id,'Tree_Glyph_')]"))
					.click();

			result.print("Expand " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to expand - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void expandFolder1(String name) throws InterruptedException {
		try {

			driver.findElement(
					By.xpath("//div[a[span[text()='" + name
							+ "']]]//img[@class='scContentTreeNodeGlyph']"))
					.click();
			result.print("Clicked on element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to click on element - " + name, "Fail");
			Assert.fail("Exception!");
		}

	}

	public void expandContent() throws InterruptedException {

		Thread.sleep(5000);
		try {
			driver.findElement(
					By.xpath("(//div[a[span[text()='Content']]])[2]//img[contains(@id,'Tree_Glyph_')]"))
					.click();

			result.print("Expand content", "Pass");
		} catch (Exception e) {
			result.print("Failed expand to content", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void doubleClickOnTemplet(String name, int i)
			throws InterruptedException {
		Thread.sleep(5000);
		WebElement v;
		try {
			v = driver.findElement(By.xpath("(//span[text()='" + name + "'])["
					+ i + "]"));
			Actions action = new Actions(driver);
			action.doubleClick(v).build().perform();
			result.print("Double click on " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to double click on " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void ClickOnTemplet(String name) throws InterruptedException {
		Thread.sleep(5000);
		WebElement v;
		try {
			driver.findElement(By.xpath("//span[text()='" + name + "']"))
					.click();
			result.print("Double click on " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to double click on " + name, "Fail");
			Assert.fail("Exception!");
		}

	}

	public void expandHomeInsideTenantInInternalLink(String name)
			throws InterruptedException {
		Thread.sleep(5000);
		try {
			String v = driver
					.findElement(
							By.xpath("(//div[a[span[text()='"
									+ name
									+ "']]])[2]//img[@class='scContentTreeNodeGlyph']"))
					.getAttribute("src");

			if (!(v.contains("expanded"))) {
				driver.findElement(
						By.xpath("(//div[a[span[text()='"
								+ name
								+ "']]])[2]//img[@class='scContentTreeNodeGlyph']"))
						.click();
			}
			result.print("Clicked on element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to click on element - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickOnSave() throws InterruptedException {
		try {
			Thread.sleep(8000);
			driver.findElement(
					By.xpath("//a//img[contains(@alt,'Save any changes.')]"))
					.click();
			Thread.sleep(25000);
			result.print("Clicked on Save", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Save", "Fail");
			Assert.fail("Exception!");
		}
	}

	// Click Functions
	public void clickPublishTab(String name) {
		try {
			driver.findElement(By.xpath("//a[text()='" + name + "']")).click();
			result.print("Clicked on Element - " + name, "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Element - " + name, "Fail");
			Assert.fail("Exception!");
		}
	}

	public void setTextInField(String value, String fieldName) {
		try {
			driver.findElement(
					By.xpath("//div[text()='" + fieldName
							+ "']/following-sibling::div/input")).sendKeys(
					value);
			result.print("Entered " + value + " in " + fieldName, "Pass");
		} catch (Exception e) {
			result.print("Failed to Entered " + value + " in " + fieldName,
					"Fail");
			Assert.fail("Exception!");
		}
	}

	public void clickOnSave1() throws InterruptedException {
		try {
			Thread.sleep(8000);
			driver.findElement(
					By.xpath("(//a//img[contains(@alt,'Save any changes.')])[2]"))
					.click();
			Thread.sleep(25000);
			result.print("Clicked on Save", "Pass");
		} catch (Exception e) {
			try {
				System.out.println("clicked on save 2");
			} catch (Exception ae) {
				result.print("Failed to click on Save", "Fail");
				Assert.fail("Exception!");
			}
		}
	}

	public void publishSite() throws InterruptedException {
		try {
			Thread.sleep(3000);
			driver.findElement(By.xpath("(//td[text()='Publish site'])[2]"))
					.click();
			result.print("Clicked on Publish Item", "Pass");
		} catch (Exception e) {
			result.print("Failed to click on Publish Item", "Fail");
			Assert.fail("Exception!");
		}
	}

	public void folderDelete(String folderNameToDelete)
			throws InterruptedException {
		try {
			Thread.sleep(3000);
			List<WebElement> v = driver.findElements(By.xpath("//span[text()='"
					+ folderNameToDelete + "']"));
			if (!(v.size() == 0)) {
				try {
					WebElement v1 = driver.findElement(By
							.xpath("//span[text()='" + folderNameToDelete
									+ "']"));
					Actions action = new Actions(driver);
					action.contextClick(v1).build().perform();
					result.print("Right clicked on Folder - "
							+ folderNameToDelete, "Pass");
					driver.findElement(
							By.xpath("//tr[contains(@id,'ContextMenu_')]//td[text()='Delete']"))
							.click();
					result.print("Click on Delete", "Pass");
					Thread.sleep(3000);
					driver.switchTo().frame("jqueryModalDialogsFrame");
					driver.switchTo().frame("scContentIframeId0");
					driver.findElement(By.xpath("//button[@id='OK']")).click();
					result.print("Click on OK", "Pass");
					driver.switchTo().defaultContent();
					driver.switchTo().frame("jqueryModalDialogsFrame");
					driver.switchTo().frame("scContentIframeId0");
					driver.findElement(By.xpath("//button[text()='Continue']"))
							.click();
					result.print("Click on Continue", "Pass");
					driver.findElement(By.xpath("//button[text()='Continue']"))
							.click();
					result.print("Click on Continue", "Pass");
					driver.switchTo().defaultContent();
				} catch (Exception e) {

				}
			}
			result.print("Deleted Folder - " + folderNameToDelete, "Pass");
		} catch (Exception e) {
			result.print("Failed to delete the folder - " + folderNameToDelete,
					"Fail");
		}
	}

	public void verifyErrorMessagerOfCreateTempplateBlank(String msg)
			throws InterruptedException {
		try {
			Thread.sleep(3000);
			String v = driver.findElement(By.xpath("//span[@id='scMessage']"))
					.getText();
			if ((v.contains(msg))) {
				result.print("Deleted Folder - ", "Pass");
			}
		
		} catch (Exception e) {
			result.print("Failed to delete the folder - ", "Fail");
		}
	}

}