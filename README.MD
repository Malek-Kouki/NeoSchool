# Application NeoSchool

Projet d'automatisation de tests fonctionnels sur l'application web **NeoSchool** (`app.neoschool.innoteam.tn`), réalisé en **Java** avec **Selenium WebDriver**, **Cucumber (BDD)** et **JUnit**, en suivant le design pattern **Page Object Model (POM)**.

## 🛠️ Technologies et Outils

* **Langage :** Java
* **Automatisation web :** Selenium WebDriver
* **Framework BDD :** Cucumber
* **Runner / Assertions :** JUnit
* **Architecture :** Page Object Model (POM)
* **Gestion de projet :** Maven

## 📂 Structure du projet

* `src/test/java/Page` : Classes représentant les pages de l'application et leurs actions (`SignInPage`, `DashboardPage`, `AcademicYearsPage`, `CreateAcademicYearsPage`, `TermsPage`, `CreateTermsPage`, `LevelsPage`, `CreateLevelsPage`, `ClassesPage`, `CreateClassesPage`, `StudentsPage`, `CreateStudentsPage`, `TeachersPage`, `CreateTeachersPage`).
* `src/test/java/stepdefinitions` : Définition des étapes Cucumber (`Hooks`, `ConnexionNeoSchool`, `CreationUneAnneeAcademique`, `CreationUnTrimestre`, `CreationUnEtudiant`, `CreationEnseignant`, `CreationLevels`, `CreationClassesAvecInformationsValides`, `SelectionnerUneAnneeAcademiqueCourante`).
* `src/test/java/runners` : Classes d'exécution des suites de tests Cucumber.
* `src/test/resources/Features` : Scénarios de tests écrits en Gherkin (`.feature`).
* `TestData` : Données de test utilisées par le projet.

## 🧪 Scénarios de tests automatisés

1. **TC_01** : Connexion à l'application NeoSchool (`TC_01_ConnexionNeoSchool.feature`).
2. **TC_02** : Création d'une année académique (`TC_02_CreationUneAnneeAcademique.feature`).
3. **TC_03** : Création d'un trimestre (`TC_03_CreationUnTrimestre.feature`).
4. **TC_04** : Création d'un étudiant (`TC_04_CreationUnEtudiant.feature`).
5. **TC_05** : Création d'un enseignant (`TC_05_CreationEnseignant.feature`).
6. **TC_06** : Création d'un niveau (`TC_06_CreationLevel.feature`).
7. **TC_08** : Création d'une classe avec des informations valides (`TC_08_CreationClasseAvecInformationsValides.feature`).
8. **TC_09** : Sélection de l'année académique courante (`TC_09_SelectionnerUneAnneeAcademiqueCourante.feature`).

## ⚙️ Exécution des tests

1. Cloner le dépôt : git clone https://github.com/Malek-Kouki/NeoSchool.git
2. Ouvrir le projet dans ton IDE (ex. Eclipse) en tant que projet Maven et mettre à jour les dépendances Maven.
3. Lancer les tests via la classe runner dans `src/test/java/runners`