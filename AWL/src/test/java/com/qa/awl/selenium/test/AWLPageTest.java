package com.qa.awl.selenium.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
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
public class AWLPageTest {

	@LocalServerPort
	static int randomPort;

	public static final String URL = "http://localhost:";

	private WebDriver driver;

	@Before
	void init() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		// options.setHeadless(true); // Turns off auto launch chrome window
		driver = new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1366, 763));
	}

	@After
	void shutdown() {
		driver.close();
	}

	public void stub() {
		AWLHomePage favAnime = PageFactory.initElements(driver, AWLHomePage.class);
		favAnime.getNameInputBox().sendKeys("Death Note");
		favAnime.getWatchedInputBox().sendKeys("13");
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
		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
		stub();
		uI.getCreateButton().click();
		assertTrue(uI.getShowAnimeName().getText().contains("Death Note"));
	}

	@Test
	public void readAnime() {
		driver.get(URL + randomPort + "/index.html");
		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
		stub();
		uI.getCreateButton().click();
		assertTrue(uI.getShowAnimeName().getText().contains("Death Note"));
		assertTrue(uI.getShowAnimeEpisode().getText().contains("13"));
		assertTrue(uI.getShowAnimeRating().getText().contains("5"));
	}

	@Test
	public void updateAnime() {
		driver.get(URL + randomPort + "/index.html");
		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
		stub();
		uI.getCreateButton().click();
		uI.getEditButton().click();
		uI.getWatchedInputBox().sendKeys("18");
		uI.getCreateButton().click(); // Update Anime Button
		assertTrue(uI.getShowAnimeEpisode().getText().contains("18"));
	}

	@Test
	public void deleteAnime() {
		driver.get(URL + randomPort + "/index.html");
		AWLHomePage uI = PageFactory.initElements(driver, AWLHomePage.class);
		stub();
		uI.getCreateButton().click();
		uI.getDeleteButton().click();
		assertTrue(uI.getDeleteConfirmed().getText().contains("Anime removed from watch list"));

	}
}
