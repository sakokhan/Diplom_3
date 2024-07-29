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
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import user.TokenResponse;
import user.User;
import user.UserClient;

import static driver.WebDriverCreator.createWebDriver;
import static pages.AccountPage.ACCOUNT_URL;
import static user.CreateUser.randomUserAllData;
import static user.UserClient.BASE_URL;

public class EnterAccountTest {
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
    @DisplayName("Вход в аккаунт")
    @Description("вход по кнопке «Войти в аккаунт» на главной")
    public void enterThroughAccountButton(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.openMainPage().click(mainPage.getLogInButton());
        authorization(loginPage);
        mainPage.click(mainPage.getPersonalAccountInProfile());
        Assert.assertEquals("Переход на страницу профиля не состоялся", driver.getCurrentUrl(), ACCOUNT_URL);
    }
    @Test
    @DisplayName("Вход в аккаунт")
    @Description("вход через кнопку «Личный кабинет»")
    public void enterThroughPersonalAccount(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.openMainPage().click(mainPage.getPersonalAccount());
        authorization(loginPage);
        mainPage.click(mainPage.getPersonalAccountInProfile());
        Assert.assertEquals("Переход на страницу профиля не состоялся", driver.getCurrentUrl(), ACCOUNT_URL);
    }
    @Test
    @DisplayName("Вход в аккаунт")
    @Description("вход через кнопку в форме регистрации")
    public void enterThroughRegistrationForm(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage().click(registerPage.getEnterText());
        authorization(loginPage);
        mainPage.click(mainPage.getPersonalAccountInProfile());
        Assert.assertEquals("Переход на страницу профиля не состоялся", driver.getCurrentUrl(), ACCOUNT_URL);
    }
    @Test
    @DisplayName("Вход в аккаунт")
    @Description("вход через кнопку в форме восстановления пароля")
    public void enterThroughPasswordRecovery(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openForgotPasswordPage().click(forgotPasswordPage.getLogInButton());
        authorization(loginPage);
        mainPage.click(mainPage.getPersonalAccountInProfile());
        Assert.assertEquals("Переход на страницу профиля не состоялся", driver.getCurrentUrl(), ACCOUNT_URL);
    }
    @After
    public void tearDown() {
        deleteUser();
        driver.close();}
}
