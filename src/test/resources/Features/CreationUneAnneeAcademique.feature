@CreationAcademicYear
Feature: Creation une annee academique
	Background: Connexion préalable à l'application TC-01
		Given Je suis dans la page Neoschool Sign in
	    Then La page se charge correctement avec les champs "School Code", "Email address" et "Password visibles"
	    When Saisir le school code "neoschool"
	    And Saisir l'email "admin@neoschool.test"
	    And Saisir le mot de passe "edupassword!"
	    And Cliquer sur le bouton login
	    Then La page Dashboard s'affiche

	Scenario: Créer une année académique et l ajouter à la liste des années académiques
		When Je clic sur le menu Acadamic Years
		Then La page Academic Years apparait
		When Je clic sur le bouton New Academic Years
		Then Le page Create Academic Years apparait avec les champs additional_labels.academic_year et additional_labels.details
		When Renseigner le champ : additional_labels.academic_year avec "2026/2027"
		And Renseigner le champ : additional_labels.details avec "Année académique couvrant les deux semestres avec les périodes d'examens et les activités pédagogiques."
		And Le champs Start Date doit contenir par défaut la valeur suivante: "2026-09-15"
		And Le champs End Date doit contenir par défaut la valeur suivante: "2027-06-30"
		And Décocher la case Import data
		And Cliquer sur le bouton Create
		Then L'année académique est crée
		And Le message suivant doit être affiché : "Academic Year has been created successfully !"
		And L'année académique crée est ajouté à la liste des années académiques