package com.testCases;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.PromisesLog.pages.HomePage_PromiseLog;
import com.PromisesLog.pages.LoginPage_PromiseLog;
import com.PromisesLog.pages.PromisePage_PromiseLog;
import com.PromisesLog.pages.PromisesListPage_PromisesLog;
import com.PromisesLog.utils.Base;

public class PromiseLogTestCases extends Base{
	static Logger log=LogManager.getLogger(PromiseLogTestCases.class);
	LoginPage_PromiseLog loginPage;
	HomePage_PromiseLog homePage;
	PromisePage_PromiseLog promisePage;
	PromisesListPage_PromisesLog promisesListPage;

	//Created Constructor to initialize the WebDriver and instances of Pages classes
	public PromiseLogTestCases() {
		driver = Base.initializeDriver();
		//Create the objects to use the locators and actions specified
		loginPage=new LoginPage_PromiseLog(driver);
		homePage = new HomePage_PromiseLog(driver);
		promisePage=new PromisePage_PromiseLog(driver);
		promisesListPage= new PromisesListPage_PromisesLog(driver);

		//Get Today's Date: It will be needed to search the added promise
		getCurrentTimeStamp();
		getTodaysDate();
	}

	@Test (priority=1) 
	public void login_without_username_password() throws  InterruptedException{
		try{
			driver.get(propConfig.getProperty("promisesLogUrl"));		
			loginPage.clickOnLoginButton();
			//Verify validation is displaying for blank fields		

			boolean verifyErrorMessage = loginPage.getErrorMessageAndCompare();
			Assert.assertTrue(verifyErrorMessage, "Unable to find the error message::"+propAssert.getProperty("errorInvalidCredential"));	
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
	}


	@Parameters ({"userName","password"})
	@Test (priority=2)
	public void login_with_username_password(String userName, String password){
		try{
			loginPage.loginToPromiseLog(userName, password);		
			// Verify user is able to login to the application.
			boolean userLoggedIn = homePage.logOutButtonPresentOnPage();
			Assert.assertTrue(userLoggedIn, "Unable to Login to PromiseLog Application..!");
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
	}

	@Test (priority=3)
	public void add_promise(){
		try{
			homePage.clickOnLogPromise();
			promisePage.logPromise(propAssert.getProperty("promiseText"));
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
	}

	@Test (priority=4)
	public void verify_promise_is_added(){
		try {
			//homePage.clickOnPromisesList();
			promisesListPage.selectpromisor();
			promisesListPage.selectStartDateEndDateAndSearch();
			boolean verifyAddedPromise=promisesListPage.verifyAddedPromise();
			Assert.assertTrue(verifyAddedPromise, "Unable to find the added promise..!");
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	@Test (priority=5)
	void logoutFromPromiseLog() throws InterruptedException{
		try{
			promisesListPage.logOutFromPromiseLog();

			//Verify if the login button is visible
			boolean userLoggedOut = loginPage.loginButtonPresentOnPage();
			Assert.assertTrue(userLoggedOut, "Unable to LogOut from PromiseLog Application..!");

		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void quit_the_driver(){
		try{
			Thread.sleep(3000);
			driver.quit();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
}
