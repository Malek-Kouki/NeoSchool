package stepdefinitions;

import Page.CreateTeachersPage;
import Page.DashboardPage;
import Page.TeachersPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class CreationEnseignant {
	DashboardPage dashboardPage = new DashboardPage(Hooks.driver);
	TeachersPage teachersPage = new TeachersPage(Hooks.driver);
	CreateTeachersPage createTeachersPage = new CreateTeachersPage(Hooks.driver);
	private String defaultEmployeeNumber;
	
	@When("je clic sur le menu Teachers")
	public void je_clic_sur_le_menu_teachers() {
		dashboardPage.clickToTeachers();
	}

	@Then("La page Teachers apparait")
	public void la_page_teachers_apparait() {
		boolean isOnTeachersPageResult = teachersPage.isOnTeachersPage();
		Assert.assertTrue("La page Teachers n'apparait pas", isOnTeachersPageResult);
	}

	@When("je clic sur le bouton New Teachers")
	public void je_clic_sur_le_bouton_new_teachers() {
		teachersPage.clickToNewTeachers();
	}

	@Then("Le page Create Teachers apparait")
	public void le_page_create_teachers_apparait() {
		boolean isDisplayedTeachers = createTeachersPage.breadcrumbsCreateTeachersIsDisplay();
	    Assert.assertTrue("Le fil d'Ariane n'est pas affiché", isDisplayedTeachers);

	    boolean containsTeachers = createTeachersPage.breadcrumbsContainsTextTeachers("Teachers");
	    Assert.assertTrue("Le fil d'Ariane ne contient pas 'Teachers'", containsTeachers);
	}

	@When("je renseigne le champ First Name de l'enseignant {string}")
	public void je_renseigne_le_champ_first_name_de_l_enseignant(String firstNameTeacherValue) {
		boolean isFirstNameTeacherResult = createTeachersPage.firstNameTeacherIsDisplay();
	    Assert.assertTrue("le champ First Name n'apparait pas", isFirstNameTeacherResult);
	    createTeachersPage.sendFirstNameTeacher(firstNameTeacherValue);
	}

	@When("je renseigne le champ Last Name l'enseignant {string}")
	public void je_renseigne_le_champ_last_name_l_enseignant(String lastNameTeacherValue) {
		boolean isLastNameTeacherResult = createTeachersPage.lastNameTeacherIsDisplay();
	    Assert.assertTrue("le champ Last Name n'apparait pas", isLastNameTeacherResult);
	    createTeachersPage.sendLastNameTeacher(lastNameTeacherValue);
	}

	@When("je renseigne le champ Date of Birth l'enseignant {string}")
	public void je_renseigne_le_champ_date_of_birth_l_enseignant(String dateOfBirth) {
		createTeachersPage.sendDateOfBirthTeacher(dateOfBirth);
	}

	@When("je renseigne le champ School Email {string}")
	public void je_renseigne_le_champ_school_email(String email) {
		boolean isEmailTeacherResult = createTeachersPage.emailTeacherIsDisplay();
	    Assert.assertTrue("le champ School Email n'apparait pas", isEmailTeacherResult);
	   
	    String emailToUse = email.equalsIgnoreCase("AUTO")
	         ? createTeachersPage.generateUniqueEmailTeacher("mohamed.bensalah@futureacademy.com")
	         : createTeachersPage.generateUniqueEmailTeacher(email);

	    createTeachersPage.sendEmailTeacher(emailToUse);
	}

	@When("je renseigne le champ Gender enseignant {string}")
	public void je_renseigne_le_champ_gender_enseignant(String genderTeacherValue) {
		createTeachersPage.selectListGenderTeacher(genderTeacherValue);
	}

	@When("je renseigne le champ Phone {string}")
	public void je_renseigne_le_champ_phone(String phoneTeacherValue) {
		boolean isPhoneTeacherResult = createTeachersPage.phoneTeacherIsDisplay();
	    Assert.assertTrue("le champ Phone n'apparait pas", isPhoneTeacherResult);
	    createTeachersPage.sendPhoneTeacher(phoneTeacherValue);
	}

	@When("je renseigne le champ Postal code {string}")
	public void je_renseigne_le_champ_postal_code(String postalCodeValue) {
		boolean isPostalCodeResult = createTeachersPage.postalCodeIsDisplay();
	    Assert.assertTrue("le champ Postal Code n'apparait pas", isPostalCodeResult);
	    createTeachersPage.sendPostalCode(postalCodeValue);
	}

	@When("je renseigne le champ Address {string}")
	public void je_renseigne_le_champ_address(String addressTeacherValue) {
		boolean addressTeacherResult = createTeachersPage.addressTeacherIsDisplay();
	    Assert.assertTrue("le champ Address n'apparait pas", addressTeacherResult);
	    createTeachersPage.sendAdressTeacher(addressTeacherValue);
	}

	@When("je renseigne le champ Status {string}")
	public void je_renseigne_le_champ_status(String statusTeacherValue) {
		createTeachersPage.selectListStatusTeacher(statusTeacherValue);
	}

	@Then("Employee Number est renseigné par défaut par le système")
	public void employee_number_est_renseigné_par_défaut_par_le_système() {
		boolean employeeNumberResult = createTeachersPage.isEmployeeNumberPopulatedByDefault();
	    Assert.assertTrue("Le champ 'Employee Number' n'est pas renseigné par défaut", employeeNumberResult);
	}
	
	public void saveDefaultEmployeeNumber() {
	    this.defaultEmployeeNumber = createTeachersPage.getEmployeeNumberValue();
	}
	
	public String getSavedEmployeeNumber() {
	    return this.defaultEmployeeNumber;
	}
	
	@When("je clique sur la zone Browse dans la section Photo de enseignant")
	public void je_clique_sur_la_zone_browse_dans_la_section_photo_de_enseignant() {
		boolean browserZoneResult = createTeachersPage.browseZonePhotoIsDisplay();
		Assert.assertTrue("La zone Browse n'est pas affichée", browserZoneResult);
	}

	@When("je sélectionne un fichier image pour enseignant depuis l’ordinateur")
	public void je_sélectionne_un_fichier_image_pour_enseignant_depuis_l_ordinateur() {
		String imagePathTeacher = java.nio.file.Paths.get("src/test/resources/TestData/test.jpg").toAbsolutePath().toString();
		createTeachersPage.uploadPhotoTeacher(imagePathTeacher);
	}

	@Then("l'image sélectionné de l'enseignant est chargé et affiché dans la zone photo")
	public void l_image_sélectionné_de_l_enseignant_est_chargé_et_affiché_dans_la_zone_photo() {
		boolean isUploadedTeacherPhotoResult = createTeachersPage.isPhotoTeacherUploaded();
		Assert.assertTrue("L'image n'est pas chargé et affiché dans la zone image", isUploadedTeacherPhotoResult);
	}

	@When("je clic sur le bouton Create dans teachers")
	public void je_clic_sur_le_bouton_create_dans_teachers() {
		saveDefaultEmployeeNumber();
		createTeachersPage.clickToCreateTeacher();
	}

	@Then("le message de création {string} s'affiche")
	public void le_message_de_création_s_affiche(String expectedConfirmMessage) {
		String actualConfirmMessage = teachersPage.getConfirmMessage();
	    Assert.assertEquals(expectedConfirmMessage, actualConfirmMessage);
	}

	@Then("l'enseignant crée est ajouté  à la liste des enseignants")
	public void l_enseignant_crée_est_ajouté_à_la_liste_des_enseignants() {
		teachersPage.clickViewOnFirstTeacher();
		
		String expectedEmployeeNumber = getSavedEmployeeNumber();
	    String actualEmployeeNumber = teachersPage.getEmployeeNumberOnDetailsPage(expectedEmployeeNumber);

	    Assert.assertEquals(
	        "L'Employee Number sur la fiche ne correspond pas au numéro par défaut",
	        expectedEmployeeNumber,
	        actualEmployeeNumber
	    );
	}
}
