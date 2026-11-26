package Page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LevelsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public LevelsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public boolean isOnLevelsPage() {
		String loginUrl = driver.getCurrentUrl();
		return loginUrl.contains("levels");
	}
	
	public final By newLevelsBoutton = By.xpath("//span[normalize-space()='New Levels']");
	private final By createdLevel = By.xpath("//h3[normalize-space()='Created']");
	private final By paginationOverview = By.className("fi-pagination-overview");
	private final By emptyStateMessage = By.className("fi-ta-empty-state-content");
	
	public void clickToNewLevels() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(newLevelsBoutton)).click();
	}
	
	public String getConfirmLevel() {	    
	    WebElement notif = wait.until(ExpectedConditions.visibilityOfElementLocated(createdLevel));
	    return notif.getText().trim();
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
