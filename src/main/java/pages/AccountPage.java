package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
    private WebDriver webDriver;
    public static final String ACCOUNT_URL = "https://stellarburgers.nomoreparties.site/account";
    // кнопка конструктор
    private final By constructorButton = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p");
    // кнопка логотип Stellar Burgers
    private final By logoButton = By.xpath("//*[@id=\"root\"]/div/header/nav/div/a");

    // кнопка выхода из аккаунта
    private final By exitButton = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button");
    public AccountPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    public By getConstructorButton() { return constructorButton; }
    public By getExitButton() {return exitButton;}
    public AccountPage click(By element){
        webDriver.findElement(element).click();
        return this;
    }
}
