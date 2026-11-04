package stepdefinitions;

import Page.AcademicYearsPage;
import Page.CreateAcademicYearsPage;
import Page.DashboardPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CreationUneAnneeAcademique {
	DashboardPage dashbordPage = new DashboardPage(Hooks.driver);
	AcademicYearsPage academicYearsPage = new AcademicYearsPage(Hooks.driver);
	CreateAcademicYearsPage createAcademicYearsPage = new CreateAcademicYearsPage(Hooks.driver);
	
	@When("Je clic sur le menu Acadamic Years")
	public void je_clic_sur_le_menu_acadamic_years() {		
		dashbordPage.clickToAcademicYearsMenu();
	}

	@Then("La page Academic Years apparait")
	public void la_page_academic_years_apparait() {
		boolean isOnAcademicYearsResult = academicYearsPage.isOnAcademicYearsPage();
		Assert.assertTrue("La page Academic Years apparait", isOnAcademicYearsResult);
	}

	@When("Je clic sur le bouton New Academic Years")
	public void je_clic_sur_le_bouton_new_academic_years() {
		academicYearsPage.saveInitialCount();
		
		academicYearsPage.clickToNewAcademicYearsBoutton();
	}

	@Then("Le page Create Academic Years apparait avec les champs additional_labels.academic_year et additional_labels.details")
	public void le_page_create_academic_years_apparait_avec_les_champs_additional_labels_academic_year_et_additional_labels_details() {
	    boolean isOnCreateAcademicYearsResult = createAcademicYearsPage.breadcrumbAcademicYearsIsDisplay();
	    Assert.assertTrue("Le page Create Academic Years apparait", isOnCreateAcademicYearsResult);
	}

	@When("Renseigner le champ : additional_labels.academic_year avec {string}")
	public void renseigner_le_champ_additional_labels_academic_year_avec(String listLabelValue) {
	    createAcademicYearsPage.selectListAcademicYear(listLabelValue);
	}

	@When("Renseigner le champ : additional_labels.details avec {string}")
	public void renseigner_le_champ_additional_labels_details_avec(String additionalLabelsDetailsValue) {
		boolean isAdditionalLabelsDetailsResult = createAcademicYearsPage.additionalLabelsDetailsIsDisplayed();
	    Assert.assertTrue("le champ additional_labels.details apparait", isAdditionalLabelsDetailsResult);
	    createAcademicYearsPage.sendAdditionalLabelsDetails(additionalLabelsDetailsValue);
	}

	@When("Le champs Start Date doit contenir par défaut la valeur suivante: {string}")
	public void le_champs_start_date_doit_contenir_par_défaut_la_valeur_suivante(String expectedStartDate) {
	   String actualStartDate = createAcademicYearsPage.getStartDateValue();
	   Assert.assertEquals("Erreur : La date de début ne correspond pas à la valeur attendue !", expectedStartDate, actualStartDate);
	}

	@When("Le champs End Date doit contenir par défaut la valeur suivante: {string}")
	public void le_champs_end_date_doit_contenir_par_défaut_la_valeur_suivante(String expectedEndDate) {
		 String actualEndDate = createAcademicYearsPage.getEndDateValue();
		 Assert.assertEquals("Erreur : La date de fin ne correspond pas à la valeur attendue !", expectedEndDate, actualEndDate);
	}

	@When("Décocher la case Import data")
	public void décocher_la_case_import_data() {
		createAcademicYearsPage.uncheckImportData();
	}

	@When("Cliquer sur le bouton Create")
	public void cliquer_sur_le_bouton_create() {
		createAcademicYearsPage.clickToCreate();
	}

	@Then("L'année académique est crée")
	public void l_année_académique_est_crée() {
		boolean isIncreased = academicYearsPage.verifyCountHasIncreased();
		Assert.assertTrue("Erreur : Le nombre total de résultats n'a pas augmenté de 1 !", isIncreased);
	}

	@Then("Le message suivant doit être affiché : {string}")
	public void le_message_suivant_doit_être_affiché (String expectedMessage) {
		boolean isPopupMessageResult = academicYearsPage.popupMessageIsDisplay();
	    Assert.assertTrue("POPUP message", isPopupMessageResult);
	    String actualMessage = academicYearsPage.getPopupMessageText();
	    Assert.assertEquals("Le message de succès ne correspond pas !", expectedMessage, actualMessage);
	}

	@Then("L'année académique crée est ajouté à la liste des années académiques")
	public void l_année_académique_crée_est_ajouté_à_la_liste_des_années_académiques() {
		boolean isAddedAcademicYear = academicYearsPage.isNewAcademicYearAdded("2026/2027");
	    Assert.assertTrue("La nouvelle année académique n'apparaît pas en haut du tableau !", isAddedAcademicYear);
	}
}
