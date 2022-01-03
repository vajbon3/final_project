import API.AccountCaller;
import API.BookStoreCaller;
import Data.Account;
import Data.Container;
import Pages.BookPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class Tests {

    @Test()
    public void task1() {
            // preparation

        // account object
        var account = new Account("Vaja","Chelidze",
                "vajbon3","Password123-$");
        // put into the container
        Container.put(account);

        // account API caller object
        var accountCaller = new AccountCaller();
        // put into the container
        Container.put(accountCaller);


            // task

        // register the account
        accountCaller.register();

        // login page
        open("https://demoqa.com/login");
        var loginPage = new LoginPage();
        loginPage.login();

        // home page
        var homePage = new HomePage();
        homePage.deleteAccount();

        // try to log in again
        loginPage.login();

    }

    @Test()
    public void task2() {
            // preparation
        // inject BookStoreCaller object into the app container
        Container.put(new BookStoreCaller());

            // task
        open("https://demoqa.com/books");

        var bookPage = new BookPage();
        bookPage.validateBooks();
    }
}
