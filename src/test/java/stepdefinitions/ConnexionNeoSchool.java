package stepdefinitions;

import Page.DashboardPage;
import Page.SignInPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class ConnexionNeoSchool {
	SignInPage signIn = new SignInPage(Hooks.driver);
	DashboardPage dashbordPage = new DashboardPage(Hooks.driver);
	
	@Given("Je suis dans la page Neoschool Sign in")
	public void je_suis_dans_la_page_neoschool_sign_in() {
		boolean isOnLoginResult = signIn.isOnLogin();
		Assert.assertTrue("erreur l'utilisateur doit être redirigé vers la page login", isOnLoginResult);
	}

	@Then("La page se charge correctement avec les champs {string}, {string} et {string}")
	public void la_page_se_charge_correctement_avec_les_champs_et(String string, String string2, String string3) {
		boolean schoolCodeIsDisplayResult = signIn.schoolCodeIsDisplay();
		Assert.assertTrue("vérifier que le champ School Code s'affiche dans la page login", schoolCodeIsDisplayResult);
		
		boolean emailAddressIsDisplayResult = signIn.emailAddressIsDisplay();
		Assert.assertTrue("vérifier que le champ Email address s'affiche dans la page login", emailAddressIsDisplayResult);
		
		boolean passwordIsDisplayResult = signIn.passwordIsDisplay();
		Assert.assertTrue("vérifier que le champ Password s'affiche dans la page login", passwordIsDisplayResult);
	}

	@When("Saisir le school code {string}")
	public void saisir_le_school_code(String schoolCodeValue) {
	   //String schoolCode = "neoschool";
	   //signIn.sendSchoolCode(schoolCode);
		signIn.sendSchoolCode(schoolCodeValue);
	}

	@When("Saisir l'email {string}")
	public void saisir_l_email(String emailValue) {
	    //String email = "admin@neoschool.test";
	    //signIn.sendEmailAddress(email);
	    signIn.sendEmailAddress(emailValue);
	}

	@When("Saisir le mot de passe {string}")
	public void saisir_le_mot_de_passe(String passwordValue) {
		//String motDePasse = "edupassword!";
		//signIn.sendPassword(motDePasse);
		signIn.sendPassword(passwordValue);
	}

	@When("Cliquer sur le bouton login")
	public void cliquer_sur_le_bouton_login() {
		boolean bouttonLoginIsDisplayResult = signIn.bouttonLoginIsEnabled();
		Assert.assertTrue("erreur le bouton Login doit être affichés", bouttonLoginIsDisplayResult);
	    signIn.clicToLogin();
	}

	@Then("La page Dashboard s'affiche")
	public void la_page_dashboard_s_affiche() {
		boolean isTitleDashboardPageResult = dashbordPage.titleDashboardPageIsDisplay();
		Assert.assertTrue("La page Dashboard s'affiche", isTitleDashboardPageResult);
	}
}
