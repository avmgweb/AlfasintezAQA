import POM.Alfasintez.AlfasintezMainPageEn;
import POM.Alfasintez.AlfasintezMainPageRu;
import POM.Alfasintez.FormData;
import driver.Driver;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Created by Дмитрий on 19.04.2017.
 */
public class TitleDescriptionTegsText {

    public AlfasintezMainPageRu alfasintezMainPageRu;
    public AlfasintezMainPageEn alfasintezMainPageEn;
    public FormData data;


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


    @Test ( priority = 1 )
    public void checkTitleRuVercion(){
        Assert.assertTrue(alfasintezMainPageRu.getTitle().contains("Альфасинтез. Производитель упаковки. Европейское качество."));
        System.out.println("TitleRu: " + alfasintezMainPageRu.getTitle());
    }

    @Test ( priority = 2 )
    public void checkH1CaptionRuVercion(){
        System.out.println("H1Ru: " + alfasintezMainPageRu.getHOneCaption());
        Assert.assertTrue(alfasintezMainPageRu.getHOneCaption().contains("МЫ УПАКОВЫВАЕМ ПРОДУКТЫ ПИТАНИЯ"));

    }

    @Test ( priority = 3 )
    public void checkDescriptionRuVercion() throws InterruptedException {
        Assert.assertTrue(alfasintezMainPageRu.getDescription().contains("Мы производим и продаем такие товары: упаковка для яиц, упаковка для фруктов, упаковка для овощей, упаковка для мяса."));
        System.out.println("DescriptionRu: " + alfasintezMainPageRu.getDescription());
        alfasintezMainPageEn = alfasintezMainPageRu.switchLanguage();
        Thread.sleep(1000);
    }

    @Test ( priority = 4 )
    public void checkTitleEnVercion(){
        Assert.assertTrue(alfasintezMainPageEn.getTitle().contains("Alfasintez"));
        System.out.println("TitleEn: " + alfasintezMainPageEn.getTitle());
    }

    @Test (enabled = false)
    public void checkDescriptionEnVercion(){
        Assert.assertTrue(alfasintezMainPageEn.getDescription().contains(""));
        System.out.println("DescriptionEn: " + alfasintezMainPageEn.getDescription());
    }

    @Test ( priority = 6 )
    public void checkH1CaptionEnVercion(){
        System.out.println("H1Ru: " + alfasintezMainPageEn.getHOneCaption());
        Assert.assertTrue(alfasintezMainPageEn.getHOneCaption().contains("WE PACK FOODSTUFF"));
    }

    @Test ( priority = 7 )
    public void check404Page(){
        data =  FormData.generate(10);
        alfasintezMainPageEn.goToUrl(alfasintezMainPageEn.getCurrentUrl()+data.random);
        Assert.assertTrue(alfasintezMainPageEn.getTitle().contains("Страница не найдена"));
    }

}
