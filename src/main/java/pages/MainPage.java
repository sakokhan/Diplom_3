package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class MainPage {
    private WebDriver webDriver;
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    //кнопка "Личный кабинет"
    private final By personalAccount = By.xpath(".//*[text()='Личный Кабинет']");
    //кнопка "Личный кабинет" в профиле
    private final By personalAccountInProfile = By.xpath("//*[@id=\"root\"]/div/header/nav/a/p");
    // кнопка "войти в аккаунт"
    private final By logInButton = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button");
    // раздел булки в конструкторе
    private final By bunSection = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]");
    // раздел соусы в конструкторе
    private final By sousesSection = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");
    // раздел начинки в конструкторе
    private final By fillingsSection = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div[2]");
    // булка Флюоресцентная булка R2-D3
    private final By fluoriscentBun = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/ul[1]/a[1]/img");
    // соус "Соус Spicy-X"
    private final By spicyXSouse = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/ul[2]/a[1]/img");
    // начинка говяжий меьеорит
    private final By beefPatty= By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/ul[3]/a[2]/img");
    public MainPage(WebDriver webDriver){this.webDriver = webDriver;}
    public By getPersonalAccount() {return personalAccount;}
    public By getLogInButton() {return logInButton;}
    public By getPersonalAccountInProfile() {return personalAccountInProfile;}
    public By getBunSection() {return bunSection;}
    public By getSousesSection() {return sousesSection;}
    public By getFillingsSection() {return fillingsSection;}
    public By getSpicyXSouse() {return spicyXSouse;}
    public By getBeefPatty() {return beefPatty;}
    public By getFluoriscentBun() {return fluoriscentBun;}
    public MainPage openMainPage(){
        webDriver.get(BASE_URL);
        return this;
    }
    public MainPage click(By element){
        webDriver.findElement(element).click();
        return this;
    }
}
