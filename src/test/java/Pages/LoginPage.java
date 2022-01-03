package Pages;

import API.AccountCaller;
import Data.Account;
import Data.Container;
import com.codeborne.selenide.Condition;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {

    public void login() {
        // get account object from the container
        var account = (Account)Container.get(Account.class.getName());

        // fill out the login form
        $("#userName").sendKeys(account.username);
        $("#password").sendKeys(account.password);

        // submit login form
        $("#login").click();

        // validate output text and API if account is deleted
        if($("#name").exists()) {
            $("#name").shouldHave(Condition.text("Invalid username or password!"));

            var caller = new AccountCaller();

            var response = caller.login();

            // Assert
            Assert.assertEquals(response.message,"User not found!");
        }
    }
}
