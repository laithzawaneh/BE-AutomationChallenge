package reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.FileWriter;
import java.io.IOException;

public class TestReport implements ITestListener {
    private FileWriter writer;

    public TestReport() {
        try {
            writer = new FileWriter("TestReport.txt", true); // Appends results to a report file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started: " + result.getName());
        logToFile("Test started: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getName());
        logToFile("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getName());
        logToFile("Test Failed: " + result.getName() + ". Error: " + result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getName());
        logToFile("Test Skipped: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("All tests finished: " + context.getAllTestMethods()[0].getRealClass().getSimpleName());
        logToFile("All tests finished: " + context.getName());
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logToFile(String message) {
        try {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
