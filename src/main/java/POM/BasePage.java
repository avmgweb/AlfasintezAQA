package POM;


import driver.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Дмитрий on 19.04.2017. TEST
 */
public class BasePage
    {

    private WebDriver driver;
    public  static String pageUrl;

    public BasePage(String browser)
    {
        driver = Driver.getInstance(browser);
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void goToUrl(String url)
    {
        driver.navigate().to(url);
    }

    public WebDriver  getDriver()
    {
        return this.driver;
    }

    public String getCurrentUrl(){
        return getDriver().getCurrentUrl();
    }

    public void goToTopOfPage() throws InterruptedException {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollTo(0,0);");
    }

    public boolean isElementDisplayed(By by){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        List<WebElement> list = driver.findElements(by);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        if (list.size() == 0)
            return false;
        else
            return list.get(0).isDisplayed();
    }

    public void changeWindowSize(int width, int height){
        getDriver().manage().window().setSize(new Dimension(width, height));
    }


}