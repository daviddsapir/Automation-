package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;

public class HomePage extends MenusPage {

	public HomePage(MainPageManager pages) {
		super(pages);
	}

	private final static String HOME_MESSAGE = "Welcome to our store";
	private String welcome=".topic-block-title > h2";

	public HomePage ensurePageLoaded() {
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(welcome))));
		return this;
	}

	public HomePage verifyHomeText() {
		log.info("Verify home text is "+HOME_MESSAGE);
		String actualMessage = driver.findElement(By.cssSelector(welcome)).getText();

		Assert.assertTrue(actualMessage.equals(HOME_MESSAGE),
				"Expected value: '" + HOME_MESSAGE + "', but actual is '" + actualMessage + "'");
		return this;
	}

	public HomePage checkIfWelcomeToOurStoreAppears() {

		log.info("Check if We \"Welcome to our store\" appears.");
		String welcomeToOurStore = driver.findElement(By.xpath("//div[@class=\"topic-block-title\"]/h2")).getText();
		Assert.assertTrue(welcomeToOurStore.equals("Welcome to our store"),
				"Expected value: '" + "Welcome to our store" + "', but actual is '" + welcomeToOurStore + "'");

		return ensurePageLoaded();
	}

	public HomePage checkIfShoppingCartIsEmpty() {

		log.info("Verify (0) in the shopping cart.");
		String verifyZero = driver.findElement(By.xpath("//li[@id=\"topcartlink\"]" +
				"//span[@class=\"cart-qty\"]")).getText();
		Assert.assertTrue(verifyZero.equals("(0)"),
				"Expected value: '" + "(0)" + "', but actual is '" + verifyZero + "'");

		return ensurePageLoaded();
	}
}
