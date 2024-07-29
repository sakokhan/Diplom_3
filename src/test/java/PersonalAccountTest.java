import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.AccountPage;
import pages.LoginPage;
import pages.MainPage;
import user.TokenResponse;
import user.User;
import user.UserClient;

import static driver.WebDriverCreator.createWebDriver;
import static pages.AccountPage.ACCOUNT_URL;
import static pages.LoginPage.LOGIN_URL;
import static user.CreateUser.randomUserAllData;
import static user.UserClient.BASE_URL;

public class PersonalAccountTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    private Response response;
    @Before
    public void setUp() {
        driver = createWebDriver();
        RestAssured.baseURI = BASE_URL;
        userClient = new UserClient();
        createUser();
        userRegistration();
    }
    @Step
    public User createUser(){
        user = randomUserAllData();
        return user;
    }
    @Step
    public Response userRegistration(){
        response = userClient.userRegistration(user);
        return response;
    }
    @Step
    public void deleteUser(){
        Response authResponse = userClient.userAuthorization(user);
        TokenResponse tokenResponse = authResponse.as(TokenResponse.class);
        String token = tokenResponse.getAccessToken();
        userClient.deleteUser(user, token);
    }
    @Step
    public void authorization(LoginPage loginPage){
        loginPage.fillEnterForm(user.getEmail(), user.getPassword())
                .click(loginPage.getEnterButton());
    }
    @Test
    @DisplayName("Личный кабинет")
    @Description("переход по клику на «Личный кабинет»")
    public void redirectByPersonalAccountInAccount(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.openMainPage().click(mainPage.getLogInButton());
        authorization(loginPage);
        mainPage.click(mainPage.getPersonalAccountInProfile());
        Assert.assertEquals("Переход на форму аккаунта не состоялся", driver.getCurrentUrl(), ACCOUNT_URL);
    }
    @Test
    @DisplayName("Личный кабинет")
    @Description("переход по клику на кнопку «Конструктор»")
    public void redirectFromPAToConstructor(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.openMainPage().click(mainPage.getLogInButton());
        authorization(loginPage);
        mainPage.getPersonalAccountInProfile();
        accountPage.click(accountPage.getConstructorButton());
        Assert.assertTrue(driver.findElement(mainPage.getFluoriscentBun()).isEnabled());
    }
    @Test
    @DisplayName("Личный кабинет")
    @Description("переход по клику на логотип Stellar Burgers")
    public void redirectFromPAToConstructorByLogo(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.openMainPage().click(mainPage.getLogInButton());
        authorization(loginPage);
        mainPage.getPersonalAccountInProfile();
        accountPage.click(accountPage.getConstructorButton());
        Assert.assertTrue(driver.findElement(mainPage.getFluoriscentBun()).isEnabled());
    }
    @Test
    @DisplayName("Личный кабинет")
    @Description("переход по клику на логотип Stellar Burgers")
    public void exitFromAccount(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountPage accountPage = new AccountPage(driver);
        mainPage.openMainPage().click(mainPage.getLogInButton());
        authorization(loginPage);
        mainPage.getPersonalAccountInProfile();
        accountPage.getExitButton();
        Assert.assertEquals("Переход на форму входа в аккаунт не состоялся", driver.getCurrentUrl(), LOGIN_URL);
    }
    @After
    public void tearDown() {
        deleteUser();
        driver.close();}
}
