package stepdefinitions;

import Page.AccueilPage;
import Page.AlerteWindowPage;
import Page.AlertsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class GererUneAlerte {
	AccueilPage pageAccueil = new AccueilPage(Hooks.driver);
	AlerteWindowPage pageAlertWindow = new AlerteWindowPage(Hooks.driver);
	AlertsPage pageAlert = new AlertsPage(Hooks.driver);


	
	@Given("je suis la page alertedemoQa")
	public void je_suis_la_page_alertedemo_qa() {
		pageAccueil.clickOnAlerteMenu();
		pageAlertWindow.clickOnAlertMenu();
	}

	@When("je clique sur le bouton Click Button to see alert")
	public void je_clique_sur_le_bouton_click_button_to_see_alert() {
		pageAlert.clickOnButtonToSeeAlert();
	}
	
	@Then("une alerte s'affiche")
	public void une_alerte_s_affiche() {
		pageAlert.CheckAlert();	    
	}

	@When("je clique sur OK de l'alerte")
	public void je_clique_sur_ok_de_l_alerte() {
		pageAlert.AcceptAlert();	    
	}

	@Then("l'alerte disparait")
	public void l_alerte_disparait() {
		boolean ActualResult= pageAlert.IsAlertPresent();	
		Assert.assertFalse("l'alrte doit disparaitre",ActualResult);
	}
}
