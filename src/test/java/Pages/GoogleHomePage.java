package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;

import Tests.BaseTest;
import Utilities.Report;
import Utilities.ScreenShots;

public class GoogleHomePage extends BaseTest  {
	WebDriver driver;
	
	public GoogleHomePage(WebDriver driver){
		this.driver = driver;
	}
	//Elements 
	By searchbox = By.xpath("//*[@name='q']");
	
	By searchBtn =By.name("btnk");
//ScreenShot object
	ScreenShots screenShot;

	//Methods
	public void googleSearch(String textTosend,String TCName) {
		screenShot = new ScreenShots();
		
		
		driver.findElement(searchbox).sendKeys(textTosend);
		//Take screenshot and update the report status
		screenShot.takeFullScreenshot(TCName);
		Report.test.log(Status.PASS,  "Key Sent");
		Report.extent.flush();
		Report.driver_extent.navigate().refresh();
		
		driver.findElement(searchbox).sendKeys(Keys.ENTER);
		//Take screenshot and update the report status
		screenShot.takeFullScreenshot(TCName);
		Report.test.log(Status.PASS,  "Key Sent");
		Report.extent.flush();
		Report.driver_extent.navigate().refresh();
		//driver.findElement(searchBtn).click();
	}
	
	
	

}
