package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateTeachersPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public CreateTeachersPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public final By breadcrumbsCreateTeachers = By.className("fi-breadcrumbs-list");
	public final By firstNameTeacher = By.xpath("//input[@id='data.first_name']");
	public final By lastNameTeacher = By.xpath("//input[@id='data.last_name']");
	public final By dateOfBirthTeacher = By.xpath("//input[@id='data.dob']");
	public final By emailTeacher = By.xpath("//input[@id='data.email']");
	private final By genderTeacher = By.xpath("//select[@id='data.gender']");
	private final By phoneTeacher = By.xpath("//input[@id='data.phone']");
	private final By postalCodeTeacher = By.xpath("//input[@id='data.postal_code']");
	private final By addressTeacher = By.xpath("//input[@id='data.address']");
	private final By statusTeacher = By.xpath("//select[@id='data.status']");
	private final By employeeNumber = By.xpath("//input[@id='data.employee_number']");
	public final By browseActionPhoto = By.className("filepond--label-action");
	public final By photoTeacherInput = By.xpath("//div[@id='data.photo']//input[@type='file']");
	public final By uploadingText = By.className("filepond--file-status-main");
	public final By createTeacherBoutton = By.xpath("//button[@id='key-bindings-1']");
	
	public boolean breadcrumbsCreateTeachersIsDisplay() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateTeachers)).isDisplayed();
	}

	public boolean breadcrumbsContainsTextTeachers(String expectedTextTeachers) {
	    WebElement breadcrumbElement = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateTeachers)
	    );
	    String actualText = breadcrumbElement.getText();
	    return actualText.contains(expectedTextTeachers);
	}
	
	public boolean firstNameTeacherIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameTeacher)).isDisplayed();
	}
	
	public void sendFirstNameTeacher(String firstNameTeacherValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameTeacher)).sendKeys(firstNameTeacherValue);
	}
	
	public boolean lastNameTeacherIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameTeacher)).isDisplayed();
	}
	
	public void sendLastNameTeacher(String lastNameTeacherValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameTeacher)).sendKeys(lastNameTeacherValue);
	}
	
	public void sendDateOfBirthTeacher(String dob) {
	    WebElement input = wait.until(ExpectedConditions.elementToBeClickable(dateOfBirthTeacher));
	    input.click();
	    input.clear();
	    input.sendKeys(dob);
	}
	
	public boolean emailTeacherIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(emailTeacher)).isDisplayed();
	}
	
	public String generateUniqueEmailTeacher(String baseEmail) {
		String cleanBase = (baseEmail != null && baseEmail.contains("@"))
		   ? baseEmail
		   : "mohamed.bensalah@futureacademy.com";
		String[] parts = cleanBase.split("@");
		   int randomNum = java.util.concurrent.ThreadLocalRandom.current().nextInt(1000, 99999);
		   return parts[0] + "." + randomNum + "@" + parts[1];
	}

	public void sendEmailTeacher(String emailTeacherValue) {
	    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(emailTeacher));
	    input.clear();
	    input.sendKeys(emailTeacherValue);
	}
	
	public void selectListGenderTeacher(String genderTeacherValue) {
		WebElement selectGenderMale = wait.until(ExpectedConditions.elementToBeClickable(genderTeacher));
	    Select select = new Select(selectGenderMale);
	    select.selectByVisibleText(genderTeacherValue.trim());
	}
	
	public boolean phoneTeacherIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneTeacher)).isDisplayed();
	}
	
	public void sendPhoneTeacher(String phoneTeacherValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(phoneTeacher)).sendKeys(phoneTeacherValue);
	}
	
	public boolean postalCodeIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeTeacher)).isDisplayed();
	}
	
	public void sendPostalCode(String postalCodeValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeTeacher)).sendKeys(postalCodeValue);
	}
	
	public boolean addressTeacherIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(addressTeacher)).isDisplayed();
	}
	
	public void sendAdressTeacher(String addressTeacherValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(addressTeacher)).sendKeys(addressTeacherValue);
	}
	
	public void selectListStatusTeacher(String statusTeacherValue) {
		WebElement selectStatusActive = wait.until(ExpectedConditions.elementToBeClickable(statusTeacher));
	    Select select = new Select(selectStatusActive);
	    select.selectByVisibleText(statusTeacherValue.trim());
	}
	
	public String getEmployeeNumberValue() {
	    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeNumber));
	    String value = input.getAttribute("value");
	    return value != null ? value.trim() : "";
	}
	
	public boolean isEmployeeNumberPopulatedByDefault() {
	    return wait.until(driver -> {
	        String val = driver.findElement(employeeNumber).getAttribute("value");
	        return val != null && !val.trim().isEmpty();
	    });
	}
	
	public boolean browseZonePhotoIsDisplay() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(browseActionPhoto)).isDisplayed();
	}
	
	public void uploadPhotoTeacher(String filePathPhoto) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(browseActionPhoto));
		
	    WebElement fileInputPhoto = wait.until(ExpectedConditions.presenceOfElementLocated(photoTeacherInput));
	    fileInputPhoto.sendKeys(filePathPhoto);
	}
	
	public boolean isPhotoTeacherUploaded() {
		WebDriverWait uploadWaitPhoto = new WebDriverWait(driver, Duration.ofSeconds(30));
	    return uploadWaitPhoto.until(ExpectedConditions.visibilityOfElementLocated(uploadingText)).isDisplayed();
	}
	
	public void clickToCreateTeacher() {
	    WebElement createBoutton = wait.until(ExpectedConditions.elementToBeClickable(createTeacherBoutton));
	    createBoutton.click();
	}
}
