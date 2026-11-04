package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public SignInPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	// vérifier si l'utilisateur est bien redirigé vers la page Sign in via l'URL
	public boolean isOnLogin() {
		String loginUrl = driver.getCurrentUrl();
		return loginUrl.contains("login");
	}
		
	private final By schoolCode = By.id("data.school_code");
	private final By emailAddress = By.id("data.email");
	private final By password = By.id("data.password");
	private final By bouttonLogin = By.cssSelector("button[type='submit']");
	
	public boolean schoolCodeIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(schoolCode)).isDisplayed();
	}
	
	public boolean emailAddressIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddress)).isDisplayed();
	}
	
	public boolean passwordIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(password)).isDisplayed();
	}
	
	public boolean bouttonLoginIsEnabled() {
		return driver.findElement(bouttonLogin).isEnabled();
	}
	
	public void sendSchoolCode(String schoolCodeValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(schoolCode)).sendKeys(schoolCodeValue);
	}
	
	public void sendEmailAddress(String emailAddressValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddress)).sendKeys(emailAddressValue);
	}
	
	public void sendPassword(String passwordValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(password)).sendKeys(passwordValue);
	}
	
	public void clicToLogin() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouttonLogin)).click();
	}
}
