package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateAcademicYearsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public CreateAcademicYearsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	private final By breadcrumbAcademicYears = By.className("fi-breadcrumbs-list");
	private final By additionalLabelsAcademicYear = By.className("choices__inner");
	private final By additionalLabelsDetails = By.xpath("//textarea[@id='data.description']");
	private final By bouttonCreate = By.xpath("//button[@id='key-bindings-1']");
	
	public boolean breadcrumbAcademicYearsIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(breadcrumbAcademicYears)).isDisplayed();
	}
	
	public void selectListAcademicYear(String listLabelValue) {
	    // 1. Cliquer sur le champ pour ouvrir la liste
	    WebElement dropdownField = wait.until(ExpectedConditions.elementToBeClickable(additionalLabelsAcademicYear));
	    dropdownField.click();
	    
	    // 2. Cibler directement l'option contenant la valeur textuelle 2026/2027
	    String xpathOption = "//div[@class='choices__list']//*[text()='" + listLabelValue + "']";
	    WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOption)));
	    option.click();
	}
	
	
	public boolean additionalLabelsDetailsIsDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(additionalLabelsDetails)).isDisplayed();
	}
	
	public void sendAdditionalLabelsDetails(String additionalLabelsDetailsValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(additionalLabelsDetails)).sendKeys(additionalLabelsDetailsValue);
	}
	
	public String getStartDateValue() {
		WebElement startDateInput = driver.findElement(By.xpath("//input[@id='data.starts_at']"));
		//Lire la valeur par défaut du champ
		String startValeurActuelle = startDateInput.getAttribute("value");
		System.out.println("Date de début par défaut :" + startValeurActuelle);
		return startValeurActuelle;
	}
	
	public String getEndDateValue() {
		WebElement endDateInput = driver.findElement(By.xpath("//input[@id='data.ends_at']"));
		//Lire la valeur par défaut du champ
		String endValeurActuelle = endDateInput.getAttribute("value");
		System.out.println("Date de fin par défaut :" + endValeurActuelle);
		return endValeurActuelle;
	}
	
	public void uncheckImportData() {
		WebElement checkboxImportData = driver.findElement(By.className("fi-fo-toggle"));
		if (checkboxImportData.isSelected()) {
			checkboxImportData.click();
		}
	}
	
	public void clickToCreate() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(bouttonCreate)).click();
	}
}
