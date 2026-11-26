@CreationClasses @SelectCurrentAcademicYear @LoginAdmin
Feature: Creation une classe avec des informations valides

	Background: Prérequis - TC-06 Créer un niveau
		When je clic sur le menu Levels
		Then La page Levels apparait
		When je clic sur le bouton New Levels
		Then Le page Create Levels apparait
		When je renseigne le champs Name "Niveau 1"
		And je clique sur le bouton Create Levels
		Then le message de création "Created" de levels s'affiche
		
	Scenario: TC-08 Créer une classe avec des informations valides
		When je clic sur le menu Classes
		Then La page Classes apparait
		When je clic sur le bouton New Classes
		Then Le page Create Classes apparait
		When je clique sur le champ Level
		Then Le niveau qui a été crée s'affiche "Niveau 1"
		When je sélectionne un niveau "Niveau 1" depuis le champs Level
		And je renseigne le champs Name Classes "AUTO"
		And je clic sur le bouton Create Classes
		Then le message de création "Created" de classes s'affiche
		And la classe crée est ajouté à la liste des classes