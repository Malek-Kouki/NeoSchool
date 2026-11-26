@CreationTrimestre @SelectCurrentAcademicYear @LoginAdmin
Feature: Creation un trimestre

	Scenario: TC-03 Créer un trimestre 
		When je clic sur le menu Terms
		Then La page Terms apparait
		When je clic sur le bouton New terms
		Then Le page Create Terms apparait
		When je renseigne le champ Name "Premier Trimestre"
		And je renseigne le champ Description "Premier trimestre de l'année académique 2027/2028"
		And je renseigne le champ Starts at "15/09/2026"
		Then le champ Ends at est automatiquement rempli avec "15/12/2026"
		When je clic sur le bouton Create
		Then le message "Created" s'affiche
		And le trimestre crée est affiché dans la liste des terms
		