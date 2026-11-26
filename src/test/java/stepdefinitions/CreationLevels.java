package stepdefinitions;

import Page.CreateLevelsPage;
import Page.DashboardPage;
import Page.LevelsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CreationLevels {
	DashboardPage dashboardPage = new DashboardPage(Hooks.driver);
	LevelsPage levelsPage = new LevelsPage(Hooks.driver);
	CreateLevelsPage createLevelsPage = new CreateLevelsPage(Hooks.driver);
	private int countBeforeCreation;
	private String createdLevelName;
	
	@When("je clic sur le menu Levels")
	public void je_clic_sur_le_menu_levels() {
		dashboardPage.clickToLevels();
	}

	@Then("La page Levels apparait")
	public void la_page_levels_apparait() {
		boolean isOnLevelsPageResult = levelsPage.isOnLevelsPage();
		Assert.assertTrue("La page Levels n'apparait pas", isOnLevelsPageResult);
		
		this.countBeforeCreation = levelsPage.getTotalResultsCount();
	}

	@When("je clic sur le bouton New Levels")
	public void je_clic_sur_le_bouton_new_levels() {
		levelsPage.clickToNewLevels();
	}

	@Then("Le page Create Levels apparait")
	public void le_page_create_levels_apparait() {
		boolean isDisplayedLevels = createLevelsPage.breadcrumbsCreateLevelsIsDisplay();
	    Assert.assertTrue("Le fil d'Ariane n'est pas affiché", isDisplayedLevels);

	    boolean containsLevels = createLevelsPage.breadcrumbsContainsTextLevels("Levels");
	    Assert.assertTrue("Le fil d'Ariane ne contient pas 'Levels'", containsLevels);
	}

	@When("je laisse le champs Name vide")
	public void je_laisse_le_champs_name_vide() {
		boolean nameLevelsResult = createLevelsPage.nameLevelsIsDisplay();
	    Assert.assertTrue("le champ Name n'apparait pas", nameLevelsResult);
	}

	@When("je clique sur le bouton Create")
	public void je_clique_sur_le_bouton_create() {
		createLevelsPage.clickToCreateLevel();
	}

	@Then("Un message d'erreur {string} s'affiche")
	public void un_message_d_erreur_s_affiche(String expectedMessageValidation) {
		String actualMessageValidation = createLevelsPage.getNameValidationMessage();
	    Assert.assertEquals(expectedMessageValidation, actualMessageValidation);
	}

	@When("je renseigne le champs Name {string}")
	public void je_renseigne_le_champs_name(String nameLevelsValue) {
		this.createdLevelName = nameLevelsValue;
		createLevelsPage.sendNameLevels(nameLevelsValue);
	}

	@When("je clique sur le bouton Create Levels")
	public void je_clique_sur_le_bouton_create_levels() {
		createLevelsPage.clickToCreateLevel();
	}

	@Then("le message de création {string} de levels s'affiche")
	public void le_message_de_création_de_levels_s_affiche(String expectedConfirmLevel) {
		String actualConfirmLevel = levelsPage.getConfirmLevel();
	    Assert.assertEquals(expectedConfirmLevel, actualConfirmLevel);
	}

	@Then("le niveau crée est ajouté à la liste des niveaux")
	public void le_niveau_crée_est_ajouté_à_la_liste_des_niveaux() {
		levelsPage.waitForPaginationOverview();
	    int countAfterCreation = levelsPage.getTotalResultsCount();
	    Assert.assertEquals(
	        "Le nombre total de résultats n'a pas augmenté de 1 après création",
	        countBeforeCreation + 1,
	        countAfterCreation
	    );
	}
}
