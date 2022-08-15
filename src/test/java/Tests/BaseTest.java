package Tests;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.Status;

import Utilities.EventListener;
import Utilities.Report;
import Utilities.ScreenShots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	public WebDriverWait wait;
	//	public static EventFiringWebDriver  oEventFire ;
	//	public static EventListener eventListener ;

	public void SetUpDriver(String browser) {

		if (browser.equalsIgnoreCase("chrome"))

		{
			System.out.println("chrome");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();

			//			oEventFire = new EventFiringWebDriver(driver);//this for eventlistenet
			//			eventListener = new EventListener();// this event listener class where we implement WebDriverEventListener

		}

		else if (browser.equalsIgnoreCase("firefox"))

		{
			System.out.println("firefox");

			WebDriverManager.firefoxdriver().setup();

			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();

					
		}

		else if (browser.equalsIgnoreCase("IE")) {

			System.out.println("IE");

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();

		
		}

		// optional default case

		else {
			System.out.println("Driver Not Supported");
		}
	}



	// after test method
	@AfterMethod
	public void TearDown(String TCName) {
		if (driver != null) {

			driver.close();// close the current window !
		}
	}
	//objecto for screenShots
	ScreenShots screenShots ;

	public void scrollDownAndClicknext(String TCName) {
		screenShots	= new ScreenShots();

		By nextButton = By.id("pnnext");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,9999)", "");

		//Take screenshot and update the report status
		screenShots.takeFullScreenshot(TCName);
		Report.test.log(Status.PASS,  "Before Click");
		Report.extent.flush();
		Report.driver_extent.navigate().refresh();
		
		driver.findElement(nextButton).click();

		//Take screenshot and update the report status
		screenShots.takeFullScreenshot(TCName);
		Report.test.log(Status.PASS,  "Button Clicked");
		Report.extent.flush();
		Report.driver_extent.navigate().refresh();
	}

	By NumberOfResults = By.xpath("//h3[@class = \"LC20lb MBeuO DKV0Md\"]");

	public int getNumberofResults() {

		List<WebElement> NoOfResults = driver.findElements(NumberOfResults);
		return NoOfResults.size();
	}

	public void refreshReportPage() {
		Report.driver_extent.navigate().refresh();

	}
}
