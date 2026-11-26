@ConnexionNeoSchool
Feature: Connexion a NeoSchool

	Scenario: TC-01 Se connecter à NeoSchool et afficher la page Dashboard
		Given Je suis dans la page Neoschool Sign in
	    Then La page se charge correctement avec les champs "School Code", "Email address" et "Password visibles"
	    When Saisir le school code "neoschool"
	    And Saisir l'email "admin@neoschool.test"
	    And Saisir le mot de passe "edupassword!"
	    And Cliquer sur le bouton login
	    Then La page Dashboard s'affiche
