package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;
import util.GenUtils;

public class CheckOutPage extends MenusPage {

	public CheckOutPage(MainPageManager pages) {
		super(pages);
	}
	
	@FindBy(xpath = "/html/body/div[6]/div[3]/div/div/div/div[1]/h1")
	private WebElement checkOutTitle;
	
	public CheckOutPage ensurePageLoaded() {
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.visibilityOf(checkOutTitle));
		
		return this;
	}
	
	public CheckOutPage selectCountry() {
		
		log.info("Select Country.");
		Select country = new Select(driver.findElement
				(By.xpath("//select[@id=\"BillingNewAddress_CountryId\"]")));
		country.selectByVisibleText("Israel");
		
		return ensurePageLoaded();
	}
	
	public CheckOutPage fillCityFeild() {
		
		log.info("Type city.");
		driver.findElement(By.id("BillingNewAddress_City")).sendKeys("Jerusalem");
		
		return ensurePageLoaded();
	}
	
	
	public CheckOutPage fillAddressFeild() {
		
		log.info("Type address.");
		driver.findElement(By.id("BillingNewAddress_Address1")).sendKeys("Hadassah");
		
		return ensurePageLoaded();
	}
	
	public CheckOutPage fillZipPostalCodeFeild() {
		
		log.info("Type Zip / postal code.");
		driver.findElement(By.id("BillingNewAddress_ZipPostalCode")).sendKeys("123");
		
		return ensurePageLoaded();
	}
	
	public CheckOutPage fillPhoneNumberFeild() {
		
		log.info("Type Phone number.");
		driver.findElement(By.id("BillingNewAddress_PhoneNumber")).sendKeys("123");
		
		return ensurePageLoaded();
	}
	
	public CheckOutPage clickContinueBillingAddress() {
		
		log.info("click Continue");
		driver.findElement(By.xpath("//div[@id=\"checkout-step-billing\"]" +
				"//button[@class=\"button-1 new-address-next-step-button\"]")).click();
		
		return ensurePageLoaded();
	}
	
	public CheckOutPage clickContinueShippingMethod() {
		
		GenUtils.sleepSeconds(2);
		log.info("Fill Shipping method.");
		log.info("click Continue.");
		driver.findElement(By.xpath("//div[@id=\"shipping-method-buttons-container\"]" +
				"//button[@class=\"button-1 shipping-method-next-step-button\" ]")).click();
		
		return ensurePageLoaded();
	}
	
	
	public CheckOutPage clickContinuePaymentMethod() {
		
		GenUtils.sleepSeconds(2);
		log.info("click Continue.");
		driver.findElement(By.xpath("//div[@id=\"payment-method-buttons-container\"]" +
				"//button[@ class=\"button-1 payment-method-next-step-button\" ]")).click();
		
		return ensurePageLoaded();
	}
	
	public CheckOutPage clickContinuePaymentInformation() {
		
		GenUtils.sleepSeconds(2);
		log.info("click Continue");
		driver.findElement(By.xpath("//div[@id=\"payment-info-buttons-container\"]" +
				"//button[@class=\"button-1 payment-info-next-step-button\"]")).click();
		
		return ensurePageLoaded();
	}

	public OrderSuccessPage clickConfirm() {

		GenUtils.sleepSeconds(2);
		log.info("Click confirm\n");
		driver.findElement(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/button")).click();

		return pages.orderSuccessPage.ensurePageLoaded();
	}
}
