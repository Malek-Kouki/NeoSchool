package Page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SortablePage {
	private WebDriver driver;
	private WebDriverWait wait;
	private Actions action;// utilisé pour le dragAndDrop
	
	public SortablePage (WebDriver driver) {
		this.driver= driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		this.action = new Actions(driver);
	}

	private final By listMenu =By.cssSelector("#demo-tab-list");
	private final By gridMenu =By.cssSelector("#demo-tab-grid");
	private final By elementOne =By.xpath("(//div[text()=\"One\"])[1]");
	private final By elementTwo =By.xpath("(//div[text()=\"Two\"])[1]");
	private final By elementThree =By.xpath("(//div[text()=\"Three\"])[1]");
	private final By elementfour =By.xpath("(//div[text()=\"Four\"])[1]");
	private final By elementFive =By.xpath("(//div[text()=\"Five\"])[1]");
	private final By elementSix =By.xpath("(//div[text()=\"Six\"])[1]"); 
	
	public boolean isListDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(listMenu)).isDisplayed();
	}
	
	public boolean isGridDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(gridMenu)).isDisplayed();
	}
	
	public boolean isOngletListAcif() {
		String classValue= driver.findElement(listMenu).getAttribute("class");
		return classValue.contains("active");
	}
	
	public boolean iselementOneDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elementOne)).isDisplayed();
	}
	
	public boolean iselementTwoDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elementTwo)).isDisplayed();
	}
	
	public boolean iselementThreeDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elementThree)).isDisplayed();
	}
	
	public boolean isElementfourDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elementfour)).isDisplayed();
	}
	
	public boolean isElementFiveDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elementFive)).isDisplayed();
	}
	public boolean isElementSixDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(elementSix)).isDisplayed();
	}
	
	public void clickToElement() {
		//clickAndHold : fonction prend en parametre une variable de type WebElement
		//clickAndHold : prépare l'action je clique et je maintien sur un élement
		// perform: exécute l'action clickAndHold ,sans perform l'action clickAndHold ne sera pas exécuté
		WebElement WebElementThree= driver.findElement(By.xpath("(//div[text()=\"Three\"])[1]"));
		
	action.clickAndHold(WebElementThree).perform();
		
	}
	
	public boolean elementThreeEnSurbrillance() {
		WebElement WebElement_elementThree= driver.findElement(By.xpath("(//div[text()=\"Three\"])[1]"));
		String couleurElementSelectionne= WebElement_elementThree.getCssValue("background-color");
		System.out.println("la clouleur de l'element selectionné  est "+couleurElementSelectionne);
		String couleurAttendu_gris= "rgba(233, 236, 239, 1)";
		return couleurElementSelectionne.equals(couleurAttendu_gris);
	}
	
	public void dragAndDropElement () {
		WebElement elementOneWebElement =driver.findElement(By.xpath("(//div[text()=\"One\"])[1]"));
		WebElement elementThreeWebElement =driver.findElement(By.xpath("(//div[text()=\"Three\"])[1]"));
		//dragAndDrop glisser et deposer un element
		action.dragAndDrop(elementThreeWebElement,elementOneWebElement ).perform();
	}
	
	public void dragAndDropSecondMethod() {
		WebElement elementOneWebElement =driver.findElement(By.xpath("(//div[text()=\"One\"])[1]"));
		WebElement elementThreeWebElement =driver.findElement(By.xpath("(//div[text()=\"Three\"])[1]"));
		action.clickAndHold(elementThreeWebElement).pause(Duration.ofSeconds(6)).moveToElement(elementOneWebElement).pause(Duration.ofSeconds(6)).release().pause(Duration.ofSeconds(6)).perform();
	}
	
	public void relacherElement() {
		//release(): relacher l'element selectionné
		action.release().perform();
		}
	
	public String verifierTextPosition1() {
		WebElement firstPosition= driver.findElement(By.xpath("(//div[@class='list-group']//div[@class='list-group-item list-group-item-action'])[1]"));
		String firstPositionValue=firstPosition.getText();
		return firstPositionValue;// contient le text de l'element qui est à la 1ere position
	}
}
