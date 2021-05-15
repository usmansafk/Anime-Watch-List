package com.qa.awl.selenium.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AWLHomePage {
	
	@FindBy(name = "name")
	private WebElement nameInputBox;
	
	@FindBy(name = "watched")
	private WebElement watchedInputBox;
	
	@FindBy(name = "rating")
	private WebElement ratingInputBox;
	
	@FindBy(id = "create-button")
	private WebElement createButton; 
	

}
