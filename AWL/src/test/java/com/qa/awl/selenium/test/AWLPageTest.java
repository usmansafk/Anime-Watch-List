package com.qa.awl.selenium.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.awl.selenium.page.AWLHomePage;
import com.qa.awl.selenium.utils.AWLTestUtils;

@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AWLPageTest {

	// @LocalServerPort
	static int randomPort = 9092;

	public static final String URL = "http://localhost:";

	private WebDriver driver;

	public static String count = "15";

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(true); // Turns off auto launch chrome window
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1366, 763));
	}

	@After
	public void shutdown() {
		driver.close();
	}

	public void stub(String name, String count, String rating) {
		AWLHomePage favAnime = PageFactory.initElements(driver, AWLHomePage.class);
		AWLTestUtils.explicitlyWait(driver, "//input[@placeholder='Name of Anime']");
		WebElement txt = favAnime.getNameInputBox();// .sendKeys(name);
		AWLTestUtils.inputText(txt, name);
		WebElement cnt = favAnime.getWatchedInputBox();// .sendKeys(count);
		AWLTestUtils.inputText(cnt, count);
		WebElement rate = favAnime.getRatingInputBox();// .sendKeys(rating);
		AWLTestUtils.inputText(rate, rating);

	}

//	 -------Tests-------:
//	@Test
//	@Order(1)
//	public void getAWLWebsite() {
//		driver.get(URL + randomPort + "/index.html");
//		assertEquals("AWL", driver.getTitle());
//	}

//	@Test
//	@Order(2)
//	public void createAnime() {
//		driver.navigate().to(URL + randomPort + "/index.html");
//		AWLTestUtils.explicitlyWait(driver, AWLHomePage.xpName);
//		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
//		stub("Death Note", "15", "5");
//		AWLTestUtils.explicitlyWait(driver, "//button[@type='submit']");
//		// uI.getCreateButton().click(); // element intercepted issue
//		WebElement btnCreate = uI.getCreateButton();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btnCreate); 
//		assertTrue(uI.getShowAnimeName().getText().contains("Death Note"));
//	}
////
//	@Test
//	@Order(3)
//	public void readAnime() {
//		driver.navigate().to(URL + randomPort + "/index.html");
//		AWLTestUtils.explicitlyWait(driver, "//input[@placeholder='Name of Anime']");
//		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
//		stub("Death Note", "15", "5");
//		WebElement btnCreate = uI.getCreateButton();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btnCreate);
//		System.out.println("Anime Created");
//		assertTrue(uI.getShowAnimeName().getText().contains("Death Note"));
//		assertTrue(uI.getShowAnimeEpisode().getText().contains(count));
//		assertTrue(uI.getShowAnimeRating().getText().contains("5"));
//	}
//
	@Test
	@Order(4)
	public void updateAnime() {
		driver.navigate().to(URL + randomPort + "/index.html");
		AWLTestUtils.explicitlyWait(driver, "//input[@placeholder='Name of Anime']");
		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
		stub("Death Note", "15", "5");
		WebElement btnCreate = uI.getCreateButton();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnCreate);
		List<WebElement> arr = driver.findElements(By.xpath("//i[contains(@class, 'edit')]"));
		WebElement btnUpdate = uI.getEditButton();
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", btnUpdate);
		AWLTestUtils.explicitlyWait(driver, "//input[@placeholder='Episodes Watched']");
		uI.getWatchedInputBox().sendKeys("18");
		btnCreate = uI.getCreateButton();
		js.executeScript("arguments[0].click();", btnCreate);
		assertTrue(uI.getShowAnimeEpisode().getText().contains((Integer.toString(Integer.parseInt(count) + 1))));
	}

//	@Test
//	@Order(5)
//	public void deleteAnime() {
//		String anime = "Death Note";
//		driver.navigate().to(URL + randomPort + "/index.html");
//		AWLTestUtils.explicitlyWait(driver, "//input[@placeholder='Name of Anime']");
//		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
//		stub("Death Note", "15", "5");
//		WebElement btnCreate = uI.getCreateButton();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", btnCreate);
//		List<WebElement> txtAnimeCount = driver.findElements(By.xpath("//td[text()='" + anime + "']"));
//		assertEquals(1, txtAnimeCount.size());
//		WebElement btnDelete = uI.getDeleteButton();
//		js.executeScript("arguments[0].click();", btnDelete);
//		txtAnimeCount = driver.findElements(By.xpath("//td[text()='" + anime + "']"));
//		assertEquals(0, txtAnimeCount.size());
//	}
}
