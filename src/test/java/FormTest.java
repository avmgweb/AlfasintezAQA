/**
 * Created by Дмитрий on 20.04.2017.
 */
import POM.Alfasintez.AlfasintezMainPageEn;
import POM.Alfasintez.AlfasintezMainPageRu;
import POM.Alfasintez.FormData;
import driver.Driver;
import org.testng.Assert;
import org.testng.annotations.*;
import sun.awt.windows.ThemeReader;

/**
 * Created by Дмитрий on 19.04.2017.
 */
public class FormTest {

    public AlfasintezMainPageRu alfasintezMainPageRu;
    public AlfasintezMainPageEn alfasintezMainPageEn;
    public FormData data;
    public FormData validDataRu;
    public FormData validDataEn;


    @Parameters("browser")
    @BeforeTest
    public void setUp(String browser) {
        alfasintezMainPageRu = new AlfasintezMainPageRu(browser);
    }


    @BeforeClass
    public void maximizeDriver() throws InterruptedException {
        Driver.maximize();
    }

    @AfterClass
    public  void logOut() throws InterruptedException {

    }

    @AfterTest
    public static void tearDown(){
        Driver.tearDown();
        Driver.nullDriver();
    }


    @Test (priority = 1)
    public void negativeCheckSubmitFormWithoutDateRu() throws InterruptedException {
        alfasintezMainPageRu.callForm();
        alfasintezMainPageRu.fillingAndSubmitForm("", "");
        Assert.assertTrue(alfasintezMainPageRu.getStatusFieldName(), "Error in statusFieldName");
        Assert.assertTrue(alfasintezMainPageRu.getStatusFieldTelephone(), "Error in statusTelephoneName");
    }

    @Test (priority = 2)
    public void negativeCheckSubmitFormWithWrongNameAndTelephoneRu() throws InterruptedException {
        data = FormData.generate(2,9);
        alfasintezMainPageRu.fillingAndSubmitForm(data.getName(), data.getTelephone());
        Assert.assertTrue(alfasintezMainPageRu.getStatusFieldName(), "Error in statusFieldName");
        Assert.assertTrue(alfasintezMainPageRu.getStatusFieldTelephone(), "Error in statusTelephoneName");

    }

    @Test (priority = 3)
    public void negativeCheckSubmitFormWithWrongNameAndValidTelephoneRu() throws InterruptedException {
        data = FormData.generate(2,13);
        alfasintezMainPageRu.fillingAndSubmitForm(data.getName(), data.getTelephone());
        Assert.assertTrue(alfasintezMainPageRu.getStatusFieldName(), "Error in statusFieldName");
        Assert.assertTrue(!(alfasintezMainPageRu.getStatusFieldTelephone()), "Error in statusTelephoneName");
    }

    @Test (priority = 4)
    public void negativeCheckSubmitFormWithValidNameAndWrongTelephoneRu() throws InterruptedException {
        data = FormData.generate(3,5);
        alfasintezMainPageRu.fillingAndSubmitForm(data.getName(), data.getTelephone());
        Assert.assertTrue(!(alfasintezMainPageRu.getStatusFieldName()), "Error in statusFieldName");
        Assert.assertTrue((alfasintezMainPageRu.getStatusFieldTelephone()), "Error in statusTelephoneName");
    }

    @Test (enabled = false)
    public void negativeCheckSubmitFormWithWrongLongNameAndValidTelephoneRu() throws InterruptedException {
        data = FormData.generate(40,13);
        alfasintezMainPageRu.fillingAndSubmitForm(data.getName(), data.getTelephone());
        Assert.assertTrue(alfasintezMainPageRu.getErrorText().contains("Имя: слишком длинное значение"), "Error in status");
    }

    @Test (priority = 6)
    public void positiveCheckSubmitFormWithValidNameAndValidTelephoneRu() throws InterruptedException {
        validDataRu = FormData.generate(15,11);
        alfasintezMainPageRu.fillingAndSubmitForm(validDataRu.getName(), validDataRu.getTelephone());
        System.out.print(alfasintezMainPageRu.getMessageOfSuccesfulSending());
        Assert.assertTrue(alfasintezMainPageRu.getMessageOfSuccesfulSending().contains("СПАСИБО ЗА ЗАЯВКУ"), "Error");
        alfasintezMainPageRu.closeForm();
        Thread.sleep(2000);
    }

    @Test (priority = 7)
    public void mobileMenuCheck() throws InterruptedException {
        alfasintezMainPageRu.changeWindowSize(768, 1024);
        alfasintezMainPageRu.closeMobileMenu();
        Assert.assertTrue(alfasintezMainPageRu.isMobileMenuDisplayed(),"ERROR");
        alfasintezMainPageEn = alfasintezMainPageRu.goToEnPageWithMobileMenu();
        Assert.assertTrue(alfasintezMainPageEn.isMobileMenuDisplayed(),"ERROR");
        alfasintezMainPageRu.changeWindowSize(1920, 1080);
    }

    @Test (priority = 8)
    public void negativeCheckSubmitFormWithoutDateEn() throws InterruptedException {
        alfasintezMainPageEn.callForm();
        alfasintezMainPageEn.fillingAndSubmitForm("", "");
        Assert.assertTrue(alfasintezMainPageEn.getStatusFieldName(), "Error in statusFieldName");
        Assert.assertTrue(alfasintezMainPageEn.getStatusFieldTelephone(), "Error in statusTelephoneName");
    }

    @Test (priority = 9)
    public void negativeCheckSubmitFormWithWrongNameAndTelephoneEn() throws InterruptedException {
        data = FormData.generate(2,9);
        alfasintezMainPageEn.fillingAndSubmitForm(data.getName(), data.getTelephone());
        Assert.assertTrue(alfasintezMainPageEn.getStatusFieldName(), "Error in statusFieldName");
        Assert.assertTrue(alfasintezMainPageEn.getStatusFieldTelephone(), "Error in statusTelephoneName");

    }

    @Test (priority = 10)
    public void negativeCheckSubmitFormWithWrongNameAndValidTelephoneEn() throws InterruptedException {
        data = FormData.generate(2,13);
        alfasintezMainPageEn.fillingAndSubmitForm(data.getName(), data.getTelephone());
        Assert.assertTrue(alfasintezMainPageEn.getStatusFieldName(), "Error in statusFieldName");
        Assert.assertTrue(!(alfasintezMainPageEn.getStatusFieldTelephone()), "Error in statusTelephoneName");
    }

    @Test (priority = 11)
    public void negativeCheckSubmitFormWithValidNameAndWrongTelephoneEn() throws InterruptedException {
        data = FormData.generate(3,5);
        alfasintezMainPageEn.fillingAndSubmitForm(data.getName(), data.getTelephone());
        Assert.assertTrue(!(alfasintezMainPageEn.getStatusFieldName()), "Error in statusFieldName");
        Assert.assertTrue((alfasintezMainPageEn.getStatusFieldTelephone()), "Error in statusTelephoneName");
    }

    @Test  (enabled = false)
    public void negativeCheckSubmitFormWithWrongLongNameAndValidTelephoneEn() throws InterruptedException {
        data = FormData.generate(40,13);
        alfasintezMainPageEn.fillingAndSubmitForm(data.getName(), data.getTelephone());
        Assert.assertTrue(alfasintezMainPageEn.getErrorText().contains("Name: text is too long"), "Error in status");
    }

    @Test (priority = 13)
    public void positiveCheckSubmitFormWithValidNameAndValidTelephoneEn() throws InterruptedException {
        validDataEn = FormData.generate(15,11);
        alfasintezMainPageEn.fillingAndSubmitForm(validDataEn.getName(), validDataEn.getTelephone());
        System.out.print(alfasintezMainPageEn.getMessageOfSuccesfulSending());
        Assert.assertTrue(alfasintezMainPageEn.getMessageOfSuccesfulSending().contains("THANK YOU FOR YOUR APPLICATION"), "Error");
    }
}
