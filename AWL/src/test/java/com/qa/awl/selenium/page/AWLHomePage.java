package com.qa.awl.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class AWLHomePage {

	// -------UI------

	@FindBy(name = "name")
	private WebElement nameInputBox;
	

	@FindBy(name = "watched")
	private WebElement watchedInputBox;


	@FindBy(name = "rating")
	private WebElement ratingInputBox;


	@FindBy(id = "create-button")
	private WebElement createButton;


	@FindBy(name = "far fa-edit text-primary")
	private WebElement editButton;

	
	@FindBy(name = "fas fa-trash-alt text-danger")
	private WebElement deleteButton;


	@FindBy(linkText = "Anime removed from watch list")
	private WebElement deleteConfirmed;

	// ----These are specific to the first anime added to table:----
	
	@FindBy(xpath = "//*[@id=\"watchlist\"]/tr[1]/td[1]")
	private WebElement showAnimeName;

	
	@FindBy(xpath = "//*[@id=\"watchlist\"]/tr[1]/td[2]")
	private WebElement showAnimeEpisode;


	@FindBy(xpath = "//*[@id=\"watchlist\"]/tr[1]/td[3]")
	private WebElement showAnimeRating;

	// ------Getters------
	public WebElement getNameInputBox() {
		return nameInputBox;
	}

	public WebElement getWatchedInputBox() {
		return watchedInputBox;
	}

	public WebElement getRatingInputBox() {
		return ratingInputBox;
	}

	public WebElement getCreateButton() {
		return createButton;
	}

	public WebElement getEditButton() {
		return editButton;
	}

	public WebElement getDeleteButton() {
		return deleteButton;
	}

	public WebElement getDeleteConfirmed() {
		return deleteConfirmed;
	}

	public WebElement getShowAnimeName() {
		return showAnimeName;
	}

	public WebElement getShowAnimeEpisode() {
		return showAnimeEpisode;
	}

	public WebElement getShowAnimeRating() {
		return showAnimeRating;
	}

}
