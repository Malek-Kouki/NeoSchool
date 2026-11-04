package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AcademicYearsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public AcademicYearsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	private final By newAcademicYearsBoutton = By.xpath("//span[normalize-space()='New Academic Years']");
	private final By popupMessage = By.cssSelector(".flex.w-full.gap-3.p-4");
	private int initialCount;
	
	public boolean isOnAcademicYearsPage() {
		String loginUrl = driver.getCurrentUrl();
		return loginUrl.contains("academic-years");
	}
	
	public int getTotalResultsCount() {
		WebElement resultsInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("fi-pagination-overview")));
		String text = resultsInfo.getText();
		
		//Extraire le nombre apres of
		String totalNumber = text.replaceAll(".*of\\s+(\\d+)\\s+results.*", "$1");
		return Integer.parseInt(totalNumber);
	}
	
	// Sauvegarder le nombre avant l'action
    public void saveInitialCount() {
        this.initialCount = getTotalResultsCount();
    }

    // Vérifier que le nombre a augmenté de 1 après l'action
    public boolean verifyCountHasIncreased() {
        int finalCount = getTotalResultsCount();
        return finalCount == (this.initialCount + 1);
    }
	
	public void clickToNewAcademicYearsBoutton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(newAcademicYearsBoutton)).click();
	}
	
	public boolean popupMessageIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(popupMessage)).isDisplayed();
	}
	
	public String getPopupMessageText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(popupMessage)).getText();
	}
	
	public boolean isNewAcademicYearAdded(String expectedYear) {
		WebElement firstRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]")));
		return firstRow.getText().contains(expectedYear);
	}
}
