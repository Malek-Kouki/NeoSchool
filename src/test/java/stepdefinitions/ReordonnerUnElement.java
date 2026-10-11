package stepdefinitions;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

import Page.AccueilPage;
import Page.InteractionPage;
import Page.SortablePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReordonnerUnElement {
	AccueilPage pageAccueil = new AccueilPage(Hooks.driver);
	InteractionPage pageInteraction = new InteractionPage(Hooks.driver);
	SortablePage pageSortable = new SortablePage(Hooks.driver);
	
	@Given("je suis dans l'oglet sortable")
	public void je_suis_dans_l_oglet_sortable() {
		pageAccueil.clickOnInteractionMenu();
		pageInteraction.clickOnsortableMenu();
		
	}

	@Then("la page se charge correctement avec l onglet list et grid")
	public void la_page_se_charge_correctement_avec_l_onglet_list_et_grid() {
		pageSortable.isListDisplayed();
		pageSortable.isGridDisplayed();
	}

	@Then("l'onglet list est actif par defaut")
	public void l_onglet_list_est_actif_par_defaut() {
		pageSortable.isOngletListAcif(); 
	}

	@Then("l'element one est affiché")
	public void l_element_one_est_affiché() {
		pageSortable.iselementOneDisplayed();
	}

	@Then("l'element two est affiché")
	public void l_element_two_est_affiché() {
		pageSortable.iselementTwoDisplayed();
	}

	@Then("l'element three est affiché")
	public void l_element_three_est_affiché() {
		pageSortable.iselementThreeDisplayed();
	}

	@Then("l'element four est affiché")
	public void l_element_four_est_affiché() {
		pageSortable.isElementfourDisplayed();
	}

	@Then("l'element five est affiché")
	public void l_element_five_est_affiché() {
		pageSortable.isElementFiveDisplayed();
	}

	@Then("l'element six est affiché")
	public void l_element_six_est_affiché() {
		pageSortable.isElementSixDisplayed();
	}

	@When("je clique et je maintien l'element three")
	public void je_clique_et_je_maintien_l_element_three() {
	//	pageSortable.clickToElement();
	}

	@Then("l'element est mis en surbrillance")
	public void l_element_est_mis_en_surbrillance() {
		//boolean  actualValue= pageSortable.elementThreeEnSurbrillance();
	//	Assert.assertTrue("La couleur de l'element selectionné doit etre gris ", actualValue);
	}

	@When("je fait glisser three vers la position one")
	public void je_fait_glisser_three_vers_la_position_one() {
	  //  pageSortable.dragAndDropElement();
		pageSortable.dragAndDropSecondMethod();
	}

	@When("je relache l'element three à la position cible")
	public void je_relache_l_element_three_à_la_position_cible() {
	  //  pageSortable.relacherElement();
	}

	@Then("l'element three apparait à la premiere posisition")
	public void l_element_three_apparait_à_la_premiere_posisition() {
	 String actualPosition= pageSortable.verifierTextPosition1();
	 assertEquals("la valeur du premier element doit etre égale à Three"
			 ,"Three",actualPosition);
	}


}
