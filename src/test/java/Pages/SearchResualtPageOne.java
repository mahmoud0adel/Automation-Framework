package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import Tests.BaseTest;

public class SearchResualtPageOne extends BaseTest {
	private WebDriver driver;
	
	public SearchResualtPageOne (WebDriver driver) { 
	this.driver = driver;
	}
	//elements
	By nextButton = By.id("pnnext");
	

	
}
