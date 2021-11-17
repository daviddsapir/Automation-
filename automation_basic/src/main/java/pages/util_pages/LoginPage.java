package pages.util_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages._pages_mngt.MainPageManager;
import pages.super_pages.MenusPage;
import pages.util_pages.HomePage;

public class LoginPage extends MenusPage {

	public LoginPage(MainPageManager pages) {
		super(pages);
	}

	public LoginPage ensurePageLoaded() {
		super.ensurePageLoaded();
		waitBig.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".login-button"))));
		return this;
	}
	
	public LoginPage setEmail(String email) {
		log.info("Type register email.");
		driver.findElement(By.id("Email")).click();
		driver.findElement(By.id("Email")).sendKeys(email);
		return pages.loginPage.ensurePageLoaded();
	}
	
	public LoginPage setPassword(String password) {
		log.info("Type register password.");
		driver.findElement(By.id("Password")).click();
		driver.findElement(By.id("Password")).sendKeys(password);
		return pages.loginPage.ensurePageLoaded();
	}
	
	public LoginPage checkRememberMe() {
		log.info("Click remember me button.");
		driver.findElement(By.xpath("//input[@id=\"RememberMe\"]")).click();
		return pages.loginPage.ensurePageLoaded();
	}

	public HomePage registerClickLogin() {
		log.info("Click Log In button.");
		driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/div/"
				+ "div/div[2]/div[1]/div[2]/form/div[3]/button")).click();
		return pages.homePage.ensurePageLoaded();
	}
	
}
