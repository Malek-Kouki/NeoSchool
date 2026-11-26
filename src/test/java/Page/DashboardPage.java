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
	private final By termsBoutton = By.xpath("//span[normalize-space()='Terms']");
	private final By studentsBoutton = By.xpath("//span[normalize-space()='Students']");
	private final By teachersBoutton = By.xpath("//span[normalize-space()='Teachers']");
	private final By levelsBoutton = By.xpath("//span[normalize-space()='Levels']");
	private final By classesBoutton = By.xpath("//span[normalize-space()='Classes']");
		
	public boolean titleDashboardPageIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(titleDashboardPage)).isDisplayed();
	}
	
	public void clickToAcademicYearsMenu() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(academicYearsMenu)).click();
	}
	
	public void clickToTerms() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(termsBoutton)).click();
	}
	
	public void clickToStudents() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(studentsBoutton)).click();
	}
	
	public void clickToTeachers() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(teachersBoutton)).click();
	}
	
	public void clickToLevels() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(levelsBoutton)).click();
	}
	
	public void clickToClasses() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(classesBoutton)).click();
	}
}
