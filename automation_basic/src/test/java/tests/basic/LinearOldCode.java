package tests.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import tests.supers.TestBase;
import util.GenUtils;

public class LinearOldCode extends TestBase {

	String timestamp, email, password;

	@Test
	public void test() {
		try {

			initParameter();
			app.getDriver().get("https://demo.nopcommerce.com/");

			registerNewUser();
			shoppingProcessEndToEnd();

			endTestSuccess();
		} catch (Throwable t) {
			onTestFailure(t);
		}
	}

	private void initParameter() {
		timestamp = GenUtils.getCurrentTimestamp(GenUtils.TIME_FORMAT_ddMMyyHHmmSSS);
		email = "randomEmail" + timestamp + "@gmail.com";
		password = "123456";
	}

	private void registerNewUser() {

		WebDriver driver = app.getDriver();

		log.info("Click Register");
		driver.findElement(By.linkText("Register")).click();

		log.info("Select male");
		driver.findElement(By.id("gender-male")).click();

		log.info("Enter first name");
		driver.findElement(By.id("FirstName")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Name1");

		log.info("Enter last name");
		driver.findElement(By.id("LastName")).click();
		driver.findElement(By.id("LastName")).sendKeys("Name2");

		log.info("Select Birthday");
		Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		day.selectByVisibleText("1");

		Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		month.selectByVisibleText("January");

		Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
		year.selectByVisibleText("1920");

		log.info("Type randomized email");

		log.info("Randomized email is: " + email);
		driver.findElement(By.id("Email")).click();
		driver.findElement(By.id("Email")).sendKeys(email);

		log.info("Type password");
		driver.findElement(By.id("Password")).click();
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).click();
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		log.info("Click register button");
		driver.findElement(By.id("register-button")).click();

		String actualTextRegistration = driver.findElement(By.cssSelector(".result")).getText();
		String expectedTextRegistration = "Your registration completed";
		Assert.assertTrue(actualTextRegistration.equals(expectedTextRegistration),
				"Expected value: '" + expectedTextRegistration + "'," +
						" but actual is '" + actualTextRegistration + "'");

		driver.findElement(By.cssSelector(".register-continue-button")).click();
		String firstScreenText = driver.findElement(By.cssSelector(".topic-block-title > h2")).getText();
		String expectedTextFirstScreen = "Welcome to our store";
		Assert.assertTrue(firstScreenText.equals(expectedTextFirstScreen),
				"Expected value: '" + expectedTextFirstScreen +
				"', but actual is '" + actualTextRegistration + "'");

		log.info("Click logout button");
		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();

	}

	private void shoppingProcessEndToEnd() {

		WebDriver driver = app.getDriver();

		log.info("Click login button.");
		driver.findElement(By.linkText("Log in")).click();

		log.info("Type register email.");
		driver.findElement(By.id("Email")).click();
		driver.findElement(By.id("Email")).sendKeys(email);

		log.info("Type register password.");
		driver.findElement(By.id("Password")).click();
		driver.findElement(By.id("Password")).sendKeys(password);

		log.info("Click Log Remember Me button.");
		driver.findElement(By.xpath("//input[@id=\"RememberMe\"]")).click();

		log.info("Click Log In button.");
		driver.findElement(By.cssSelector(".buttons button.register-button")).click();

		log.info("Click Books button.");
		driver.findElement(By.linkText("Books")).click();

		// Select the second book.
		log.info("Click Add to cart.\n");
		String  productPriceFirstCheck = driver.findElement(By.xpath("//div[@data-productid=\"38\"]" +
				"//span[@class=\"price actual-price\"]")).getText();
		driver.findElement(By.xpath("//div[@data-productid=\"38\"]//button" +
				"[@class=\"button-2 product-box-add-to-cart-button\"]")).click();

		log.info("Verify that (1) appears in shopping card (sleep a bit till green panel).");
		GenUtils.sleepSeconds(6);
		String amount = driver.findElement(By.xpath("//li[@id=\"topcartlink\"]//span[@class=\"cart-qty\"]")).getText();

		log.info("Checking amount correctness:");
		Assert.assertTrue(amount.equals("(1)"),
				"Expected value: '" + "(1)" + "', but actual is '" + amount + "'");

		log.info("Click Shopping card button");
		driver.findElement(By.xpath("//li[@id=\"topcartlink\"]")).click();

		log.info("Check that price is right (the one you we saved in step 3.");
		String productPriceSecondCheck = driver.findElement(By.xpath("//div[@class=\"totals\"]//td[@class=\"cart-total-right\"]//strong")).getText();
		Assert.assertTrue(productPriceFirstCheck.equals(productPriceSecondCheck),
				"Expected value: '" + productPriceFirstCheck +
				"', but actual is '" + productPriceSecondCheck + "'");

		log.info("Sign V in \"I agree with the terms of service\" checkbox.");
		driver.findElement(By.xpath("//input[@id=\"termsofservice\"]")).click();

		log.info("Click Check Out ” button.\n");
		driver.findElement(By.xpath("//button[@id=\"checkout\"]")).click();

		
		log.info("Select Country.");
		Select country = new Select(driver.findElement(By.xpath("//select[@id=\"BillingNewAddress_CountryId\"]")));
		country.selectByVisibleText("Israel");

		log.info("Type city.");
		driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Jerusalem");

		log.info("Type address.");
		driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Hadassah");

		log.info("Type Zip / postal code.");
		driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("123");

		log.info("Type Phone number.");
		driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("123");

		log.info("click Continue");
		driver.findElement(By.xpath("//div[@id=\"checkout-step-billing\"]" +
				"//button[@class=\"button-1 new-address-next-step-button\"]")).click();


		GenUtils.sleepSeconds(2);
		log.info("Fill Shipping .");
		log.info("click Continue.");
		driver.findElement(By.xpath("//div[@id=\"shipping-method-buttons-container\"]" +
				"//button[@class=\"button-1 shipping-method-next-step-button\" ]")).click();


		GenUtils.sleepSeconds(2);
		log.info("Fill Payment method.");
		log.info("click Continue.");
		driver.findElement(By.xpath("//div[@id=\"payment-method-buttons-container\"]" +
				"//button[@ class=\"button-1 payment-method-next-step-button\" ]")).click();


		GenUtils.sleepSeconds(2);
		log.info("Fill Payment information.");
		log.info("click Continue");
		driver.findElement(By.xpath("//div[@id=\"payment-info-buttons-container\"]" +
				"//button[@class=\"button-1 payment-info-next-step-button\"]")).click();


		GenUtils.sleepSeconds(2);
		log.info("Fill Confirm order.");
		log.info("Click confirm\n");
		driver.findElement(By.xpath("//div[@id=\"confirm-order-buttons-container\"]" +
				"//button[@class=\"button-1 confirm-order-next-step-button\"]")).click();


		GenUtils.sleepSeconds(2);
		log.info("Check if Thank you appears.");
		String thankYou = driver.findElement(By.xpath("//div[@class=\"page" +
				" checkout-page order-completed-page\"]//h1")).getText();
		Assert.assertTrue(thankYou.equals("Thank you"),
				"Expected value: '" + "Thank you" + "', but actual is '" + thankYou + "'");

		log.info("Check if Thank you appears.");
		String orderSuccessfullyProcessed  = driver.findElement(By.xpath("//div[@class=\"section order-completed\"]" +
				"//div[@class=\"title\"]//strong")).getText();
		Assert.assertTrue(orderSuccessfullyProcessed.equals("Your order has been successfully processed!"),
				"Expected value: '" + "Your order has been successfully processed!" + "'," +
						" but actual is '" + orderSuccessfullyProcessed + "'");


		log.info("Click Continue to complete order.");
		driver.findElement(By.xpath("//button[@class=\"button-1 order-completed-continue-button\"]")).click();


		log.info("Check if We \"Welcome to our store\" appears.");
		String welcomeToOurStore = driver.findElement(By.xpath("//div[@class=\"topic-block-title\"]/h2")).getText();
		Assert.assertTrue(welcomeToOurStore.equals("Welcome to our store"),
				"Expected value: '" + "Welcome to our store" + "', but actual is '" + welcomeToOurStore + "'");


		log.info("Verify (0) in the shopping cart.");
		String verifyZero = driver.findElement(By.xpath("//li[@id=\"topcartlink\"]" +
				"//span[@class=\"cart-qty\"]")).getText();
		Assert.assertTrue(verifyZero.equals("(0)"),
				"Expected value: '" + "(0)" + "', but actual is '" + verifyZero + "'");


		log.info(""); // to add some spacing before the final log.

		GenUtils.sleepSeconds(2);

	}
}