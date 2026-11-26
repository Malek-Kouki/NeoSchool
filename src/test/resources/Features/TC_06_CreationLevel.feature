@CreationLevels @SelectCurrentAcademicYear @LoginAdmin
Feature: Création Level
	Scenario: TC-06 Créer un niveau
		When je clic sur le menu Levels
		Then La page Levels apparait
		When je clic sur le bouton New Levels
		Then Le page Create Levels apparait
		When je laisse le champs Name vide
		And je clique sur le bouton Create
		Then Un message d'erreur "Veuillez renseigner ce champ." s'affiche
		When je renseigne le champs Name "Niveau 1"
		And je clique sur le bouton Create Levels
		Then le message de création "Created" de levels s'affiche
		And le niveau crée est ajouté à la liste des niveaux