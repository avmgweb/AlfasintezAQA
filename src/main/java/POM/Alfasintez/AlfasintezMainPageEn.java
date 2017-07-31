package POM.Alfasintez;

import POM.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

/**
 * Created by Дмитрий on 19.04.2017.
 */
public class AlfasintezMainPageEn extends BasePage{
    @FindBy (xpath = "//div/SECTION/nav/ul/li[@class=\"green-border-btn\"]")
    private List<WebElement> callForm;

    @FindBy(xpath = "//meta[@name='description']")
    private WebElement description;

    @FindBy(xpath = "//h1")
    private  WebElement  h1;

    @FindBy (xpath = ".//*[@id='av-alfasintez']/div[5]/div[2]/div/div[2]/div[3]/section/nav/ul")
    private WebElement popupMenu;

    @FindBy (xpath = ".//*[@id='av-alfasintez']/div[5]/div[2]/div/div[2]/div[2]/section/nav/ul")
    private WebElement topMenu;

    public static String pageUrl = "http://alfasintez.com.ua/en";

    @FindBy (xpath = ".//*[@id='av-alfasintez']/div[5]/div[6]/div/div[2]")
    private WebElement allProducts;

    public AlfasintezMainPageEn(String browser) {
        super(browser);
    }

    public AlfasintezMainPageEn(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getDriver().getTitle();
    }

    public String getDescription() {
        return description.getAttribute("content");
    }

    public String getHOneCaption() {
        return h1.getText();
    }



    public boolean isMobileMenuDisplayed(){
        return isElementDisplayed(By.id("hamburger"));
    }

    public void fillingAndSubmitForm(String name, String telephone) throws InterruptedException {
        WebElement nameField = getDriver().findElement(By.cssSelector("input[name=\"form_text_3\"]"));
        WebElement telephoneField = getDriver().findElement(By.cssSelector("input[name=\"form_text_4\"]"));
        WebElement submit = getDriver().findElement(By.cssSelector("input[name=\"web_form_submit\"]"));
        nameField.clear();
        nameField.sendKeys(name);
        telephoneField.clear();
        telephoneField.sendKeys(telephone);
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.elementToBeClickable(submit));
        Thread.sleep(500);
        submit.click();
    }

    public boolean getStatusFieldName() {
        return getDriver().findElement(By.xpath("//div/form/*[@class=\"alfasintez-question-3\"]/input[@type=\"text\"]")).getAttribute("class").contains("inputError");
    }

    public String getErrorText() {
        return getDriver().findElement(By.xpath(".//*[@id='comp_01be4c52e1e83bc338d959646dee1da8']/div/form/div[2]/p/font")).getText();
    }

    public String getMessageOfSuccesfulSending() {
        return getDriver().findElement(By.xpath(".//*[@id='comp_01be4c52e1e83bc338d959646dee1da8']/div/div/span")).getText();
    }

    public boolean getStatusFieldTelephone() {
        return getDriver().findElement(By.xpath("//div/form/*[@class=\"alfasintez-question-4\"]/input[@type=\"text\"]")).getAttribute("class").contains("inputError");
    }

    public void callForm() throws InterruptedException {
        callForm.get(0).click();
    }

    public void goToBlockOfPopupMenu(String nameOfBlock){
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.visibilityOf(popupMenu));
        List<WebElement> elementsOfMenu = popupMenu.findElements(By.xpath("li/a"));
        for (WebElement punktOfMenu : elementsOfMenu){
            if (punktOfMenu.getText().contains(nameOfBlock)) {
                System.out.println(punktOfMenu.getText());
                punktOfMenu.click();
            }
        }
    }

    public void goToBlockOfMainMenu(String nameOfBlock){
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.visibilityOf(topMenu));
        List<WebElement> elementsOfMenu = topMenu.findElements(By.xpath("li/a"));
        for (WebElement punktOfMenu : elementsOfMenu){
            if (punktOfMenu.getText().contains(nameOfBlock)) {
                punktOfMenu.click();
            }
        }
    }

    public void refreshEn() {
        getDriver().navigate().to(pageUrl);
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.visibilityOf(topMenu));
    }

    public Boolean checkProducts() throws InterruptedException {
        Boolean flag = true;
        List <WebElement> products = allProducts.findElements(By.xpath("div/div/div/div/a"));
        for (WebElement product : products) {
            product.click();
            String parentWindow = getDriver().getWindowHandle();
            Set<String> handles =  getDriver().getWindowHandles();
            for(String windowHandle  : handles) {
                if(!windowHandle.equals(parentWindow)) {
                    getDriver().switchTo().window(windowHandle);
                    if (getDriver().getTitle().contains("Страница не найдена"))
                        flag = false;
                    Thread.sleep(1000);
                    getDriver().close();
                    getDriver().switchTo().window(parentWindow);
                }
            }
        }
        return flag;
    }

    public Boolean checkHower() throws InterruptedException {
        Boolean flag = true;
        List <WebElement> products = allProducts.findElements(By.xpath("div/div/div/div/a"));
        for (WebElement product : products) {
            Actions action = new Actions(getDriver());
            action.moveToElement(product).build().perform();
            if (!product.getCssValue("color").contains("rgba(141, 192, 75, 1)"))
                flag = false;
            Thread.sleep(300);
        }
        return flag;
    }
}
