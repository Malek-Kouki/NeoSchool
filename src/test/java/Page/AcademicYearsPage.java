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
	private final By setCurrentBoutton = By.xpath("((//tr[contains(., '2026/2027') and .//*[normalize-space()='Set as Current']])[1]//*[normalize-space()='Set as Current'])[1]");
	private final By currentAcademicModal = By.xpath("//div[contains(@class,'fi-modal-window') and not(contains(@style,'display: none'))]");
	private final By confirmSetCurrentButton = By.xpath("(//button[@type='submit'])[2]");
	public final By confirmTitleMessage = By.xpath("//h3[normalize-space()='Academic Year Switched Successfully']");
	public final By confirmTextMessage = By.className("fi-no-notification-body");
	public final By banniereVerte = By.xpath("(//div[contains(@class,'bg-green-400')])[2]");
	
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
	
	public void clickToCurrentButton() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(setCurrentBoutton)).click();
	}
	
	public boolean isSetAsCurrentModalDisplayed(String expectedTitle, String expectedMessage) {
	    WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(currentAcademicModal));
	    String fullText = modal.getText();
	    return fullText.contains(expectedTitle) && fullText.contains(expectedMessage);
	}
	
	public void clickConfirmSetCurrent() {
	    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmSetCurrentButton));
	    confirmBtn.click();
	}
	
	public boolean confirmTitleMessageIsDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmTitleMessage)).isDisplayed();
	}
	
	public boolean confirmTextMessageIsDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmTextMessage)).isDisplayed();
	}
	
	public boolean isBanniereVerteEnHautEtValide(String expectedMessage) {
		WebElement banner = wait.until(ExpectedConditions.visibilityOfElementLocated(banniereVerte));
		boolean isDisplayed = banner.isDisplayed();
		int yPosition = banner.getRect().getY();
	    boolean isAtTop = yPosition >= 0 && yPosition <= 250;
		
		String actualText = banner.getText().replaceAll("\\s+", " ").trim();
		String expectedText = expectedMessage.replaceAll("\\s+", " ").trim();
		
		System.out.println("Actual text: [" + actualText + "]");
	    System.out.println("Expected text: [" + expectedText + "]");
	    System.out.println("Banner Y position: " + yPosition);
	    
		boolean textMatches = actualText.equalsIgnoreCase(expectedText) || actualText.contains(expectedText);
		return isDisplayed && isAtTop && textMatches;
	}
}
