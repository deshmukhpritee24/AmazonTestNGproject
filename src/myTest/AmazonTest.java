package myTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonTest

{
	WebDriver driver;
	
@BeforeMethod	
public void setUp() 

{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\USER\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
	ChromeOptions opt=new ChromeOptions();
	opt.addArguments("--remote-allow-origins=*");
	driver=new ChromeDriver(opt);
	driver.get("https://amazon.in");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
}

@Test

public void verifyTitle()
{
	String actualTitle=driver.getTitle();
	String expectedTitle="Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
	Assert.assertEquals(actualTitle,expectedTitle);
}

@Test 
public void verifyLogo()
{
WebElement logo=driver.findElement(By.xpath("//a[@aria-label=\"Amazon.in\"]"));	
boolean result=logo.isDisplayed();
Assert.assertTrue(result );

}
@Test

public void allbrokenLinks() throws MalformedURLException, IOException
{
List <WebElement> links=driver.findElements(By.xpath("//a[contains(@href,'.')]"));
for(WebElement link:links)
{
String url=link.getAttribute("href");
HttpURLConnection con=(HttpURLConnection) new URL(url).openConnection();
con.setRequestMethod("HEAD");
con.connect();

int respcode=con.getResponseCode();

if(respcode>=400)
{
System.out.println("Link Text:"+link.getText()+"Response Code:"+respcode);	
}

}
}

@AfterMethod
public void tearDown()

{
	driver.quit();
}

}
