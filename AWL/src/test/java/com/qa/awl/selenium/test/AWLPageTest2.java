package com.qa.awl.selenium.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.awl.selenium.page.AWLHomePage;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AWLPageTest2 {

	// @LocalServerPort
	static int randomPort = 9092;

	public static final String URL = "http://localhost:";

	private WebDriver driver;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		ChromeOptions options = new ChromeOptions();
		//options.setHeadless(true); // Turns off auto launch chrome window
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1366, 763));
	}

//	@After
//	public void shutdown() {
//		driver.close();
//	}

	public void stub() {
		AWLHomePage favAnime = PageFactory.initElements(driver, AWLHomePage.class);
		favAnime.getNameInputBox().sendKeys("AOT");
		favAnime.getWatchedInputBox().sendKeys("15");
		favAnime.getRatingInputBox().sendKeys("5");
	}

	// -------Tests-------:
//	@Test
//	public void getAWLWebsite() {
//		driver.get(URL + randomPort + "/index.html");
//		assertEquals("AWL", driver.getTitle());
//	}
//
//	@Test
//	public void createAnime() {
//		driver.get(URL + randomPort + "/index.html");
//		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
//		stub();
//		//uI.getCreateButton().click(); // element intercepted issue
//		WebElement btnCreate = uI.getCreateButton();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		 js.executeScript("arguments[0].click();", btnCreate); //(action("pure js"), element)
//		assertTrue(uI.getShowAnimeName().getText().contains("Death Note"));
//	}

//	@Test
//	public void readAnime() {
//		driver.get(URL + randomPort + "/index.html");
//		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
//		stub();
//		WebElement btnCreate = uI.getCreateButton();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btnCreate);
//		assertTrue(uI.getShowAnimeName().getText().contains("AOT"));
//		assertTrue(uI.getShowAnimeEpisode().getText().contains("15"));
//		assertTrue(uI.getShowAnimeRating().getText().contains("5"));
//	}

//	@Test
//	public void updateAnime() {
//		driver.get(URL + randomPort + "/index.html");
//		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
//		stub();
//		WebElement btnCreate = uI.getCreateButton();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btnCreate); 
//		
//		List<WebElement> arr = driver.findElements(By.xpath("//i[contains(@class, 'edit')]"));
//		System.out.println("--------count: "+arr.size());
//		WebElement btnUpdate = uI.getEditButton();
//		
//		js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btnUpdate);
//		uI.getWatchedInputBox().sendKeys("18");
//
//		btnCreate = uI.getCreateButton();
//		js.executeScript("arguments[0].click();", btnCreate); 
//		
//		assertTrue(uI.getShowAnimeEpisode().getText().contains("18"));
//	}

	@Test  
	public void deleteAnime(String anime) {
		driver.get(URL + randomPort + "/index.html");
		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
		stub();
		WebElement btnCreate = uI.getCreateButton();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnCreate); 
		List<WebElement> txtAnimeCount = driver.findElements(By.xpath("//td[text()='" + anime + "']"));
		assertEquals(txtAnimeCount, 1);
		WebElement btnDelete = uI.getCreateButton();
		js.executeScript("arguments[0].click();", btnDelete); 
		//assertTrue(uI.getDeleteConfirmed().getText().contains("Anime removed from watch list"));
		txtAnimeCount = driver.findElements(By.xpath("//td[text()='" + anime + "']"));
		assertEquals(txtAnimeCount, 0);
	}
}

