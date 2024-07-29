package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver webDriver;
    public static final String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";
    //поле ввода имени
    private final By nameInput= By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    //поле ввода email
    private final By emailInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    //поле ввода пароля
    private final By passwordInput = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/div/input");
    // ккнопка "Зарегистрироваться"
    private final By registrationButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    //сообщение об ошибке при некорректном пароле
    private final By error = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[3]/div/p");
    // кнопка "войти" на форме регистрации
    private final By enterText = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public By getNameInput() {return nameInput;}
    public By getEmailInput() {return emailInput;}
    public By getPasswordInput() {return passwordInput;}
    public By getRegistrationButton() {return registrationButton;}
    public By getError() {return error;}
    public By getEnterText() {return enterText;}
    public RegisterPage openRegisterPage() {
        webDriver.get(REGISTER_URL);
        return this;
    }
    public RegisterPage fillRegistrationForm(String name, String email, String password){
        webDriver.findElement(getNameInput()).sendKeys(name);
        webDriver.findElement(getEmailInput()).sendKeys(email);
        webDriver.findElement(getPasswordInput()).sendKeys(password);
        return this; }
    public RegisterPage click(By element){
        webDriver.findElement(element).click();
        return this;
    }
}
