package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccueilPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	public AccueilPage (WebDriver driver) {
		this.driver= driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	private final By menuInteraction = By.xpath("//a[@href='/interaction']");
	private final By menuAlerte = By.xpath("//a[@href='/alertsWindows']");

		
	public void clickOnInteractionMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(menuInteraction)).click();
	}
	
	public void clickOnAlerteMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(menuAlerte)).click();
	}
	

}
