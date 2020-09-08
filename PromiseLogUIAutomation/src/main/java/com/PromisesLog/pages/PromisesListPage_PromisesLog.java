package com.PromisesLog.pages;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.PromisesLog.utils.Base;

import net.bytebuddy.implementation.FieldAccessor.PropertyConfigurable;
// List of all promises that you have received from any other employee


public class PromisesListPage_PromisesLog extends Base{
	static Logger log=LogManager.getLogger(PromisesListPage_PromisesLog.class);

	@FindBy(xpath="//font[contains(text(),'Promise added successfully')]")
	WebElement promiseAddValidation;

	@FindBy(id="cboEmp")
	WebElement promisor;

	@FindBy(id="txtStartDate")
	WebElement startDateTextBox;

	@FindBy(id="txtEndDate")
	WebElement endDateTextBox;

	@FindBy(xpath="//a[@href='../logout.php']")
	WebElement logoutButton;

	@FindBy(xpath="//input[@value='Login']")
	WebElement loginButton;

	@FindBy(xpath="//input[@name='btnSearch']")
	WebElement searchButton;


	//To search the added promise we have to select the start date and end date with Promisor
	public void selectStartDateEndDateAndSearch(){
		startDateTextBox.sendKeys(todaysDate);
		endDateTextBox.sendKeys(todaysDate);
		searchButton.click();
	}


	//Initializing page factory for PromisesListPage_PromisesLog
	public PromisesListPage_PromisesLog(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//Actions Method

	public void logOutFromPromiseLog(){
		try {
			logoutButton.click();	
		}
		catch(NoSuchElementException e){
			log.error("Unable to find the Logout Button");
		}	
	}


	public boolean verifyAddedPromise(){
		try{
			if (textPresentOnPage(driver, propAssert.getProperty("promiseText")+timeStamp)) {
				return true;
			}
		}catch(Exception e){
			log.error("Unable to find the created Promise..");
		}
		return false;

	}

	public void selectpromisor()
	{
		try{
			explicitWait(promisor, 7);
			promisor.click();
			Select select=new Select(promisor);
			select.selectByVisibleText("Sonali test");

		}catch(NoSuchElementException e)
		{
			log.error("Unable to find the the element Promisor or element with Sonali Test");
		}	


	}
}
