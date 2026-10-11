@GestionAlert
Feature: Gestion Des Alertes

		Scenario: Afficher une alerte simple et la fermer avec OK
			Given je suis la page alertedemoQa
			When je clique sur le bouton Click Button to see alert
			Then une alerte s'affiche
			When je clique sur OK de l'alerte
			Then l'alerte disparait