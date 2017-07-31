package driver;

import logger.WebEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Дмитрий on 30.03.2017.
 */
    public class Driver {

    private static WebDriver driver;

    public static WebDriver getInstance(String browser)
    {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        System.setProperty("webdriver.ie.driver", "src/main/resources/IEDriverServer.exe");

        if (driver==null)
        {
            switch (browser)
            {
                case "firefox":  driver = new FirefoxDriver();
                    break;
                case "ie":  driver = new InternetExplorerDriver();
                    break;
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                default: driver = new FirefoxDriver();
            }
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);

            eventDriver.register(new WebEventListener());

            return eventDriver.register(new WebEventListener());
        }

        driver.manage().window().maximize();
        return driver;
    }

    public static void tearDown(){
        driver.quit();

    }

    public static void maximize(){
        driver.manage().window().maximize();

    }
    public static void nullDriver(){
        driver = null;

    }



}