package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsWindowsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public AlertsWindowsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	private final By menuAlerte = By.xpath("//a[normalize-space()='Alerts']");
	
	public void clickToMenuAlerte() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(menuAlerte)).click();
	}
}
