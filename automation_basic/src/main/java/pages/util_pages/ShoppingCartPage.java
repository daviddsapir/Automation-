package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;
import util.GenUtils;

public class ShoppingCartPage extends MenusPage {
	
	public ShoppingCartPage(MainPageManager pages) {
		super(pages);
	}
	
	
	@FindBy(linkText = "Shopping cart")
	private WebElement shoppingCartTitle;

	public ShoppingCartPage ensurePageLoaded() {
		
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.visibilityOf(shoppingCartTitle));
		
		return this;
	}
	
	public ShoppingCartPage verifyProductPriceInShoppingCart(String price) {
		
		log.info("Validate price");
		String productPriceSecondCheck = driver.findElement(By.xpath("//*[@id=\"shopping-cart-form\"]"
				+ "/div[3]/div[2]/div[1]/table/tbody/tr[4]/td[2]/span/strong")).getText();

		// check price correctness
		Assert.assertTrue(price.equals(productPriceSecondCheck),
				"Expected value: '" + price +
				"', but actual is '" + productPriceSecondCheck + "'");
		
		return ensurePageLoaded();
	}
	
	public ShoppingCartPage checkAgreeWithTheTermsOfServiceCheckbox() {
		
		log.info("Sign V in \"I agree with the terms of service\" checkbox.");
		driver.findElement(By.xpath("//input[@id=\"termsofservice\"]")).click();
		
		return ensurePageLoaded();
	}
	
	public CheckOutPage clickCheckOutButton() {
		
		log.info("Click Check Out button.");
		driver.findElement(By.xpath("//button[@id=\"checkout\"]")).click();
		
		return pages.checkOutPage.ensurePageLoaded();
	}
}
