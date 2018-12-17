package Selenium.SeleniumTA;



import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.fluent.Request;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.base.Function;

public class CommonMethods {
	
	public static String waitForElement(String item, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement aboutMe;
		aboutMe= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(item)));
		System.out.println("Wait Explicit");
		return item;
	}
	
	public static void screenshotmethod(WebDriver driver)
	{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	try
		{FileUtils.copyFile(scrFile, new File("C:\\Users\\Training_C2a.05.01\\Desktop\\SeleniumAutomation\\ScreenShot\\"+timestamp()+" NewImage.png"));}
	catch(Exception e){
			System.out.println("Exception Occured:"+e.getMessage());}
	}
	
	
	public static String timestamp()
	{
		return new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date());
	}
	
	//Get Respond Code
	public static boolean getResponseCode(String url)
	{
		int res_code=0;
		try {
			res_code=Request.Get(url).execute().returnResponse().getStatusLine().getStatusCode();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res_code:"+res_code);
		if(res_code==200)
			return true;
		else
			return false;
	}

	public static WebElement CheckElementPresent(final String item, WebDriver driver,final boolean stopExecution ) throws Exception {
		final By searchBy = By.xpath(item);
		WebElement webElement = null;
		try
		{
			Function<WebDriver, WebElement> findElementFunction = new FindElementFunction<WebDriver, WebElement>(searchBy);
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(10, TimeUnit.SECONDS)
					.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class).ignoring(WebDriverException.class);
			webElement = wait.until(findElementFunction);
		}
		catch (Exception e) {
			System.out.println("Element Not Found: "+ e);
		}
		if (webElement != null) {
			try {
				System.out.println("Element [ " + searchBy.toString() + " ] Found");
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(false);",webElement);
			} catch (Exception ex) {
				System.out.println("Exception occured while scrolling to the element. "+ex);
			}
		} 
		else {
			
			if(stopExecution)
				throw new Exception("Element "+ item);
			else
			 {
				try {
					ITestResult reult;
					reult = Reporter.getCurrentTestResult();
					reult.setStatus(ITestResult.SUCCESS_PERCENTAGE_FAILURE);
					reult.setThrowable(new Exception());
					Reporter.setCurrentTestResult(reult);

				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		}
		return webElement;
	}
	
	public static class FindElementFunction<T, K> implements Function<WebDriver, WebElement> {
		private By searchByLocator;

		public FindElementFunction(final By by) {
			searchByLocator = by;
		}
		public final WebElement apply(final WebDriver driver) {
			return driver.findElement(searchByLocator);
		}
	}	
		
}
