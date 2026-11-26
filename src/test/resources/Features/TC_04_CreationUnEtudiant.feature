@CreationEtudiant @SelectCurrentAcademicYear @LoginAdmin
Feature: Creation un etudiant

	Scenario: TC-04 Créer un étudiant
		When je clic sur le menu Students
		Then La page Students apparait
		When je clic sur le bouton New Students
		Then Le page Create Students apparait
		When je renseigne le champ First Name "Ahmed"
		And je renseigne le champ Last Name "Askri"
		And je renseigne le champ Date of Birth "22/08/2016"
		And je renseigne le champ Gender "Male"
		And je renseigne le champ Admission Number "AUTO_>600"
		And je clique sur la zone Browse dans la section Photo
		And je sélectionne un fichier image depuis l’ordinateur
		Then l'image sélectionné est chargé et affiché dans la zone photo
		When je clic sur le bouton Create dans students
		Then le message de confirmation "Created" s'affiche
		And l'étudiant crée est ajouté  à la liste des étudiants