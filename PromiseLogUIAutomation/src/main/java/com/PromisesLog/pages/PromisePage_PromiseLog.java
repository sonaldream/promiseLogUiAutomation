package com.PromisesLog.pages;

import java.util.NoSuchElementException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.PromisesLog.utils.Base;

public class PromisePage_PromiseLog extends Base{
	static Logger log=LogManager.getLogger(PromisePage_PromiseLog.class);

	@FindBy(xpath="//select[@name='cboEmp']")
	WebElement selectPromisorDropdown;

	@FindBy(className="mandatory_red_small")
	WebElement mandatoryFieldsLabel;

	@FindBy(id="txtPromise")
	WebElement promiseTextBox;

	@FindBy(id="btnSubmit")
	WebElement logPromiseButton;



	//Initializing page factory for PromisePage_PromiseLog
	public PromisePage_PromiseLog(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void logPromise(String txtPromise)
	{

		try {
			selectPromisorDropdown.click();
			Select select=new Select(selectPromisorDropdown);
			select.selectByVisibleText("Sonali test");
			mandatoryFieldsLabel.click();

			/* NOTE:
			 * While adding a promise if we add same promise text we get the error 
			 * as :: Promise already exists
			 * To avoid the Promise already exists Error...
			 * Adding timestamp in promise text to have different promise text for every run
			 * Examples:
			 * 1. Pormise Text :08-09-2020 10:19:43
			 * 2. Promise Text :08-09-2020 11:11:11
			 */

			promiseTextBox.sendKeys(txtPromise+timeStamp);
			logPromiseButton.click();
		} 
		catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
}
