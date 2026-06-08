package Listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listenerclass implements ITestListener {
	
	public void onTestStart(ITestResult Result) {
		
		System.out.println("Test Case Started");
	}
	
public void onTestSkipped(ITestResult Result) {
		
		System.out.println("Test Case Skipped");
	}

public void onTestFailure(ITestResult Result) {
	
	System.out.println("Test Case Failed");
}

public void onTestFinished(ITestResult Result) {
	
	System.out.println("Test Case Completed");
}
}
