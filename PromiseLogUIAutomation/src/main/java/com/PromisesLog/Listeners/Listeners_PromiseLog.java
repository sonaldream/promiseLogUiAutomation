package com.PromisesLog.Listeners;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.testCases.PromiseLogTestCases;

public class Listeners_PromiseLog implements ITestListener{
	static Logger log=LogManager.getLogger(Listeners_PromiseLog.class);

	public void onFinish(ITestContext result) {
		//log.info("Test case finished:"+result.getName());

	}

	public void onStart(ITestContext result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailure(ITestResult result) {
		log.info("Test case Failed :"+result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		log.info("Test case Skipped :"+result.getName());

	}

	public void onTestStart(ITestResult result) {
		//log.info("Test case started:"+result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		log.info("Test case Passed :"+result.getName());
	}

}
