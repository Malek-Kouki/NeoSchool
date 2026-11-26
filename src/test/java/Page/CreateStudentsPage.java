package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateStudentsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public CreateStudentsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	public final By breadcrumbsCreateStudents = By.className("fi-breadcrumbs-list");
	public final By firstName = By.xpath("//input[@id='data.first_name']");
	public final By lastName = By.xpath("//input[@id='data.last_name']");
	public final By dateOfBirthInput = By.xpath("//input[@id='data.dob']");
	private final By genderLabel = By.cssSelector("select[id='data.gender']");
	private final By admissionNumberInput = By.cssSelector("input[id='data.admission_number']");
	public final By photoFileInput = By.xpath("//div[@id='data.photo']//input[@type='file']");
	public final By browseAction = By.className("filepond--label-action");
	public final By uploadedItem = By.className("filepond--file-status-main");
	public final By createStudentBoutton = By.xpath("//button[@id='key-bindings-1']");

	public boolean breadcrumbsCreateStudentsIsDisplay() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateStudents)).isDisplayed();
	}

	public boolean breadcrumbsContainsText(String expectedText) {
	    WebElement breadcrumbElement = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(breadcrumbsCreateStudents)
	    );
	    String actualText = breadcrumbElement.getText();
	    return actualText.contains(expectedText);
	}
	
	public boolean firstNameIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).isDisplayed();
	}
	
	public void sendFirstName(String firstNameValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(firstNameValue);
	}
	
	public boolean lastNameIsDisplay() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).isDisplayed();
	}
	
	public void sendLastName(String lastNameValue) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(lastNameValue);
	}
	
	public void sendDateOfBirth(String dateDdMmYyyy) {
	    WebElement input = wait.until(ExpectedConditions.elementToBeClickable(dateOfBirthInput));
	    input.click();
	    input.clear();
	    input.sendKeys(dateDdMmYyyy);
	}
	
	public void selectListGender(String genderLabelValue) {
		WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(genderLabel));
	    Select select = new Select(selectElement);
	    select.selectByVisibleText(genderLabelValue.trim());
	}
	
	public boolean admissionNumberIsDisplay() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(admissionNumberInput)).isDisplayed();
	}
	
	// Ajouter une méthode qui génère un nombre > 600
	public String generateAdmissionNumberAbove600() {
	    int number = java.util.concurrent.ThreadLocalRandom.current().nextInt(601, 100000);
	    return String.valueOf(number);
	}

	public void sendAdmissionNumber(String admissionNumberValue) {
	    WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(admissionNumberInput));
	    input.clear();
	    input.sendKeys(admissionNumberValue);
	}
	
	public boolean browseZoneIsDisplay() {
	    return wait.until(ExpectedConditions.visibilityOfElementLocated(browseAction)).isDisplayed();
	}
	
	public void uploadPhoto(String absoluteFilePath) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(browseAction));
		
	    WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(photoFileInput));
	    fileInput.sendKeys(absoluteFilePath);
	}
	
	public boolean isPhotoUploaded() {
		WebDriverWait uploadWait = new WebDriverWait(driver, Duration.ofSeconds(30));
	    return uploadWait.until(ExpectedConditions.visibilityOfElementLocated(uploadedItem)).isDisplayed();
	}
	
	public void clickToCreateStudent() {
	    WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(createStudentBoutton));
	    createBtn.click();
	}
}
