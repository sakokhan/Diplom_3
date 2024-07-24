package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver webDriver;
    public static final String RECOVERY_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    public ForgotPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    // кнопка войти
    private final By logInButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
    public By getLogInButton() {return logInButton;}
    public ForgotPasswordPage openForgotPasswordPage() {
        webDriver.get(RECOVERY_URL);
        return this;
    }
    public ForgotPasswordPage click(By element){
        webDriver.findElement(element).click();
        return this;
    }
}
