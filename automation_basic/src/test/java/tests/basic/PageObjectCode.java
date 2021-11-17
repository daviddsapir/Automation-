package tests.basic;

import org.openqa.selenium.By;
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
	
		// Login.
		login();
		
		// Select product.
		addBookToShoppingCart();
		
		// Check out.
		checkOut();

		// order success page
		OrderSuccessPage();

		checkHomePageAndShoppingCart();

	}
	
	private void login() {
		// login
		app.pages()
				.menusPage
				.clickLoginLink()
				.setEmail(email)
				.setPassword(password)
				.checkRememberMe()
				.clickLoginButton();
	}
	
	private void addBookToShoppingCart() {

		// get the price of the second product.
		String price = app.pages().menusPage
				.clickBooks()
				.getPrice();

		// add to cart the second product.
		String productAmount = app.pages()
								.itemsListPage
								.selectSecondProduct()
								.getProductAmount();

		// verify data.
		app.pages()
				.itemsListPage
				.verifyOnceProductInShoppingCart(productAmount)
				.ClickShoppingCardButton()
				.verifyProductPriceInShoppingCart(price)
				.checkAgreeWithTheTermsOfServiceCheckbox()
				.clickCheckOutButton();
		
	}
	
	private void checkOut() {
		
		app.pages()
				.checkOutPage
				.selectCountry()
				.fillCityFeild()
				.fillAddressFeild()
				.fillZipPostalCodeFeild()
				.fillPhoneNumberFeild()
				.clickContinueBillingAddress()
				.clickContinueShippingMethod()
				.clickContinuePaymentMethod()
				.clickContinuePaymentInformation()
				.clickConfirm();

	}

	private void OrderSuccessPage() {
		app.pages().
				orderSuccessPage
				.checkIfThankYouAppears()
				.clickContinueToCompleteOrder();
	}

	public void checkHomePageAndShoppingCart() {
		app.pages().
				homePage
				.checkIfWelcomeToOurStoreAppears()
				.checkIfShoppingCartIsEmpty();
	}
}
