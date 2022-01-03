package Pages;

import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BasePage {

    public void deleteAccount() {
        // click account deletion button
        $(By.xpath("//button[text()='Delete Account']"))
                .scrollIntoView(true)
                .click();

        // confirm delete modal
        $("#closeSmallModal-ok").click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // validate popup with text 'User Deleted.'
        var alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"User Deleted.");

        // confirm alert box
        alert.accept();
    }
}
