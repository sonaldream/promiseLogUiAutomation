package com.PromisesLog.pages;

import java.util.NoSuchElementException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.PromisesLog.utils.Base;

public class HomePage_PromiseLog extends Base{
	static Logger log=LogManager.getLogger(HomePage_PromiseLog.class);


	@FindBy(xpath="//a[@href='promise/log_promise.php']")
	WebElement logPromiseLink;

	@FindBy(xpath="//a[@href='logout.php']")
	WebElement logoutButton;

	@FindBy(xpath="//*[@href='promise/promise_to.php']")
	WebElement promisesList;

	//Initializing page factory for HomePage_PromiseLog
	public HomePage_PromiseLog(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public void clickOnLogPromise()
	{
		try{
			logPromiseLink.click();
		}catch(NoSuchElementException e){
			log.error("Unable to find LogPromise Link..");
		}
	}

	public void clickOnPromisesList()
	{
		try{
			promisesList.click();
		}catch(NoSuchElementException e){
			log.error("Unable to find Promise List Link..");
		}
	}

	public boolean logOutButtonPresentOnPage(){
		try{
			//Verify if the LougOut button is visible to confirm user is logged in successfully
			explicitWait(logoutButton, 5);
			return logoutButton.isDisplayed();
		}catch(NoSuchElementException e)
		{
			log.error("Unable to find the LogOut Button");
			return false;
		}

	}


}
