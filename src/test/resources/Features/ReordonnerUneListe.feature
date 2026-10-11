Feature: Gestion des interactions

	Scenario: Reordonner  une list
		Given je suis dans l'oglet sortable
		Then la page se charge correctement avec l onglet list et grid
		And l'onglet list est actif par defaut
		And l'element one est affiché
		And l'element two est affiché
		And l'element three est affiché
		And l'element four est affiché
		And l'element five est affiché
		And l'element six est affiché
		When je clique et je maintien l'element three
		Then l'element est mis en surbrillance
		When je fait glisser three vers la position one
		And je relache l'element three à la position cible 
		Then l'element three apparait à la premiere posisition
		
