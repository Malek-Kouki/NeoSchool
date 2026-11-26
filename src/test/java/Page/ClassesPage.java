package Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClassesPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public ClassesPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean isOnClassesPage() {
		String loginUrl = driver.getCurrentUrl();
		return loginUrl.contains("academic-classes");
	}
	
	public final By newClassesBoutton = By.xpath("//span[normalize-space()='New Classes']");
	private final By paginationOverview = By.className("fi-pagination-overview");
	private final By emptyStateMessage = By.className("fi-ta-empty-state-content");
	
	public void clickToNewClasses() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(newClassesBoutton)).click();
	}
	
	public int getTotalResultsCount() {
		// 1. Si l'état vide "No Levels" est affiché, le total est 0
	    if (!driver.findElements(emptyStateMessage).isEmpty()
	            && driver.findElements(emptyStateMessage).get(0).isDisplayed()) {
	        return 0;
	    }

	    // 2. Sinon, on lit le compteur de pagination Filament
	    List<WebElement> paginationElements = driver.findElements(paginationOverview);
	    if (paginationElements.isEmpty()) {
	        return 0;
	    }

	    String text = paginationElements.get(0).getText().trim();
	    java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("(\\d+)").matcher(text);
	    String lastNumber = null;
	    while (matcher.find()) {
	        lastNumber = matcher.group(1);
	    }

	    if (lastNumber != null) {
	        return Integer.parseInt(lastNumber);
	    }
	    throw new IllegalStateException("Aucun nombre trouvé dans : '" + text + "'");
	}
	
	public void waitForPaginationOverview() {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(paginationOverview));
	}
}
