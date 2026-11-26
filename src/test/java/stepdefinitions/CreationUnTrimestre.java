package stepdefinitions;

import Page.CreateTermsPage;
import Page.DashboardPage;
import Page.TermsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CreationUnTrimestre {
	DashboardPage dashboardPage = new DashboardPage(Hooks.driver);
	TermsPage termsPage = new TermsPage(Hooks.driver);
	CreateTermsPage createTerms = new CreateTermsPage(Hooks.driver);

	@When("je clic sur le menu Terms")
	public void je_clic_sur_le_menu_terms() {
		dashboardPage.clickToTerms();
	}

	@Then("La page Terms apparait")
	public void la_page_terms_apparait() {
		boolean isOnTermsPageResult = termsPage.isOnTermsPage();
		Assert.assertTrue("La page Terms Years apparait", isOnTermsPageResult);
	}

	@When("je clic sur le bouton New terms")
	public void je_clic_sur_le_bouton_new_terms() {
		termsPage.clickToNewTerms();
	}

	@Then("Le page Create Terms apparait")
	public void le_page_create_terms_apparait() {
		boolean breadcrumbsCreateTermsResult = createTerms.breadcrumbsCreateTermsIsDisplay();
	    Assert.assertTrue("Le fil d'Ariane n'est pas affiché", breadcrumbsCreateTermsResult);

	    boolean containsTerms = createTerms.breadcrumbsContainsTextTerms("Terms");
	    Assert.assertTrue("Le fil d'Ariane ne contient pas 'Terms'", containsTerms);
	}

	@When("je renseigne le champ Name {string}")
	public void je_renseigne_le_champ_name(String nameValue) {
	    boolean isDisplayedNameResult = createTerms.nameIsDisplay();
	    Assert.assertTrue("le champ Name apparait", isDisplayedNameResult);
	    createTerms.sendName(nameValue);
	}

	@When("je renseigne le champ Description {string}")
	public void je_renseigne_le_champ_description(String descriptionValue) {
		boolean isDisplayedDescriptionResult = createTerms.descriptionIsDisplay();
	    Assert.assertTrue("le champ Description apparait", isDisplayedDescriptionResult);
	    createTerms.sendDescription(descriptionValue);
	}

	@When("je renseigne le champ Starts at {string}")
	public void je_renseigne_le_champ_starts_at(String date) {
		String[] parts = date.split("/");
	    // "15" (ou "05" -> "5")
	    String day = String.valueOf(Integer.parseInt(parts[0]));
	    // "09" -> "September"
	    String month = convertMonthToVisibleText(parts[1]);
	    // "2026"
	    String year = parts[2];

	    createTerms.openStartDatePicker();
	    createTerms.selectMonthStart(month);
	    createTerms.selectYearStart(year);
	    createTerms.selectDayStart(day);
	    
	    String displayedDate = createTerms.getStartAtValue();
	    System.out.println("Date affichée dans Starts at : " + displayedDate);
	}

	private String convertMonthToVisibleText(String monthNumber) {
	    return switch (monthNumber) {
	        case "01", "1" -> "January";
	        case "02", "2" -> "February";
	        case "03", "3" -> "March";
	        case "04", "4" -> "April";
	        case "05", "5" -> "May";
	        case "06", "6" -> "June";
	        case "07", "7" -> "July";
	        case "08", "8" -> "August";
	        case "09", "9" -> "September";
	        case "10" -> "October";
	        case "11" -> "November";
	        case "12" -> "December";
	        default -> throw new IllegalArgumentException("Mois invalide : " + monthNumber);
	    };
	}

	@Then("le champ Ends at est automatiquement rempli avec {string}")
	public void le_champ_ends_at_est_automatiquement_rempli_avec(String expectedDate) {
		String actualDate = createTerms.getEndsAtValue();
	    System.out.println("Date affichée dans Ends at : " + actualDate);
	    Assert.assertEquals(expectedDate, actualDate);
	}

	@When("je clic sur le bouton Create")
	public void je_clic_sur_le_bouton_create() {
		createTerms.clickToSection();
		createTerms.clickToCreate();
	}

	@Then("le message {string} s'affiche")
	public void le_message_s_affiche(String expectedMessage) {
		String actualMessage = termsPage.getCreatedNotificationText();
	    Assert.assertEquals(expectedMessage, actualMessage);
	}
	
	@Then("le trimestre crée est affiché dans la liste des terms")
	public void le_trimestre_crée_est_affiché_dans_la_liste_des_terms() {
		String expectedName = "Premier Trimestre";
	    String expectedStartDate = "15 Sep 2026";
	    String expectedEndDate = "15 Dec 2026";
		boolean isTermPresent = termsPage.isTermDisplayedInTable(expectedName, expectedStartDate, expectedEndDate);
	    Assert.assertTrue("Le trimestre " + expectedName + " avec les dates " + expectedStartDate + " et " + expectedEndDate + " n'est pas affiché dans la liste.", isTermPresent);
	}
}
