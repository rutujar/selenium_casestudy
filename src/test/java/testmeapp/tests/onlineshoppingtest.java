package testmeapp.tests;
import testmeapp.utility.Drivers;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class onlineshoppingtest {
	  WebDriver driver=Drivers.getDriver("chrome");
	  ExtentHtmlReporter reporter=new ExtentHtmlReporter("C:\\Users\\training_b6B.01.16\\Desktop\\test1.html");
	  ExtentReports extent=new ExtentReports();
	  ExtentTest logger=extent.createTest("testmeapp");
	 
	 
  @AfterMethod
  public void getResultAfterMethod(ITestResult result) throws IOException{

	   extent.attachReporter(reporter);

	  if(result.getStatus()==ITestResult.SUCCESS)
	  {
		  logger.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"test case passed", ExtentColor.BLUE));
		  TakesScreenshot snapshot=(TakesScreenshot)driver;
		  File src=snapshot.getScreenshotAs(OutputType.FILE);
		  String path=System.getProperty("user.dir")+"/extent.reports/snapshots"+result.getName()+".png";
		  FileUtils.copyFile(src, new File(path));
		  logger.addScreenCaptureFromPath(path,result.getName());
		  //logger.log(Status.PASS,MarkupHelper.createLabel(result.getName()+"test case failed", ExtentColor.RED));
		  
	  }
	  else if(result.getStatus()==ITestResult.SKIP)
	  {
		  logger.log(Status.SKIP,MarkupHelper.createLabel(result.getName()+"test case skiped", ExtentColor.ORANGE));
		  
	  }
	  extent.flush();
	  
  }

  @BeforeTest
  public void startReportBeforeTest() {
	  
	  String url="http://10.232.237.143:443/TestMeApp";
	  driver.navigate().to(url);
	  driver.manage().window().maximize();
	  extent.attachReporter(reporter);
	  
  }

  @AfterTest
  public void endReportAfterTest() {
	  driver.findElement(By.linkText("SignOut")).click();	 
	  driver.close();
  }

  @Test(priority=1)
  public void testRegistration()
  {

	  driver.findElement(By.xpath("//a[@href='RegisterUser.htm']")).click();
	  driver.findElement(By.id("userName")).sendKeys("rutuja18");
	  driver.findElement(By.id("err"));
	  driver.findElement(By.id("firstName")).sendKeys("rutuja");
	  driver.findElement(By.id("lastName")).sendKeys("rajesh");
	  driver.findElement(By.name("password")).sendKeys("ruturajesh");
	  driver.findElement(By.name("confirmPassword")).sendKeys("ruturajesh");
	  WebElement pc=driver.findElement(By.xpath("//input[@value='Female']"));
	  pc.click();
	  driver.findElement(By.id("emailAddress")).sendKeys("ruturajesh@gmail.com");
	  driver.findElement(By.id("mobileNumber")).sendKeys("1234567890");
	  driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("22/09/2019");
	  driver.findElement(By.id("address")).sendKeys("bangalore");
	  
	  Select p=new Select(driver.findElement(By.xpath("//select[@id='securityQuestion']")));
	  p.selectByIndex(1);
	  driver.findElement(By.id("answer")).sendKeys("blue");
	  driver.findElement(By.name("Submit")).click();	 
	   
	   
  }
  
  @Test(priority=2)
  public void testLogin()
  {
		 
	   
	  driver.findElement(By.name("userName")).sendKeys("lalitha");
	  driver.findElement(By.name("password")).sendKeys("password123");
	  driver.findElement(By.name("Login")).click();
  }
  
  @Test(priority=3)
  public void testCart() throws InterruptedException {
	  Actions act1=new Actions(driver);
	  act1.moveToElement(driver.findElement(By.xpath("//a[@href='#']"))).perform();
	  Thread.sleep(1000);
	  act1.moveToElement(driver.findElement(By.linkText("Electronics"))).click().build().perform();
	  Thread.sleep(1000);
	  act1.moveToElement(driver.findElement(By.linkText("Head Phone"))).click().build().perform();
	  driver.findElement(By.xpath("//a[@href='#' and @class='btn btn-success btn-product']")).click();
	  driver.findElement(By.xpath("//a[@href='displayCart.htm']")).click();
	  driver.findElement(By.xpath("//a[@href='checkout.htm']")).click();

  }
  
  @Test(priority=4)
  public void testPayment() throws InterruptedException
  {
	  driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
	  Thread.sleep(10000);
	  driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div/div/div[1]/div[1]/div")).click();
	  driver.findElement(By.id("btn")).click();
	  driver.findElement(By.name("username")).sendKeys("123456");
	  driver.findElement(By.name("password")).sendKeys("Pass@456");
	  driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div/div/div/form/div/div[3]/input")).click();
	  driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
	  driver.findElement(By.xpath("//input[@value='PayNow']")).click();
  }
}
