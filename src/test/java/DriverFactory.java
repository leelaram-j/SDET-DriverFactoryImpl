import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.testng.TestNGAntTask.Mode.testng;

/**
 * Q. What is a Singleton design pattern?
 * 	Ensure that only one instance of the singleton class ever exists
 * 	provide global access to that instance.
 * 
 * Q. What is a Factory design pattern?
 * 	Define a separate operation (factory method) for creating an object.
 * 	Create an object by calling a factory method.
 * 
 *
 * Note: Design patterns represent the best practices to follow for solving common design problems.
 */
public class DriverFactory
{
	//Singleton design pattern
	private static final DriverFactory instance = new DriverFactory();

	private DriverFactory(){
	}
	
	public static DriverFactory getInstance(){
		return instance;
	}
	
	//Factory design pattern
	private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();

	public WebDriver getDriver(BrowserType type) {
		if (threadLocal.get() == null) {
			switch (type) {
			case CHROME:
				threadLocal.set(new ChromeDriver());
				break;
			case FIREFOX:
				threadLocal.set(new FirefoxDriver());
				break;
			default:
				break;
			}
		}
		return threadLocal.get();
	}

}