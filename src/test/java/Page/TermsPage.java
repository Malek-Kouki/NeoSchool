package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TermsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public TermsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean isOnTermsPage() {
		String loginUrl = driver.getCurrentUrl();
		return loginUrl.contains("terms");
	}
	
	public final By newtermsBoutton = By.xpath("//span[normalize-space()='New Terms']");
	private final By createdMessage = By.xpath("//h3[normalize-space()='Created']");

	
	public void clickToNewTerms() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(newtermsBoutton)).click();
	}
	
	public String getCreatedNotificationText() {	    
	    WebElement notif = wait.until(ExpectedConditions.visibilityOfElementLocated(createdMessage));
	    return notif.getText().trim();
	}
	
	
	public boolean isTermDisplayedInTable(String name, String startDate, String endDate) {
	    // XPath dynamique pour cibler la ligne du tableau contenant le nom, la date de début et la date de fin
	    String xpathRow = "//tr[td[normalize-space()='" + name + "'] and td[normalize-space()='" + startDate + "'] and td[normalize-space()='" + endDate + "']]";
	    try {
	        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathRow))).isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
}
