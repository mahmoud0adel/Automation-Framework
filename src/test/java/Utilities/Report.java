package Utilities;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Report extends ScreenShots {
	// Objects
	/*
	 * 1-The ExtentHtmlReporter or "ExtentSparkReporter" class is used for creating
	 * the HTML reports with it's configurations . 2-The ExtentReports class is used
	 * for creating the tests. 3-The ExtentTest class is used for generating the
	 * logs in the Extent Report.
	 */
	public ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static WebDriver driver_extent;

	public void SetUpExtent() {
		// generate HTML report
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/extentReport.html");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// Configurations
		// report title
		htmlReporter.config().setDocumentTitle("Automation Report");
		// report name
		htmlReporter.config().setReportName("VOIS_report ");

		htmlReporter.config().setTimeStampFormat("EEEE, dd  MMMM , yyyy, hh:mm a ");
		htmlReporter.config().setTheme(Theme.STANDARD);// Theme.DARK or Theme.STANDARD

		driver_extent = new ChromeDriver();
		driver_extent.manage().window().maximize();
//		    	//opening the report
		extent.flush();
		// using the report driver
		driver_extent.get("file:" + System.getProperty("user.dir") + "/Reports/extentReport.html");

	}

	// changeing the folder name to the folder with status name
	public synchronized void renameScreenShotsFolder(String TCName) {
		lastDateAndTimeForTc = GetCurrenDateAndTime();
		// getting the old folder directory and it's name
		File oldName = new File(fullDirectory + TCName + "_");
		// creating new folder name and directory
		File newName = new File(fullDirectory + TCName + "_" + lastDateAndTimeForTc);
		// if the old name is exist then rename it to the new name we created
		if (oldName.renameTo(newName)) {
			// after renaming success print this
			System.out.println("folder renamed successfully");
		} else {
			// printing this if the renaming failed
			System.out.println("Failed to rename folder");
		}

	}

	// we can take the below from the screenshots evidence class object
	public void InsertAllImagesToTheReport(String TCName) {
		// sub-folders) where we get the images
		File imagesSrcFilePath = new File(fullDirectory + TCName + "_" + lastDateAndTimeForTc);
		// to use it to get screenshot name after it below
		String imagesPath = fullDirectory + TCName + "_" + lastDateAndTimeForTc + "/";

		// array of files to get the list of items inside the src folder path
		File[] list = imagesSrcFilePath.listFiles();

		// Step 5 : printing the number of found items
		System.out.println("Source folder item list " + list.length);

		// Step 6 : Iterate through the files in the source folder
		for (int images = 0; images < list.length; images++) {
			if (list[images].isFile()) {
				System.out.println("Found File name - : " + list[images].getName());

				test.addScreenCaptureFromPath(imagesPath + list[images].getName());
			}
		}
	}

}
