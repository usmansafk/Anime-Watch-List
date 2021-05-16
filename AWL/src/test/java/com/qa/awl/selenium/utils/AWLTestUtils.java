package com.qa.awl.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AWLTestUtils {

	public static WebElement explicitlyWait(WebDriver driver, String xpath) {
		driver.manage().timeouts().implicitlyWait(0, SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		// WebElement element =
		// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		// System.out.println("Element with xpath: " + xpath + " is displayed: " +
		// element.isDisplayed());
		driver.manage().timeouts().implicitlyWait(20, SECONDS);
		return element;
	}

	public static void inputText(WebElement element, String text) {

		element.clear();
		element.sendKeys(text);

	}

}
