package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private WebDriver webDriver;
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    // Вы — новый пользователь? Зарегистрироваться
    private final By registration = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a");
    // поле ввода email
    private final By emailInputEnter =By.xpath ("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    // поле ввода пароля
    private final By passwordInputEnter = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    // кнопка входа в личный кабинет
    private final By personalAccount = By.xpath("//*[@id=\"root\"]/div/header/nav/a/p");
    // кнопка "Войти"
    private final By enterButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    //  кнопка войти в форме восстановления пароля
    private final By enterInRecoveryForm = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    // кнопка "восстановить пароль"
    private final By passwordRecovery = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[2]/a");
    // заголовок вход
    private final By enterText = By.xpath("//*[@id=\"root\"]/div/main/div/h2");
    public LoginPage(WebDriver webDriver){this.webDriver = webDriver;}
    public By getRegistration() {return registration;}
    public By getPasswordRecovery() {return passwordRecovery;}
    public By getEnterInRecoveryForm() {return enterInRecoveryForm;}
    public By getEmailInputEnter() {return emailInputEnter;}
    public By getPasswordInputEnter() {return passwordInputEnter;}
    public By getEnterButton() {return enterButton;}
    public By getPersonalAccount(){return personalAccount;}
    public By getEnterText(){return enterText;}
    public LoginPage openLoginPage() {
        webDriver.get(LOGIN_URL);
        return this;
    }
    public LoginPage click(By element){
        webDriver.findElement(element).click();
        return this;
    }
    public LoginPage fillEnterForm(String email, String password){
        webDriver.findElement(getEmailInputEnter()).sendKeys(email);
        webDriver.findElement(getPasswordInputEnter()).sendKeys(password);
        return this;
    }
}
