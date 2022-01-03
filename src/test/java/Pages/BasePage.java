package Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BasePage {
    WebDriver driver;

    public BasePage(){
        driver = getWebDriver();
    }

}
