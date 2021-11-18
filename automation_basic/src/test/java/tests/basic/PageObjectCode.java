package tests.basic;

import org.testng.annotations.Test;

import tests.supers.TestBase;
import util.GenUtils;

public class PageObjectCode extends TestBase {

	String timestamp, email, password, price;

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
        price = "";
	}

	
	private void registerNewUser() {

		app.pages().menusPage.clickRegister().chooseGender("male")
				.setFirstName("Name1").setLastName("Name2")
				.selectBirthdayDay("1").selectBirthdayMonth("January").selectBirthdayYear("2000")
				.setMail(email)
				.setPassword(password).setPasswordVerification(password)
				.clickRegisterButton()
				.verifySuccessMessage().clickContinue().verifyHomeText().logout();
	}

	private void shoppingProcessEndToEnd() {

		login();

		addBookToShoppingCart();

		GenUtils.sleepSeconds(2);	// just to take a glance at homepage.

	}

	private void login() {

		app.pages()
				.menusPage
				.clickLoginLink()
				.setEmail(email)
				.setPassword(password)
				.checkRememberMe()
				.clickLoginButton();
	}

	private void addBookToShoppingCart() {

		// get price.
		String price = app.pages()
								.menusPage
								.clickBooks()
								.getPrice();


		// Add to cart.
		String productQty = app.pages()
									.itemsListPage
									.selectSecondProduct()
									.getProductAmount();

		app.pages()
				.itemsListPage
				.verifyCart(productQty)
				.clickShoppingCardButton()
				.verifyProductPriceInShoppingCart(price)
				.checkAgreeWithTheTermsOfServiceCheckbox()
				.clickCheckOutButton()
				.selectCountry()
				.fillCityFeild()
				.fillAddressFeild()
				.fillZipPostalCodeFeild()
				.fillPhoneNumberFeild()
				.clickContinueBillingAddress()
				.clickContinueShippingMethod()
				.clickContinuePaymentMethod()
				.clickContinuePaymentInformation()
				.clickConfirm()
				.checkIfThankYouAppears()
				.clickContinueToCompleteOrder()
				.verifyHomeText()
				.checkAmountInCart(0);
	}
}
