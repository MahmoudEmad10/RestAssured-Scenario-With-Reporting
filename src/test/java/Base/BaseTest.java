package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import utilities.TestConfig;
import org.testng.ITestResult;

import Listeners.TestNGListeners;
import org.testng.ITestNGListener;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected TestConfig config;

    @BeforeClass
    public static void setUp() {
        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("ExtentReport.html");
        extent.attachReporter(htmlReporter);
    }

    @BeforeClass
    public void initializeConfig() {
        config = new TestConfig();  // Initialize config here
    }

//    protected void startTest(String testName) {
//        test = extent.createTest(testName);
//    }
//
//    protected void log(String message) {
//        if (test != null) {
//            test.info(message);
//        }
//    }

//    @AfterClass
//    public static void tearDown() {
//        extent.flush();
//    }

}
