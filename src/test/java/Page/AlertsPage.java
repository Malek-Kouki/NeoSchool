package Page;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsPage {
	
		private WebDriver driver;
		private WebDriverWait wait;

		public AlertsPage (WebDriver driver) {
			this.driver = driver;
			this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		}

		private final By ButtonToSeeAlert = By.xpath("(//button[@class='btn btn-primary'])[1]");
		
		public void clickOnButtonToSeeAlert() {
			((JavascriptExecutor) driver) .executeScript("window.scrollTo(0, 0);");

			wait.until(ExpectedConditions.elementToBeClickable(ButtonToSeeAlert)).click();
		}
		
		public Alert CheckAlert() {
			return wait.until(ExpectedConditions.alertIsPresent());
		}
		
		public void AcceptAlert() {
			driver.switchTo().alert().accept();
		}
		
		public boolean IsAlertPresent() {
			try {
				driver.switchTo().alert();
				return true;
			}
			catch(Exception E) {
				return false;
			}
		}

}
