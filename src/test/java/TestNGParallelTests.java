import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Q. What is the need to run tests in parallel?
 * 	Tests are run in parallel, primarily for 2 reasons:
 * 	a. Faster execution time
 * 			(Typically Test suites take hours to complete depending upon the AUT and whether its full Regression suite etc) 
 * 	b. Test if Application under Test(AUT) is thread-safe
 * 			(When concurrent requests are made to AUT, the application should respond back with corresponding responses/pages)
 * 	
 * Q. How to run tests in parallel?
 * 	Using TestNG suite file - define the 'parallel' attribute to run tests in parallel
 * 		parallel="methods" : Each test method will be run in a separate thread
 * 		parallel="tests"   : Each <test> tag will be run in a separate thread
 * 		parallel="classes" : Each class will be run in a separate thread
 * 		parallel="instances" : Each instace of class will be run in a separate thread
 */
public class  TestNGParallelTests extends BaseTest{
	
	@Test
	void calcTest() {
		WebDriver driver = getDriver();
        
		WebElement element = driver.findElement(By.name("q")); 
        element.sendKeys("sqrt 16");
        element.submit();
        
        WebElement result = driver.findElement(By.id("cwos"));
        Assert.assertEquals(result.getText(), "4");
        System.out.println(Thread.currentThread().getId());
	}
	
	@Test
	void searchTest() {
		WebDriver driver = getDriver(BrowserType.FIREFOX, "http://www.google.com");
        
		WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Selenium WebDriver");
        element.submit();
        
        (new WebDriverWait(driver, 15)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().startsWith("Selenium WebDriver");
            }
        });
        Assert.assertTrue(driver.getTitle().startsWith("Selenium WebDriver"));
        System.out.println(Thread.currentThread().getId());
	}

}
