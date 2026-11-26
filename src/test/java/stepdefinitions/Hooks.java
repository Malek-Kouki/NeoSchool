package stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    public static WebDriver driver;

    private static ExtentReports extent;
    public static ExtentTest test;

    //Url de l'application
    private static final String baseUrl = "https://app.neoschool.innoteam.tn/";
    // Chemin du rapport
    private static final String reportDir =
            System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "custom-report";

    //Parmetre du rapport
    private static void initReportOnce() {
        if (extent != null) return;

        new File(reportDir).mkdirs();

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
                reportDir + File.separator + "Execution Results - " + timestamp + ".html"
        );
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional Testing");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

     
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (extent != null) extent.flush();
        }));
    }

    // Pour lancer le driver
    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        initReportOnce();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.get(baseUrl);

        test = extent.createTest(scenario.getName());
    }
    
    @Before(value = "@LoginAdmin", order = 2)
    public void loginAdminBeforeScenario() {
        ConnexionNeoSchool connexion = new ConnexionNeoSchool();
        connexion.je_suis_dans_la_page_neoschool_sign_in();
        connexion.saisir_le_school_code("neoschool");
        connexion.saisir_l_email("admin@neoschool.test");
        connexion.saisir_le_mot_de_passe("edupassword!");
        connexion.cliquer_sur_le_bouton_login();
        connexion.la_page_dashboard_s_affiche();
    }
    
 // 3ème @Before : sélectionne l'année académique courante après le login
    @Before(value = "@SelectCurrentAcademicYear", order = 3)
    public void selectCurrentAcademicYearBeforeScenario() {
        SelectionUneAnneeAcademiqueCourante selectionAnnee = new SelectionUneAnneeAcademiqueCourante();
        selectionAnnee.je_suis_dans_la_page_dashboard();
        selectionAnnee.je_clic_sur_le_menu_academic_years();
        selectionAnnee.la_page_apparait("Academic Years");
        selectionAnnee.je_clic_sur_dans_la_page_dans_le_tableau_des_années_académiques_dans_la_ligne_de_l_année(
                "Set as current", "Academic Years", "2026/2027");
        selectionAnnee.la_fenetre_s_affiche(
                "Set as Current Academic year This will Change the academic year of the school for all accounts.");
        selectionAnnee.je_clic_sur_le_bouton("Confirm");
        selectionAnnee.le_message_de_confirmation_suivant_s_affiche_academic_year_switched_successfully();
        selectionAnnee.le_message_de_confirmation_suivant_s_affiche_you_have_successfully_switched_to_a_different_academic_year_your_data_are_scoped_accordingly();
        selectionAnnee.une_bannière_verte_s_affiche_en_haut_de_la_page_avec_le_message_suivant(
                "You're working in the Current Academic Year. 2026/2027");
    }

    // Screenshot si cas de test Failed / Ou Passed dans le rapport + état d'exécution
    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            test.fail("FAILED: " + scenario.getName());
            String path = takeScreenshot(scenario.getName());
            test.addScreenCaptureFromPath(path);
        } else {
            test.pass("PASSED: " + scenario.getName());
        }

        if (driver != null) driver.quit();
    }

    private static String takeScreenshot(String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String destination = reportDir + File.separator + screenshotName + "_" + dateName + ".png";
        FileUtils.copyFile(source, new File(destination));
        return destination;
    }
}
