package POM.Alfasintez;

import POM.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class AlfasintezMainPageRu extends BasePage {

    @FindBy(xpath = "//meta[@name='description']")
    private WebElement description;

    @FindBy(xpath = "//div/div/ul[@id=\"lang-toggle\"]")
    private  WebElement  switchLanguage;

    @FindBy(xpath = "//h1")
    private  WebElement  h1;

    @FindBy (xpath = ".//*[@id='av-alfasintez']/div[4]/div[2]/div[2]/div[2]/div[3]/section/nav/ul")
    private WebElement popupMenu;

    @FindBy (xpath = "//div/div/div/div/ul/li/div/a")
    private List<WebElement> socialButtons;

    @FindBy (xpath = ".//*[@id='av-alfasintez']/div[4]/div[2]/div[2]/div[2]/div[2]/section/nav/ul")
    private WebElement topMenu;

    @FindBy (xpath = ".//*[@id='av-alfasintez']/div[4]/div[6]/div/div[2]")
    private WebElement allProducts;

    @FindBy (xpath = "//div/SECTION/nav/ul/li[@class=\"green-border-btn\"]")
    private List<WebElement> callForm;



    public static String pageUrl = "http://alfasintez.com.ua/ru";


    public AlfasintezMainPageRu(String browser) {
        super(browser);
        goToUrl(pageUrl);
    }

    public AlfasintezMainPageRu(WebDriver driver) {
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

    public AlfasintezMainPageEn switchLanguage(){
        List<WebElement> languages = switchLanguage.findElements(By.className("green-border-btn"));
        for (WebElement element : languages){
            if (element.getAttribute("class").contains("active"));
                element.click();
        }
        return new AlfasintezMainPageEn(getDriver());
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

    public void gotosocNetwork(String socNetwork) throws InterruptedException {
        switch (socNetwork){
            case "gmail" : socialButtons.get(0).click(); Thread.sleep(1000); break;
            case "twitter" : socialButtons.get(1).click();; Thread.sleep(1000); break;
            case "facebook" : socialButtons.get(2).click();; Thread.sleep(1000); break;
            default: System.out.println("Соц. сеть не найдена"); break;
        }

    }

    public String getUrlAndCloseNewWindow() {
        String urlNewWindow = "";
        String parentWindow = getDriver().getWindowHandle();
        Set<String> handles =  getDriver().getWindowHandles();
        for(String windowHandle  : handles)
        {
            if(!windowHandle.equals(parentWindow))
            {
                getDriver().switchTo().window(windowHandle);
                urlNewWindow = getCurrentUrl();
                getDriver().close(); //closing child window
                getDriver().switchTo().window(parentWindow); //cntrl to parent window
            }
        }
        return urlNewWindow;
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

    public void callForm() {
        callForm.get(0).click();
    }


    public void fillingAndSubmitForm(String name, String telephone) throws InterruptedException {
        WebElement nameField = getDriver().findElement(By.cssSelector("input[name=\"form_text_1\"]"));
        WebElement telephoneField = getDriver().findElement(By.cssSelector("input[name=\"form_text_2\"]"));
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
        return getDriver().findElement(By.xpath("//div/form/*[@class=\"alfasintez-question-1\"]/input[@type=\"text\"]")).getAttribute("class").contains("inputError");
    }

    public boolean getStatusFieldTelephone() {
        return getDriver().findElement(By.xpath("//div/form/*[@class=\"alfasintez-question-2\"]/input[@type=\"text\"]")).getAttribute("class").contains("inputError");
    }

    public String getErrorText() {
        return getDriver().findElement(By.xpath(".//*[@id='comp_7fd8098a0e1bfe017b9b6d2b1f9269e2']/div/form/div[2]/p/font")).getText();
    }

    public String getMessageOfSuccesfulSending() {
        return getDriver().findElement(By.xpath(".//*[@id='comp_7fd8098a0e1bfe017b9b6d2b1f9269e2']/div/div/span")).getText();
    }

    public boolean isMobileMenuDisplayed(){
        return isElementDisplayed(By.id("hamburger"));
    }

    public AlfasintezMainPageEn goToEnPageWithMobileMenu() throws InterruptedException {
        getDriver().findElement(By.id("hamburger")).click();
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.visibilityOf(getDriver().findElement(By.xpath(".//*[@id='mobile-menu']/li[6]/a[2]"))));
        getDriver().findElement(By.xpath(".//*[@id='mobile-menu']/li[6]/a[2]")).click();
        return new AlfasintezMainPageEn(getDriver());
    }

    public void closeForm() {
        getDriver().findElement(By.xpath(".//*[@id='av-alfasintez']/div[2]/div[2]")).click();
    }

    public void closeMobileMenu() {
        getDriver().findElement(By.id("hamburger")).click();
    }

    public void refreshRu() {
        getDriver().navigate().to(pageUrl);
        (new WebDriverWait(getDriver(), 10))
                .until(ExpectedConditions.visibilityOf(topMenu));
    }


}
