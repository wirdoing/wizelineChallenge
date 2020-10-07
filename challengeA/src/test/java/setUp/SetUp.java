package setUp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SetUp {
	static WebDriver driver;
	static ExtentHtmlReporter htmlReporter;
	static ExtentTest test;
	static ExtentReports extent;
	static String timeStamp;
	static String htmlReportPath = "challengeA/reports/";
	static String timeStamp2;
	File srcFiler;

	@BeforeClass
	public static void reporter() {
		timeStamp = new SimpleDateFormat("dd-MMMM-yyyy", new Locale("ES", "MX")).format(new Date());
		timeStamp2 = new SimpleDateFormat("dd-MMMM-yyyy - hh;mm;ss a").format(new Date());
		htmlReporter = new ExtentHtmlReporter(htmlReportPath +"/"+ timeStamp + timeStamp2 + ".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@BeforeMethod
	public static void driverCreation(Method method) {
		Properties prop = new Properties();
		FileInputStream inputStream2;
		try {
			inputStream2 = new FileInputStream("challengeA/src/test/resources/config.properties");
			prop.load(inputStream2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String browser = prop.getProperty("driver").toLowerCase();
		switch (browser) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			case "internet explorer":
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				break;
			case "opera":
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
			default:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		test = extent.createTest(method.getName());
	}
	
	public static WebDriver GetDriver(){
		return driver;
	}

	
	
	@AfterMethod
    public void getResult(ITestResult result) throws Exception {
        if (result.getStatus() == ITestResult.FAILURE)
        {
			srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFiler, new File("challengeA/reports" +"/"+ timeStamp + "/" + timeStamp2 + ".png"));
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
			test.fail("Photo of the error", MediaEntityBuilder.createScreenCaptureFromPath("challengeA/reports" + timeStamp + "/" + timeStamp2 + ".png").build());
			test.fail(result.getThrowable());

        } 
        else if (result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
        } 
        else if (result.getStatus() == ITestResult.SKIP)
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.BLUE));
		}
		
		driver.close();
	 }
	 
	 @AfterTest
	public void testend() throws Exception {
		extent.flush();
		driver.quit();
    }

}
