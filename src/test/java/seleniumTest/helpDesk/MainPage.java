package seleniumTest.helpDesk;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import seleniumTest.core.BaseSeleniumPage;

public class MainPage extends BaseSeleniumPage {
//    private final By queueList = By.id("userName");
//    private final By queueList2 = By.xpath("//select[@id='userName']");
//
//    private WebElement queueListElement = driver.findElement(queueList);

    @FindBy(xpath = "//select[@id='userName']")
    private WebElement queueList3;
}
