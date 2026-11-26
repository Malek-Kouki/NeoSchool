@CreationAcademicYear @LoginAdmin
Feature: Creation une annee academique

	Scenario: TC-02 Créer une année académique et l ajouter à la liste des années académiques
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