package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateTermsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public CreateTermsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public final By breadcrumbsCreateTerms = By.className("fi-breadcrumbs-list");
	public final By name = By.xpath("//input[@id='data.name']");
	public final By description = By.xpath("//textarea[@id='data.description']");
	//public final By startsAtData = By.xpath("//input[@id='data.starts_at']");
	public final By calendar = By.xpath("(//div[contains(@class,'fi-fo-date-time-picker-panel')])[1]");
	public final By startAtDataButton = By.xpath("//input[@id='data.starts_at']/ancestor::button[1]");
	public final By monthSelectStart = By.xpath("(//select[@x-model='focusedMonth'])[1]");
	public final By yearSelectStart = By.xpath("(//input[@type='number'])[1]");
	public final By endsAtInput = By.xpath("//input[@id='data.ends_at']");
	public final By createBoutton = By.xpath("//button[@id='key-bindings-1']");
	public final By sectionStatus = By.xpath("//section[@id='data.sectionsstatus']");
	
	public By dayOptionStart(String day) {
	    return By.xpath("(//div[@role='grid']//div[@role='option' and normalize-space(.)='" + day + "'])[1]");
	}
	
	public boolean breadcrumbsCreateTermsIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateTerms)).isDisplayed();
	}
	
	public boolean breadcrumbsContainsTextTerms(String expectedText) {
	    WebElement breadcrumbElement = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateTerms)
	    );
	    String actualText = breadcrumbElement.getText();
	    return actualText.contains(expectedText);
	}
	
	public boolean nameIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(name)).isDisplayed();
	}
	
	public void sendName(String nameValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(name)).sendKeys(nameValue);
	}
	
	public boolean descriptionIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(description)).isDisplayed();
	}
	
	public void sendDescription(String descriptionValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(description)).sendKeys(descriptionValue);
	}
	
	public void fixDatePickerOverflow() {
	    String js = "document.querySelectorAll('.fi-fo-date-time-picker-panel').forEach(p => {"
	              + "  p.style.maxWidth = '280px';"
	              + "  p.style.overflow = 'hidden';"
	              + "});";
	    ((JavascriptExecutor) driver).executeScript(js);
	}
	
	// Ouvrir le champ
	//public void clickToStartAt() {
	 //   WebElement startInput = wait.until(ExpectedConditions.elementToBeClickable(startAtDataButton));
	//    startInput.click();
	//    fixDatePickerOverflow();
	//}
	
	public void openStartDatePicker() {
	    WebElement startInput = wait.until(ExpectedConditions.elementToBeClickable(startAtDataButton));
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", startInput);

	    // On essaie d'ouvrir le datepicker et on attend que le Month soit visible
	    for (int attempt = 1; attempt <= 2; attempt++) {
	        startInput.click();
	        fixDatePickerOverflow();
	        try {
	            new WebDriverWait(driver, Duration.ofSeconds(4))
	                .until(ExpectedConditions.visibilityOfElementLocated(monthSelectStart));
	            return;
	        } catch (Exception ignored) {
	           
	        }
	    }

	    wait.until(ExpectedConditions.visibilityOfElementLocated(monthSelectStart));
	}
	
	// Choisir le mois
	public void selectMonthStart(String monthVisibleText) {
	    WebElement monthElement = wait.until(ExpectedConditions.visibilityOfElementLocated(monthSelectStart));
	    new Select(monthElement).selectByVisibleText(monthVisibleText);
	}
	
	// Choisir l'annee
	public void selectYearStart(String year) {
	    WebElement yearElement = wait.until(ExpectedConditions.elementToBeClickable(yearSelectStart));
	    yearElement.click();
	    // sélectionne tout le champ par défaut (2026)
	    yearElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));
	    // Efface la sélection avant d'écrire la nouvelle année
	    yearElement.sendKeys(Keys.BACK_SPACE);
	    // Remplace 2026 par la nouvelle valeur
	    yearElement.sendKeys(year);
	    // Prise en compte de nouvelle annee avant le clic de jour
	    yearElement.sendKeys(Keys.TAB);
	}
	
	// Choisir le jour
	public void selectDayStart(String day) {
	    wait.until(ExpectedConditions.elementToBeClickable(dayOptionStart(day))).click();
	}
	
	// Récupérer la valeur affichée dans le champ Starts at
	public String getStartAtValue() {
	    WebElement startAtElement = wait.until(ExpectedConditions.visibilityOfElementLocated(startAtDataButton));
	    return startAtElement.getDomProperty("value");
	}
	
	public String getEndsAtValue() {
	    WebElement endsAtElement = wait.until(ExpectedConditions.visibilityOfElementLocated(endsAtInput));
	    
	    // On patiente jusqu'à ce que le champ ne soit plus vide (le temps que le calcul JS s'effectue)
	    wait.until(driver -> {
	        String val = endsAtElement.getDomProperty("value");
	        return val != null && !val.trim().isEmpty();
	    });
	    
	    return endsAtElement.getDomProperty("value");
	}
	
	public void clickToCreate() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(createBoutton)).click();
	}
	
	public void clickToSection() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(sectionStatus)).click();
	}

}
