package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;

public class ItemsListPage extends MenusPage {

    private String price;

    public ItemsListPage(MainPageManager pages) { super(pages); }

    public ItemsListPage ensurePageLoaded() {
        super.ensurePageLoaded();
        waitBig.until(ExpectedConditions.visibilityOf(driver.
                findElement(By.xpath("//div[@class=\"page-title\"]"))));
        return this;
    }

    public ItemsListPage selectSecondProduct() {
        // Select the second book.
        log.info("Click Add to cart.\n");
       price = driver.findElement(By.xpath("//div[@data-productid=\"38\"]" +
                "//span[@class=\"price actual-price\"]")).getText();
        driver.findElement(By.xpath("//div[@data-productid=\"38\"]//button" +
                "[@class=\"button-2 product-box-add-to-cart-button\"]")).click();
        return ensurePageLoaded();
    }

}
