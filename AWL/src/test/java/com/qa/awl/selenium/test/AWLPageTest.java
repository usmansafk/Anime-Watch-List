package com.qa.awl.selenium.test;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AWLPageTest {
	
	private WebDriver driver;
	
	
	@Before 
	void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1366,763));
	}
	
	@Test
	public void getAWLWebsite() {
		driver.get("http://localhost:9092/index.html");
		assertEquals("AWL", driver.getTitle());
	}
	



}
