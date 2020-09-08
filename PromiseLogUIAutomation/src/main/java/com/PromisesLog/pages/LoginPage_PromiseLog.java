package com.PromisesLog.pages;

import java.util.NoSuchElementException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.PromisesLog.utils.Base;
import com.testCases.PromiseLogTestCases;

public class LoginPage_PromiseLog extends Base{
	static Logger log=LogManager.getLogger(LoginPage_PromiseLog.class);


	//Page factory

	@FindBy(xpath="//font[contains( text(), 'Invalid Username/password')]")
	WebElement errorInvalidUnamePwd;

	@FindBy(xpath="//input[@name='txtUsername']")
	WebElement userNameTextBox;

	@FindBy(xpath="//input[@name='txtPassword']")
	WebElement passwordTextBox;

	@FindBy(xpath="//input[@value='Login']")
	WebElement loginButton;

	public boolean loginButtonPresentOnPage()
	{
		try{
			return loginButton.isDisplayed();		
		}catch(NoSuchElementException e)
		{
			log.error("Unable to find the Login Button..");
			return false; 
		}
	}

	public boolean getErrorMessageAndCompare(){
		String actualInvalidErrorMsg = errorInvalidUnamePwd.getText();
		try{
			if (actualInvalidErrorMsg.equals(propAssert.getProperty("errorInvalidCredential"))) {
				return true;
			}
		}catch(NoSuchElementException e)
		{
			log.error("Unable to find the webelement having Error Message..");
			return false; 
		}
		return false;
	}

	//Initializing page factory for LoginPage_PromiseLog
	public LoginPage_PromiseLog(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	//Action methods
	public void clickOnLoginButton()
	{
		try{
			loginButton.click();

		}
		catch(NoSuchElementException e){
			log.error("Unable to find the Login Button...");
		}catch(Exception e){
			e.printStackTrace();
		}


	}

	public void loginToPromiseLog(String userName,String password)
	{
		try{
			explicitWait(userNameTextBox, 5);
			//Enter Username and Password
			userNameTextBox.sendKeys(userName);
			passwordTextBox.sendKeys(password);
			//Click on Login Button
			clickOnLoginButton();
		}
		catch(NoSuchElementException e){
			log.error("Unable to locate the element..");
		}
	}
}

