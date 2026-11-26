@CreationEnseignant @SelectCurrentAcademicYear @LoginAdmin
Feature: Creation Enseigant

	Scenario: TC-05 Créer un enseignant
		When je clic sur le menu Teachers
		Then La page Teachers apparait
		When je clic sur le bouton New Teachers
		Then Le page Create Teachers apparait
		When je renseigne le champ First Name de l'enseignant "Mohamed"
		And je renseigne le champ Last Name l'enseignant "Ben Salah"
		And je renseigne le champ Date of Birth l'enseignant "12/05/1985"
		And je renseigne le champ School Email "mohamed.bensalah@futureacademy.com"
		And je renseigne le champ Gender enseignant "Male"
		And je renseigne le champ Phone "+216 59 595 959"
		And je renseigne le champ Postal code "2000"
		And je renseigne le champ Address "31 rue picaso"
		And je renseigne le champ Status "active"
		Then Employee Number est renseigné par défaut par le système
		When je clique sur la zone Browse dans la section Photo de enseignant
		And je sélectionne un fichier image pour enseignant depuis l’ordinateur
		Then l'image sélectionné de l'enseignant est chargé et affiché dans la zone photo
		When je clic sur le bouton Create dans teachers
		Then le message de création "Created" s'affiche
		And l'enseignant crée est ajouté  à la liste des enseignants