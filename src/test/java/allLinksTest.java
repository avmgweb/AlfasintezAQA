/**
 * Created by Дмитрий on 20.04.2017.
 */
import POM.Alfasintez.AlfasintezMainPageEn;
import POM.Alfasintez.AlfasintezMainPageRu;
import driver.Driver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Дмитрий on 19.04.2017.
 */
public class allLinksTest {

    public AlfasintezMainPageRu alfasintezMainPageRu;
    public AlfasintezMainPageEn alfasintezMainPageEn;
    public AlfasintezMainPageEn TitleDescriptionTegsText;
    public String browser;
    public boolean flag = true;

    @Parameters("browser")
    @BeforeTest
    public void setUp(String browser) {
        alfasintezMainPageRu = new AlfasintezMainPageRu(browser);
        this.browser = browser;
    }


    @BeforeClass
    public void maximizeDriver() throws InterruptedException {
        Driver.maximize();
    }

    @AfterClass
    public void logOut() throws InterruptedException {
    }

    @AfterTest
    public static void tearDown() {
        Driver.tearDown();
        Driver.nullDriver();
    }
        @Test (priority = 1)
        public void checkMainMenuProductsLinkRu() throws InterruptedException {
            alfasintezMainPageRu.goToBlockOfMainMenu("Продукция");
            Assert.assertTrue(alfasintezMainPageRu.getCurrentUrl().contains("#production"), "Error in Main Menu");
            alfasintezMainPageRu.goToBlockOfPopupMenu("Продукция");
            Assert.assertTrue(alfasintezMainPageRu.getCurrentUrl().contains("#production"), "Error in Popup Menu");
            alfasintezMainPageRu.refreshRu();
        }

        @Test (priority = 2)
        public void checkMainMenuOurPartnersLinkRu() throws InterruptedException {
            alfasintezMainPageRu.goToBlockOfMainMenu("С нами сотрудничают");
            Assert.assertTrue(alfasintezMainPageRu.getCurrentUrl().contains("#ourPartners"), "Error in Main Menu");
            alfasintezMainPageRu.goToBlockOfPopupMenu("С нами сотрудничают");
            Assert.assertTrue(alfasintezMainPageRu.getCurrentUrl().contains("#ourPartners"), "Error in Popup Menu");
            alfasintezMainPageRu.refreshRu();
        }

        @Test (priority = 2)
        public void checkMainMenuOurCertificatesLinkRu() throws InterruptedException {
            alfasintezMainPageRu.goToBlockOfMainMenu("Наши сертификаты");
            Assert.assertTrue(alfasintezMainPageRu.getCurrentUrl().contains("#ourCertificates"), "Error in Main Menu");
            alfasintezMainPageRu.goToBlockOfPopupMenu("Наши сертификаты");
            Assert.assertTrue(alfasintezMainPageRu.getCurrentUrl().contains("#ourCertificates"), "Error in Popup Menu");
            alfasintezMainPageRu.goToTopOfPage();
            alfasintezMainPageRu.refreshRu();
        }

        @Test (priority = 3)
        public void checkMainMenuContactsLinkRu() throws InterruptedException {
            alfasintezMainPageRu.goToBlockOfMainMenu("Контакты");
            Assert.assertTrue(alfasintezMainPageRu.getCurrentUrl().contains("#contacts"), "Error in Main Menu");
            alfasintezMainPageRu.goToBlockOfPopupMenu("Контакты");
            Assert.assertTrue(alfasintezMainPageRu.getCurrentUrl().contains("#contacts"), "Error in Popup Menu");
            alfasintezMainPageRu.refreshRu();
        }

        @Test  (priority = 4)
        public void checkSocialNetworkRu() throws InterruptedException {
            alfasintezMainPageRu.gotosocNetwork("gmail");
            Thread.sleep(1500);
            Assert.assertTrue(alfasintezMainPageRu.getUrlAndCloseNewWindow().contains("https://plus.google.com/107163398754465430169"),
                    "Gmail error");
            //alfasintezMainPageRu.gotosocNetwork("vk");
            //Thread.sleep(1500);
            //Assert.assertTrue(alfasintezMainPageRu.getUrlAndCloseNewWindow().contains("https://vk.com/alfasintez"),
                    //"Vk error");
            alfasintezMainPageRu.gotosocNetwork("twitter");
            Thread.sleep(1500);
            Assert.assertTrue(alfasintezMainPageRu.getUrlAndCloseNewWindow().contains("https://twitter.com/Alfasintezcomua"),
                    "Twitter error");
            alfasintezMainPageRu.gotosocNetwork("facebook");
            Thread.sleep(1500);
            Assert.assertTrue(alfasintezMainPageRu.getUrlAndCloseNewWindow().contains("https://www.facebook.com/alfasintez.com.ua/"),
                    "Facebook error");
        }

        @Test  (priority = 5)
        public void checkProductionLinksRu() throws InterruptedException {
            Thread.sleep(1500);
            alfasintezMainPageRu.goToTopOfPage();
            Assert.assertTrue(alfasintezMainPageRu.checkProducts(),
                    "Product links error  ");
        }

    @Test(priority = 7)
    public void checkProductionStyleRu() throws InterruptedException {
        System.out.println(browser);
        alfasintezMainPageRu.goToTopOfPage();
        if (browser == "chrome")
        flag = alfasintezMainPageRu.checkHower();
        Assert.assertTrue(flag, "Product style error");
        Thread.sleep(1000);
    }


    @Test (priority = 8)
    public void checkMainMenuProductsLinkEn() throws InterruptedException {
        alfasintezMainPageEn = alfasintezMainPageRu.switchLanguage();
        alfasintezMainPageEn.goToBlockOfMainMenu("Products");
        Assert.assertTrue(alfasintezMainPageEn.getCurrentUrl().contains("#production"), "Error in Main Menu");
        alfasintezMainPageEn.goToBlockOfPopupMenu("Products");
        Assert.assertTrue(alfasintezMainPageEn.getCurrentUrl().contains("#production"), "Error in Popup Menu");
        alfasintezMainPageEn.refreshEn();
    }

    @Test (priority = 9)
    public void checkMainMenuOurPartnersLinkEn() throws InterruptedException {
        alfasintezMainPageEn.goToBlockOfMainMenu("Our partners");
        Assert.assertTrue(alfasintezMainPageEn.getCurrentUrl().contains("#ourPartners"), "Error in Main Menu");
        alfasintezMainPageEn.goToBlockOfPopupMenu("Our partners");
        Assert.assertTrue(alfasintezMainPageEn.getCurrentUrl().contains("#ourPartners"), "Error in Popup Menu");
        alfasintezMainPageEn.refreshEn();
    }

    @Test (priority = 10)
    public void checkMainMenuOurCertificatesLinkEn() throws InterruptedException {
        alfasintezMainPageEn.goToBlockOfMainMenu("Our certificates");
        Assert.assertTrue(alfasintezMainPageEn.getCurrentUrl().contains("#ourCertificates"), "Error in Main Menu");
        alfasintezMainPageEn.goToBlockOfPopupMenu("Our certificates");
        Assert.assertTrue(alfasintezMainPageEn.getCurrentUrl().contains("#ourCertificates"), "Error in Popup Menu");
        alfasintezMainPageEn.goToTopOfPage();
        alfasintezMainPageEn.refreshEn();
    }

    @Test (priority = 11)
    public void checkMainMenuContactsLinkEn() throws InterruptedException {
        alfasintezMainPageEn.goToBlockOfMainMenu("Contacts");
        Assert.assertTrue(alfasintezMainPageEn.getCurrentUrl().contains("#contacts"), "Error in Main Menu");
        alfasintezMainPageEn.goToBlockOfPopupMenu("Contacts");
        Assert.assertTrue(alfasintezMainPageEn.getCurrentUrl().contains("#contacts"), "Error in Popup Menu");
        alfasintezMainPageEn.refreshEn();
    }

    @Test  (priority = 11)
    public void checkSocialNetworkEn() throws InterruptedException {
        alfasintezMainPageRu.gotosocNetwork("gmail");
        Thread.sleep(1000);
        Assert.assertTrue(alfasintezMainPageRu.getUrlAndCloseNewWindow().contains("https://plus.google.com/107163398754465430169"),
                "Gmail error");
        //alfasintezMainPageRu.gotosocNetwork("vk");
        //Thread.sleep(1000);
        //Assert.assertTrue(alfasintezMainPageRu.getUrlAndCloseNewWindow().contains("https://vk.com/alfasintez"),
                //"Vk error");
        alfasintezMainPageRu.gotosocNetwork("twitter");
        Thread.sleep(1000);
        Assert.assertTrue(alfasintezMainPageRu.getUrlAndCloseNewWindow().contains("https://twitter.com/Alfasintezcomua"),
                "Twitter error");
        alfasintezMainPageRu.gotosocNetwork("facebook");
        Thread.sleep(1000);
        Assert.assertTrue(alfasintezMainPageRu.getUrlAndCloseNewWindow().contains("https://www.facebook.com/alfasintez.com.ua/"),
                "Facebook error");
    }

    @Test  (priority = 12)
    public void checkProductionLinksEn() throws InterruptedException {
        Thread.sleep(1500);
        alfasintezMainPageEn.goToTopOfPage();
        Assert.assertTrue(alfasintezMainPageEn.checkProducts(),
                "Product links error  ");
    }

    @Test(priority = 13)
    public void checkProductionStyleEn() throws InterruptedException {
        System.out.println(browser);
        alfasintezMainPageEn.goToTopOfPage();
        if (browser == "chrome")
            flag = alfasintezMainPageEn.checkHower();
        Assert.assertTrue(flag, "Product style error");
    }

}

