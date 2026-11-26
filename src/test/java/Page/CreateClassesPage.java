package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateClassesPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public CreateClassesPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public final By breadcrumbsCreateClasses = By.className("fi-breadcrumbs-list");
	private final By levelsField = By.className("fi-select-input");
	private final By nameClasses = By.xpath("//input[@id='data.name']");
	public final By createClassesBoutton = By.xpath("//button[@id='key-bindings-1']");
	private final By notifCreated = By.xpath("//h3[normalize-space()='Created']");
	private static int classIndex = 0;
	
	public boolean breadcrumbsCreateClassesIsDisplay() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateClasses)).isDisplayed();
	}

	public boolean breadcrumbsContainsTextClasses(String expectedTextClasses) {
	    WebElement breadcrumbElementClasses = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateClasses)
	    );
	    String actualTextClasses = breadcrumbElementClasses.getText();
	    return actualTextClasses.contains(expectedTextClasses);
	}
	
	public void clickLevelsField() {
	    WebElement field = wait.until(ExpectedConditions.elementToBeClickable(levelsField));
	    field.click();
	}
	
	public boolean isLevelPresentInDropdown(String expectedLevel) {
	    WebElement selectElementLevel = wait.until(ExpectedConditions.visibilityOfElementLocated(levelsField));
	    org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(selectElementLevel);

	    // Récupère toutes les balises option
	    for (WebElement option : select.getOptions()) {
	    	// lit le texte visible et vérifie si ce texte correspond au niveau attendu
	        if (option.getText().trim().equals(expectedLevel.trim())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public void selectLevelInDropdown(String levelName) {
	    WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(levelsField));
	    org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(selectElement);
	    select.selectByVisibleText(levelName.trim());
	}
	
	public boolean classesNameIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(nameClasses)).isDisplayed();
	}
	
	public void sendClassesName(String classesName) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(nameClasses)).sendKeys(classesName);
	}
	
	public String generateNextClassName() {
	    char letter = (char) ('A' + (classIndex % 26));
	    classIndex++;
	    return "Classes " + letter + " " + (System.currentTimeMillis() % 1000);
	}
	
	public void clickToCreateClasses() {
	    WebElement createBoutton = wait.until(ExpectedConditions.elementToBeClickable(createClassesBoutton));
	    createBoutton.click();
	}
	
	public String getConfirmNotification() {	    
	    WebElement notif = wait.until(ExpectedConditions.visibilityOfElementLocated(notifCreated));
	    return notif.getText().trim();
	}
	
}
