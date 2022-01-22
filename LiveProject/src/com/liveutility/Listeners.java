package com.liveutility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners implements ITestListener {

	ExtentReports report = TestUtility.extendreportsetup();; //To update the status of each TC
	ExtentTest test;
	ThreadLocal <ExtentTest> extentthread = new ThreadLocal <ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		
		test = report.createTest(result.getMethod().getMethodName());
		extentthread.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentthread.get().log(Status.PASS, "TEST CASE PASSED IS " + result.getName()); //To get name of the method into report
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentthread.get().log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); //To get name of the method into report
		extentthread.get().log(Status.FAIL, "TEST CASE FAILED DUE TO  " + result.getThrowable()); // To get the error message into report
		String destination = null;
		try {
			destination = TestUtility.screenshot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // To get the screenshot path
		extentthread.get().addScreenCaptureFromPath(destination, "Screenshot"); // To add the screenshot into report
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentthread.get().log(Status.SKIP, "TEST CASE SKIPPED IS " + result.getName()); //To get name of the method into report
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}

}
