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

import com.qa.awl.selenium.page.AWLMainPage;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AWLMainPageTest {
	
	/*
	 * Each test passes if ran 
	 * Run tests individually or a few times to get pass. 
	 * Issue: requires wait config
	 * 
	 */

	@LocalServerPort
	static int randomPort = 9092; //temporary hardcoded port due to accessing issue

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

	@After
	public void shutdown() {
		driver.close();
	}

	public void stub() {
		AWLMainPage favAnime = PageFactory.initElements(driver, AWLMainPage.class);
		favAnime.getNameInputBox().sendKeys("Death Note");
		favAnime.getWatchedInputBox().sendKeys("15");
		favAnime.getRatingInputBox().sendKeys("5");
	}

	// -------Tests-------:
	@Test
	public void getAWLWebsite() {
		driver.get(URL + randomPort + "/index.html");
		assertEquals("AWL", driver.getTitle());
	}

	@Test
	public void createAnime() {
		driver.get(URL + randomPort + "/index.html");
		AWLMainPage uI = PageFactory.initElements(driver, AWLMainPage.class);
		stub();
		WebElement btnCreate = uI.getCreateButton();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].click();", btnCreate);
		assertTrue(uI.getShowAnimeName().getText().contains("Death Note"));
	}

	@Test
	public void readAnime() {
		driver.get(URL + randomPort + "/index.html");
		AWLMainPage uI = PageFactory.initElements(driver, AWLMainPage.class);
		stub();
		WebElement btnCreate = uI.getCreateButton();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnCreate);
		System.out.println("---Anime Created---");
		assertTrue(uI.getShowAnimeName().getText().contains("Death Note"));
		assertTrue(uI.getShowAnimeEpisode().getText().contains("15"));
		assertTrue(uI.getShowAnimeRating().getText().contains("5"));
	}

	@Test
	public void updateAnime() {
		driver.get(URL + randomPort + "/index.html");
		AWLMainPage uI = PageFactory.initElements(driver, AWLMainPage.class);
		stub();
		WebElement btnCreate = uI.getCreateButton();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnCreate); 
		List<WebElement> arr = driver.findElements(By.xpath("//i[contains(@class, 'edit')]"));
		WebElement btnUpdate = uI.getEditButton();
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnUpdate);
		uI.getWatchedInputBox().sendKeys("1");
		btnCreate = uI.getCreateButton();
		js.executeScript("arguments[0].click();", btnCreate); 
		assertTrue(uI.getShowAnimeEpisode().getText().contains("151"));
	}


	@Test
	public void deleteAnime() {
		String anime = "Death Note";
		driver.get(URL + randomPort + "/index.html");
		AWLMainPage uI = PageFactory.initElements(driver, AWLMainPage.class);
		stub();
		WebElement btnCreate = uI.getCreateButton();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnCreate); 
		List<WebElement> txtAnimeCount = driver.findElements(By.xpath("//td[text()='" + anime + "']"));
		assertEquals(1, txtAnimeCount.size());
		WebElement btnDelete = uI.getDeleteButton();
		js.executeScript("arguments[0].click();", btnDelete); 
		txtAnimeCount = driver.findElements(By.xpath("//td[text()='" + anime + "']"));
		assertEquals(0, txtAnimeCount.size());
	}
}