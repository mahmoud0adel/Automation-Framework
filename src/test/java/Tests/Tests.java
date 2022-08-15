package Tests;

import Utilities.ReadDataFromExcel;
import Utilities.Report;
import Utilities.ScreenShots;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.Status;

import Pages.GoogleHomePage;
import Pages.SearchResualtPageOne;
import Pages.SearchResultPageTwo;

public class Tests extends BaseTest {
	ScreenShots screenShot;
	Report report;

	@BeforeSuite
	public void BeforeSuite() {
		SetUpDriver("chrome");
		report = new Report();
		report.SetUpExtent();

	}

	@BeforeMethod
	public void BeforeMethod(ITestResult result) {
		Report.test = Report.extent.createTest(result.getMethod().getMethodName());

	}

	@Test(description = "This test case one now")
	public void TestCaseOne() {
		String testCaseName = new Object() {
		}.getClass().getEnclosingMethod().getName();

		ReadDataFromExcel o1 = new ReadDataFromExcel("TestData");
		String searchData = o1.getExcelDataByeRowNOAndColumnNO(2, 1);
		String url = o1.getExcelDataByeRowNOAndColumnNO(1, 1);
		GoogleHomePage homepage = new GoogleHomePage(driver);
	
		driver.navigate().to(url);
		homepage.googleSearch(searchData,testCaseName);
		SearchResualtPageOne pageOne = new SearchResualtPageOne(driver);
		pageOne.scrollDownAndClicknext(testCaseName);
		int numberOfResultPageOne = (pageOne.getNumberofResults());
		System.out.println("++++++++++++No.Results in Page 1 ++++++++++++" + numberOfResultPageOne);
		SearchResultPageTwo pageTwo = new SearchResultPageTwo(driver);
		int numberOfResultPageTwo = (pageTwo.getNumberofResults());
		System.out.println("+++++++++++++No.Results in Page 2++++++++++++" + numberOfResultPageTwo);
		pageTwo.scrollDownAndClicknext(testCaseName);
		
	
		if (numberOfResultPageOne == numberOfResultPageTwo) {
			Report.test.log(Status.PASS, "Assertion Pased");
			Assert.assertEquals(numberOfResultPageOne, numberOfResultPageTwo);

		} else {
			Report.test.log(Status.FAIL, "Assert Failed ");
			Assert.assertEquals(numberOfResultPageOne, numberOfResultPageTwo);
		}


	}



	@AfterMethod
	public void after(ITestResult result) {
		Report report = new Report();

		if (result.getStatus() == ITestResult.SUCCESS) {

			System.out.println(result.getName() + " passed **********");

//				evidenceAndScreenShots.InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
			// passing to the report
			Report.test.log(Status.PASS, result.getName() + "Passed");
			Report.extent.flush();
			Report.driver_extent.navigate().refresh();

			// rename screenshots folder for each test case
			report.renameScreenShotsFolder(result.getName());

			// insert Images to report
			report.InsertAllImagesToTheReport(result.getName());

		} else if (result.getStatus() == ITestResult.FAILURE) {

			System.out.println(result.getName() + " Failed **********");

//				evidenceAndScreenShots.InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
			Report.test.log(Status.FAIL, result.getName() + "Failed");
			
			Report.extent.flush();
			Report.driver_extent.navigate().refresh();

			// rename screenshots folder for each test case
			report.renameScreenShotsFolder(result.getName());

			// insert Images to report
			report.InsertAllImagesToTheReport(result.getName());
		} else {

			System.out.println(result.getName() + "Skipped **********");

			Report.test.log(Status.SKIP, result.getName() + "Skiped");
			Report.extent.flush();
			Report.driver_extent.navigate().refresh();
			
			// rename screenshots folder for each test case
			report.renameScreenShotsFolder(result.getName());

			// insert Images to report
			report.InsertAllImagesToTheReport(result.getName());
		}
		TearDown(result.getName());
		Report.extent.flush();
		refreshReportPage();
	

	}

	@AfterSuite
	public void afterSuite() {
		refreshReportPage();

	}

}