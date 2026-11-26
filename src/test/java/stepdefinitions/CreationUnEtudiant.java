package stepdefinitions;

import Page.CreateStudentsPage;
import Page.DashboardPage;
import Page.StudentsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CreationUnEtudiant {
	DashboardPage dashboardPage = new DashboardPage(Hooks.driver);
	StudentsPage studentsPage = new StudentsPage(Hooks.driver);
	CreateStudentsPage createStudentsPage = new CreateStudentsPage(Hooks.driver);
	private String admissionNumberUsed;
	
	@When("je clic sur le menu Students")
	public void je_clic_sur_le_menu_students() {
		dashboardPage.clickToStudents();
	}

	@Then("La page Students apparait")
	public void la_page_students_apparait() {
		boolean isOnStudentsPageResult = studentsPage.isOnStudentsPage();
		Assert.assertTrue("La page Students apparait", isOnStudentsPageResult);
	}

	@When("je clic sur le bouton New Students")
	public void je_clic_sur_le_bouton_new_students() {
		studentsPage.clickToNewStudents();
	}

	@Then("Le page Create Students apparait")
	public void le_page_create_students_apparait() {
		boolean isDisplayed = createStudentsPage.breadcrumbsCreateStudentsIsDisplay();
	    Assert.assertTrue("Le fil d'Ariane n'est pas affiché", isDisplayed);

	    boolean containsStudents = createStudentsPage.breadcrumbsContainsText("Students");
	    Assert.assertTrue("Le fil d'Ariane ne contient pas 'Students'", containsStudents);
	}

	@When("je renseigne le champ First Name {string}")
	public void je_renseigne_le_champ_first_name(String firstNameValue) {
		boolean isDisplayedFirstNameResult = createStudentsPage.firstNameIsDisplay();
	    Assert.assertTrue("le champ Name n'apparait pas", isDisplayedFirstNameResult);
	    createStudentsPage.sendFirstName(firstNameValue);
	}

	@When("je renseigne le champ Last Name {string}")
	public void je_renseigne_le_champ_last_name(String lastNameValue) {
		boolean isDisplayedLastNameResult = createStudentsPage.lastNameIsDisplay();
	    Assert.assertTrue("le champ Last Name n'apparait pas", isDisplayedLastNameResult);
	    createStudentsPage.sendLastName(lastNameValue);
	}

	@When("je renseigne le champ Date of Birth {string}")
	public void je_renseigne_le_champ_date_of_birth(String dateOfBirth) {
	    createStudentsPage.sendDateOfBirth(dateOfBirth);
	}

	@When("je renseigne le champ Gender {string}")
	public void je_renseigne_le_champ_gender(String genderLabelValue) {
	    createStudentsPage.selectListGender(genderLabelValue);
	}

	@When("je renseigne le champ Admission Number {string}")
	public void je_renseigne_le_champ_admission_number(String admissionNumberValue) {
		boolean admissionNumberIsDisplayResult = createStudentsPage.admissionNumberIsDisplay();
		Assert.assertTrue("le champ Admission Number n'apparait pas", admissionNumberIsDisplayResult);

	    String valueToSend;
	    if ("AUTO_>600".equals(admissionNumberValue) || "un chiffre supérieur à 600".equals(admissionNumberValue)) {
	        valueToSend = createStudentsPage.generateAdmissionNumberAbove600();
	    } else {
	        valueToSend = admissionNumberValue;
	    }

	    this.admissionNumberUsed = valueToSend;
	    createStudentsPage.sendAdmissionNumber(valueToSend);
	}

	@When("je clique sur la zone Browse dans la section Photo")
	public void je_clique_sur_la_zone_browse_dans_la_section_photo() {
		boolean browseZoneResult = createStudentsPage.browseZoneIsDisplay();
		Assert.assertTrue("La zone Browse n'est pas affichée", browseZoneResult);
	}

	@When("je sélectionne un fichier image depuis l’ordinateur")
	public void je_sélectionne_un_fichier_image_depuis_l_ordinateur() {
		String imagePath = java.nio.file.Paths.get("src/test/resources/TestData/test.jpg").toAbsolutePath().toString();
	    createStudentsPage.uploadPhoto(imagePath);
	}

	@Then("l'image sélectionné est chargé et affiché dans la zone photo")
	public void l_image_sélectionné_est_chargé_et_affiché_dans_la_zone_photo() {
		boolean isPhotoUploadedResult = createStudentsPage.isPhotoUploaded();
		Assert.assertTrue("L'image n'est pas chargé et affiché dans la zone image", isPhotoUploadedResult);
	}

	@When("je clic sur le bouton Create dans students")
	public void je_clic_sur_le_bouton_create_dans_students() {
		createStudentsPage.clickToCreateStudent();
	}

	@Then("le message de confirmation {string} s'affiche")
	public void le_message_de_confirmation_s_affiche(String expectedMessageConfirm) {
		String actualMessageConfirm = studentsPage.getConfirmText();
	    Assert.assertEquals(expectedMessageConfirm, actualMessageConfirm);
	}

	@Then("l'étudiant crée est ajouté  à la liste des étudiants")
	public void l_étudiant_crée_est_ajouté_à_la_liste_des_étudiants() {
	    studentsPage.clickViewOnFirstStudent();
	    
	    boolean isAdmissionDisplayed = studentsPage.isAdmissionNumberOnViewPage(this.admissionNumberUsed);
	    Assert.assertTrue(
	        "Admission Number " + this.admissionNumberUsed + "' n'est pas affiché sur la fiche de l'étudiant", isAdmissionDisplayed
	    );
	}
}
