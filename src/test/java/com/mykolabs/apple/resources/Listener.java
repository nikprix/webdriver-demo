package com.mykolabs.apple.resources;

import com.mykolabs.apple.selenium.SeleniumManager;
import com.mykolabs.apple.util.PropertiesReader;
import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

/**
 * This Listener class is used to add flexibility in tests execution and in
 * current example provides the way to make a screenshot of the page when test
 * has failure.
 *
 * @author nikprix
 */
public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener, IReporter {

    @Override
    public void onTestFailure(ITestResult result) {
        if (PropertiesReader.getPropertyAsBoolean("takeScreenshots")) {
            SeleniumManager.valueOf().takeScreenShot();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void onStart(ISuite suite) {
    }

    @Override
    public void onFinish(ISuite suite) {
    }
}
