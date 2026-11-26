package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public StudentsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean isOnStudentsPage() {
		String loginUrl = driver.getCurrentUrl();
		return loginUrl.contains("students");
	}
	
	public final By newStudentsBoutton = By.xpath("//span[normalize-space()='New Students']");
	private final By confirmMessage = By.xpath("//h3[normalize-space()='Created']");
	public final By firstRowViewButton = By.xpath("(//span[contains(text(),'View')])[1]");
	
	public void clickToNewStudents() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(newStudentsBoutton)).click();
	}
	
	public String getConfirmText() {	    
	    WebElement notif = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmMessage));
	    return notif.getText().trim();
	}
	
	public void clickViewOnFirstStudent() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowViewButton)).click();
	}
	
	public boolean isAdmissionNumberOnViewPage(String admissionNumber) {
		By admissionLocator = By.xpath("//span[contains(normalize-space(.), '" + admissionNumber.trim() + "')]");
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(admissionLocator)).isDisplayed();
	}
	
}
