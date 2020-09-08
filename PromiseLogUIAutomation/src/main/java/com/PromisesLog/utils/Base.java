package com.PromisesLog.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import net.bytebuddy.implementation.FieldAccessor.PropertyConfigurable;


public class Base {
	public static WebDriver driver;
	public static Properties propConfig, propAssert;
	public static String todaysDate, timeStamp;
	static Logger log=LogManager.getLogger(Base.class);


	public Base()
	{
		try {
			//System.out.println("Setting Property File configuration");
			propConfig=new Properties();
			FileInputStream fis=new FileInputStream("./src/main/resource/config.properties");
			propConfig.load(fis);

			propAssert=new Properties();
			FileInputStream assertionValues=new FileInputStream("./src/main/resource/assertionValues.properties");
			propAssert.load(assertionValues);

			//Configure log4j
			log4jSetup();

		} catch (FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	public static WebDriver initializeDriver()
	{
		try{

			System.setProperty("webdriver.chrome.driver", propConfig.getProperty("chromeDriverPath"));
			driver= new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			log.info("Chrome driver has been initialized successfully and window is maximized");
		}
		catch(Exception e){
			log.error("Something wrong ! Unable to initialize the driver: "+e);
		}
		return driver;

	}

	public static String getCurrentTimeStamp(){
		SimpleDateFormat dateWithTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
		Date date = new Date();  	    
		timeStamp = dateWithTime.format(date);
		log.info("TimeStamp : "+timeStamp);
		return timeStamp;
	}


	public static String getTodaysDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();  
		todaysDate = dateFormat.format(date);  
		log.info("Date :  "+todaysDate);
		return todaysDate;

	}


	public static boolean textPresentOnPage(WebDriver driver,String text)
	{
		return driver.getPageSource().contains(text);
	}

	//Explicit wait
	public static void explicitWait(WebElement element,int waitTime)
	{
		WebDriverWait wait=new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void log4jSetup(){
		PropertyConfigurator.configure(propConfig.getProperty("log4jConfig"));
	}
}
