package stepdefinitions;

import Page.AcademicYearsPage;
import Page.DashboardPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class SelectionUneAnneeAcademiqueCourante {
	DashboardPage dashboardPage = new DashboardPage(Hooks.driver);
	AcademicYearsPage academicYearsPage = new AcademicYearsPage(Hooks.driver);
	
	@Given("je suis dans la page Dashboard")
	public void je_suis_dans_la_page_dashboard() {
		boolean titleDashboardPageResult = dashboardPage.titleDashboardPageIsDisplay();
		Assert.assertTrue("La page Dashboard doit apparaitre", titleDashboardPageResult);
	}

	@When("je clic sur le menu Academic Years")
	public void je_clic_sur_le_menu_academic_years() {
		dashboardPage.clickToAcademicYearsMenu();
	}

	@Then("La page {string} apparait")
	public void la_page_apparait(String string) {
		boolean isOnAcademicYearsResult = academicYearsPage.isOnAcademicYearsPage();
		Assert.assertTrue("La page Academic Years apparait", isOnAcademicYearsResult);
	}

	@When("je clic sur {string} dans la page {string}, dans le tableau des années académiques, dans la ligne de l'année {string}")
	public void je_clic_sur_dans_la_page_dans_le_tableau_des_années_académiques_dans_la_ligne_de_l_année(String string, String string2, String string3) {
		academicYearsPage.clickToCurrentButton();
	}

	@Then("La fenetre {string} s'affiche")
	public void la_fenetre_s_affiche(String expectedModalContent) {
		String expectedTitle = "Set as Current Academic year";
		String expectedMessage = "This will Change the academic year of the school for all accounts.";
		Assert.assertTrue("error : Le texte attendu dans le scénario est incomplet", expectedModalContent.contains(expectedTitle) && expectedModalContent.contains(expectedMessage));
		boolean displayed = academicYearsPage.isSetAsCurrentModalDisplayed(expectedTitle, expectedMessage);
	    Assert.assertTrue("La fenêtre de confirmation ou son message n'est pas affiché sur la page", displayed);
	}

	@When("je clic sur le bouton {string}")
	public void je_clic_sur_le_bouton(String buttonName) {
		if ("Confirm".equalsIgnoreCase(buttonName)) {
	        academicYearsPage.clickConfirmSetCurrent();
	    } else {
	        throw new IllegalArgumentException("Bouton non géré dans cette étape : " + buttonName);
	    }
	}

	@Then("Le message de confirmation suivant s'affiche : Academic Year Switched Successfully")
	public void le_message_de_confirmation_suivant_s_affiche_academic_year_switched_successfully() {
	    boolean confirmTitleMessageResult = academicYearsPage.confirmTitleMessageIsDisplayed();
	    Assert.assertTrue("Le message de confirmation suivant s'affiche : Academic Year Switched Successfully", confirmTitleMessageResult);
	}

	@Then("Le message de confirmation suivant s'affiche : You have successfully switched to a different academic year. Your data are scoped accordingly.")
	public void le_message_de_confirmation_suivant_s_affiche_you_have_successfully_switched_to_a_different_academic_year_your_data_are_scoped_accordingly() {
		boolean confirmTextMessageResult = academicYearsPage.confirmTextMessageIsDisplayed();
	    Assert.assertTrue("Le message de confirmation suivant s'affiche : You have successfully switched to a different academic year. Your data are scoped accordingly.", confirmTextMessageResult);
	}

	@Then("Une bannière verte s’affiche en haut de la page avec le message suivant : {string}")
	public void une_bannière_verte_s_affiche_en_haut_de_la_page_avec_le_message_suivant(String expectedMessage) {
		boolean isBanniereResult = academicYearsPage.isBanniereVerteEnHautEtValide(expectedMessage);
	    Assert.assertTrue(
	        "La bannière verte n'est pas affichée en haut de la page avec le message attendu : " + expectedMessage,
	        isBanniereResult
	    );
	}
}
