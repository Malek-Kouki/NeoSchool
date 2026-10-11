package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlerteWindowPage {
	private WebDriver driver;
	private WebDriverWait wait;

	public AlerteWindowPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	private final By AlerteMenu = By.xpath("//span[normalize-space()='Alerts']");

	public void clickOnAlertMenu() {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
		wait.until(ExpectedConditions.elementToBeClickable(AlerteMenu)).click();
	}

}
