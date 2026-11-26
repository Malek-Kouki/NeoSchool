package stepdefinitions;

import Page.ClassesPage;
import Page.CreateClassesPage;
import Page.DashboardPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CreationClassesAvecInformationsValides {
	DashboardPage dashboardPage = new DashboardPage(Hooks.driver);
	ClassesPage classesPage = new ClassesPage(Hooks.driver);
	CreateClassesPage createClassesPage = new CreateClassesPage(Hooks.driver);
	private int countBeforeCreation;
	
	@When("je clic sur le menu Classes")
	public void je_clic_sur_le_menu_classes() {
		dashboardPage.clickToClasses();
	}

	@Then("La page Classes apparait")
	public void la_page_classes_apparait() {
		boolean isOnClassesPageResult = classesPage.isOnClassesPage();
		Assert.assertTrue("La page Classses n'apparait pas", isOnClassesPageResult);
		
		this.countBeforeCreation = classesPage.getTotalResultsCount();
	}

	@When("je clic sur le bouton New Classes")
	public void je_clic_sur_le_bouton_new_classes() {
		classesPage.clickToNewClasses();
	}

	@Then("Le page Create Classes apparait")
	public void le_page_create_classes_apparait() {
		boolean isDisplayedClasses = createClassesPage.breadcrumbsCreateClassesIsDisplay();
	    Assert.assertTrue("Le fil d'Ariane n'est pas affiché", isDisplayedClasses);

	    boolean containsClasses = createClassesPage.breadcrumbsContainsTextClasses("Classes");
	    Assert.assertTrue("Le fil d'Ariane ne contient pas 'Classes'", containsClasses);
	}

	@When("je clique sur le champ Level")
	public void je_clique_sur_le_champ_level() {
		createClassesPage.clickLevelsField();
	}

	@Then("Le niveau qui a été crée s'affiche {string}")
	public void le_niveau_qui_a_été_crée_s_affiche(String expectedLevel) {
		boolean isPresentLevel = createClassesPage.isLevelPresentInDropdown(expectedLevel);
	    Assert.assertTrue(
	        "Le niveau '" + expectedLevel + "' n'est pas présent dans la liste Level",
	        isPresentLevel
	    );
	}

	@When("je sélectionne un niveau {string} depuis le champs Level")
	public void je_sélectionne_un_niveau_depuis_le_champs_level(String levelName) {
		createClassesPage.selectLevelInDropdown(levelName);
	}

	@When("je renseigne le champs Name Classes {string}")
	public void je_renseigne_le_champs_name_classes(String classesName) {
	    boolean isClassesNameResult = createClassesPage.classesNameIsDisplay();
	    Assert.assertTrue("le champ Classes Name n'apparait pas", isClassesNameResult);

	    // Générer classes auto
	    String nameToUse = (classesName == null || classesName.isBlank() || classesName.equalsIgnoreCase("AUTO"))
	            ? createClassesPage.generateNextClassName()
	            : classesName;

	    createClassesPage.sendClassesName(nameToUse);
	}

	@When("je clic sur le bouton Create Classes")
	public void je_clic_sur_le_bouton_create_classes() {
		createClassesPage.clickToCreateClasses();
	}

	@Then("le message de création {string} de classes s'affiche")
	public void le_message_de_création_de_classes_s_affiche(String expectedConfirmNot) {
		String actualConfirmNot = createClassesPage.getConfirmNotification();
	    Assert.assertEquals(expectedConfirmNot, actualConfirmNot);
	}

	@Then("la classe crée est ajouté à la liste des classes")
	public void la_classe_crée_est_ajouté_à_la_liste_des_classes() {
		dashboardPage.clickToClasses();
		classesPage.waitForPaginationOverview();
	    int countAfterCreation = classesPage.getTotalResultsCount();
	    Assert.assertEquals(
	        "Le nombre total de résultats n'a pas augmenté de 1 après création",
	        countBeforeCreation + 1,
	        countAfterCreation
	    );
	}
}
