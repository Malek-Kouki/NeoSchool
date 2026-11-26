package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TeachersPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public TeachersPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean isOnTeachersPage() {
		String loginUrl = driver.getCurrentUrl();
		return loginUrl.contains("teachers");
	}
	
	public final By newTeachersBoutton = By.xpath("//span[normalize-space()='New Teachers']");
	private final By messageCreated = By.xpath("//h3[normalize-space()='Created']");
	public final By firstViewTeacherButton = By.xpath("(//span[contains(text(),'View')])[1]");
	
	public void clickToNewTeachers() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(newTeachersBoutton)).click();
	}
	
	public String getConfirmMessage() {	    
	    WebElement notif = wait.until(ExpectedConditions.visibilityOfElementLocated(messageCreated));
	    return notif.getText().trim();
	}
	
	public void clickViewOnFirstTeacher() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstViewTeacherButton)).click();
	}
	
	public String getEmployeeNumberOnDetailsPage(String expectedEmployeeNumber) {
	    By badgeLocator = By.xpath("//span[normalize-space()='" + expectedEmployeeNumber + "']");
	    WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(badgeLocator));
	    return badge.getText().trim();
	}
}
