package Selenium.SeleniumTA;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;

public class NewTest 
{
	WebDriver driver;
	
  @Test
  public void functionfirst() throws Exception 
  {
	  driver.get("http://www.google.com");
	  CommonMethods.getResponseCode("http://www.google.com");
	  WebElement SearchField=driver.findElement(By.xpath("//input[@name='q']"));
	  SearchField.click();
	  SearchField.sendKeys("Value1");
	  Thread.sleep(2000);
	  CommonMethods.screenshotmethod(driver);
	  driver.findElement(By.xpath("//input[@name='btnK']")).click();
	  CommonMethods.waitForElement("//div[@id='hdtb-msb']", driver);
	  CommonMethods.CheckElementPresent("//div[@id='hdtb-msb2']", driver,false);
	 System.out.println("Validation");
	 CommonMethods.CheckElementPresent("//div[@id='hdtb-msb']", driver,false);
	 ExcelReader.ReadFileFromExcel();
	 JavascriptExecutor js=(JavascriptExecutor) driver;
	 js.executeScript("window.scrollBy(0,2000)");
	 WebElement DataHeader=driver.findElement(By.xpath("//div[@id='hdtb-msb']"));
	 System.out.println("Done");
	 Actions action = new Actions(driver);
     action.moveToElement(DataHeader).build().perform();
	 String commend="notepad.exe";
	 Runtime run=Runtime.getRuntime();
	 run.exec(commend);
     //Robot robot=new Robot();
     //robot.keyPress(KeyEvent.VK_TAB);
     
     //robot.keyPress(KeyEvent.VK_SHIFT);
     //robot.keyPress(KeyEvent.VK_A);
     //robot.keyPress(KeyEvent.VK_B);
     Thread.sleep(5000);
	  ExcelReader.GetValue(2,"Skill");
  }
  
  @BeforeTest
  public void beforeTest() 
  {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Training_C2a.05.01\\Desktop\\SeleniumAutomation\\lib\\chromedriver.exe");
	  driver =new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @AfterTest
  public void afterTest() 
  {
	  driver.quit();
  }

}
