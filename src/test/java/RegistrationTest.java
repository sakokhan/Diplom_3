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
import pages.LoginPage;
import pages.RegisterPage;
import user.TokenResponse;
import user.User;
import user.UserClient;

import java.time.Duration;

import static driver.WebDriverCreator.createWebDriver;
import static java.util.concurrent.TimeUnit.SECONDS;
import static user.CreateUser.randomUserAllData;
import static user.UserClient.BASE_URL;
import static utils.Utils.randomString;

public class RegistrationTest {
    private WebDriver driver;
    private UserClient userClient;
    private User user;
    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(3, SECONDS.toChronoUnit()));
        RestAssured.baseURI = BASE_URL;
        userClient = new UserClient();
        createUser();
    }
    @Step
    public User createUser(){
        user = randomUserAllData();
        return user;
    }
    @Step
    public void deleteUser(){
        Response authResponse = userClient.userAuthorization(user);
        TokenResponse tokenResponse = authResponse.as(TokenResponse.class);
        String token = tokenResponse.getAccessToken();
        userClient.deleteUser(user, token);
    }
    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Регистрация пользователя успешный сценарий")
    public void successfulRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        registerPage.openRegisterPage()
        .fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword())
        .click(registerPage.getRegistrationButton());
        if(driver.findElement(loginPage.getEnterText()).isDisplayed()) {
            System.out.println("Пользователь зарегистрирован");
        }else {
            System.out.println("Пользователь не зарегистрирован");
        }
        deleteUser();
    }
    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Регистрация пользователя пароль меньше 6 символов")
    public void registrationBadPassword() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openRegisterPage()
                .fillRegistrationForm(user.getName(), user.getEmail(), randomString(5))
                .click(registerPage.getRegistrationButton());
        Assert.assertTrue(driver.findElement(registerPage.getError()).isDisplayed());
    }
    @After
    public void tearDown() {driver.close();}

}
