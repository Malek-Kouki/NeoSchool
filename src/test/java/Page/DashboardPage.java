package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	private final By titleDashboardPage = By.className("fi-header-heading");
	private final By academicYearsMenu = By.xpath("//span[normalize-space()='Academic Years']");
		
	public boolean titleDashboardPageIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(titleDashboardPage)).isDisplayed();
	}
	
	public void clickToAcademicYearsMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(academicYearsMenu)).click();
	}
}
