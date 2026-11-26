package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateLevelsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public CreateLevelsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public final By breadcrumbsCreateLevels = By.className("fi-breadcrumbs-list");
	public final By NameLevels = By.xpath("//input[@id='data.name']");
	public final By createLevelBoutton = By.xpath("//button[@id='key-bindings-1']");
	
	public boolean breadcrumbsCreateLevelsIsDisplay() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateLevels)).isDisplayed();
	}
	
	public boolean breadcrumbsContainsTextLevels(String expectedTextLevels) {
	    WebElement breadcrumbLevels = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateLevels)
	    );
	    String actualTextLevels = breadcrumbLevels.getText();
	    return actualTextLevels.contains(expectedTextLevels);
	}
	
	public boolean nameLevelsIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(NameLevels)).isDisplayed();
	}
	
	public void sendNameLevels(String nameLevelsValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(NameLevels)).sendKeys(nameLevelsValue);
	}
	
	public void clickToCreateLevel() {
	    WebElement createBouttonLevel = wait.until(ExpectedConditions.elementToBeClickable(createLevelBoutton));
	    createBouttonLevel.click();
	}
	
	public String getNameValidationMessage() {
	    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(NameLevels));
	    String msg = input.getAttribute("validationMessage");
	    return msg != null ? msg.trim() : "";
	}
}
