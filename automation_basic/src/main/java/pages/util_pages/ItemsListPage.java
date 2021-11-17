package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;
import util.GenUtils;

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
		log.info("Click Add to cart.");
		driver.findElement(By.xpath("//div[@data-productid=\"38\"]//button" +
				"[@class=\"button-2 product-box-add-to-cart-button\"]")).click();
		
		GenUtils.sleepSeconds(2);
		
		return ensurePageLoaded();
	}

	public String getPrice() {
		
		return driver.findElement(By.xpath("//div[@data-productid=\"38\"]" +
				"//span[@class=\"price actual-price\"]")).getText();
	}

	/**
	 *
	 * @return {String}
	 */
	public String getProductAmount() {
		
		return driver.findElement(By.xpath("//li[@id=\"topcartlink\"]//span[@class=\"cart-qty\"]")).getText();
	}


	public ItemsListPage verifyOnceProductInShoppingCart(String amount) {
		log.info("Checking amount correctness:");
		
		GenUtils.sleepSeconds(6);
		
		Assert.assertTrue(amount.equals("(1)"),
				"Expected value: '" + "(1)" + "', but actual is '" + amount + "'");
		
		return ensurePageLoaded();
	}

	public ShoppingCartPage ClickShoppingCardButton() {
		log.info("Click Shopping card button");
		driver.findElement(By.xpath("//li[@id=\"topcartlink\"]")).click();

		return pages.shoppingCartPage.ensurePageLoaded();
	}
	
}
