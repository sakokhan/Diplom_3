import driver.WebDriverCreator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import static driver.WebDriverCreator.createWebDriver;

public class ConstructorTest {
    private WebDriver driver;
    @Before
    public void setUp() {
        driver = createWebDriver();
    }
    @Test
    @DisplayName("Конструктор")
    @Description("работает переход к разделу соусы")
    public void souseSectionRedirect(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage().click(mainPage.getSousesSection());
        Assert.assertTrue(driver.findElement(mainPage.getSpicyXSouse()).isEnabled());
    }
    @Test
    @DisplayName("Конструктор")
    @Description("работает переход к разделу соусы")
    public void fillingsSectionRedirect(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage().click(mainPage.getFillingsSection());
        Assert.assertTrue(driver.findElement(mainPage.getBeefPatty()).isEnabled());
    }
    @Test
    @DisplayName("Конструктор")
    @Description("работает переход к разделу булки из раздела соусы")
    public void bunSectionRedirect(){
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage().click(mainPage.getSousesSection());
        Assert.assertTrue(driver.findElement(mainPage.getSpicyXSouse()).isEnabled());
        mainPage.click(mainPage.getBunSection());
        Assert.assertTrue(driver.findElement(mainPage.getFluoriscentBun()).isEnabled());
    }
    @After
    public void tearDown() {driver.close();}
}
