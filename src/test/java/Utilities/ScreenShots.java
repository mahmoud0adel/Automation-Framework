package Utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Tests.BaseTest;

public class ScreenShots extends BaseTest {
	protected String fullDirectory = System.getProperty("user.dir") + "/" + "/screenshots/" + "/";// i have to add
																									// ###test name and
																									// "/"##
	private static String currentDateAndTime;
	protected static String lastDateAndTimeForTc;

	public synchronized void takeFullScreenshot(String TCName) {
		// taking the screenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		try {

			FileUtils.copyFile(file,
					new File(fullDirectory + TCName + "_" + "/" + TCName + "_" + GetCurrenDateAndTime() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected String GetCurrenDateAndTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
		LocalDateTime dateTime = LocalDateTime.now();
		currentDateAndTime = dateTime.format(formatter);// test
		return currentDateAndTime;
	}

}
