package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;
import util.GenUtils;

public class OrderSuccessPage extends MenusPage {

    public OrderSuccessPage(MainPageManager pages) {
        super(pages);
    }

    public OrderSuccessPage ensurePageLoaded() {

        super.ensurePageLoaded();
        waitBig.until(ExpectedConditions.visibilityOf(driver.
                findElement(By.xpath("/html/body/div[6]/div[3]/div/div/div/div[1]/h1"))));

        return this;
    }

    public OrderSuccessPage checkIfThankYouAppears() {

        GenUtils.sleepSeconds(2);
        log.info("Check if Thank you appears.");
        String orderSuccessfullyProcessed  = driver.findElement(By.xpath("//div[@class=\"section order-completed\"]" +
                "//div[@class=\"title\"]//strong")).getText();
        Assert.assertTrue(orderSuccessfullyProcessed.equals("Your order has been successfully processed!"),
                "Expected value: '" + "Your order has been successfully processed!" + "'," +
                        " but actual is '" + orderSuccessfullyProcessed + "'");

        return ensurePageLoaded();
    }

    public HomePage clickContinueToCompleteOrder() {

        log.info("Click Continue to complete order.");
        driver.findElement(By.xpath("//button[@class=\"button-1 order-completed-continue-button\"]")).click();

        return pages.homePage.ensurePageLoaded();
    }
}
