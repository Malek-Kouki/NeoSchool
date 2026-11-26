@SelectionAcademicYear @LoginAdmin
Feature: Selection une annee academique courante
	    
	Scenario: TC-09 Selectionner une annee academique courante
	Given je suis dans la page Dashboard
	When je clic sur le menu Academic Years
	Then La page "Academic Years" apparait
	When je clic sur "Set as current" dans la page "Academic Years", dans le tableau des années académiques, dans la ligne de l'année "2026/2027"
	Then La fenetre "Set as Current Academic year This will Change the academic year of the school for all accounts." s'affiche
	When je clic sur le bouton "Confirm"
	Then Le message de confirmation suivant s'affiche : Academic Year Switched Successfully 
	And  Le message de confirmation suivant s'affiche : You have successfully switched to a different academic year. Your data are scoped accordingly.
	And Une bannière verte s’affiche en haut de la page avec le message suivant : "You're working in the Current Academic Year: 2026/2027"