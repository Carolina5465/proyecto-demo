package demo.steps;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import demo.RunCucumberTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class formularioOrangeHRMSteps {
    private WebDriver driver = Hooks.driver;
    private ExtentTest test = RunCucumberTest.getExtentReports().createTest("Formulario OrangeHRM");

    private void takeScreenshot(String stepName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "c:\\Users\\MA02035\\IdeaProjects\\demo-project\\screenshots\\" + stepName + "_" + System.currentTimeMillis() + ".png";
            Files.createDirectories(Paths.get("c:\\Users\\MA02035\\IdeaProjects\\demo-project\\screenshots"));
            Files.copy(screenshot.toPath(), Paths.get(screenshotPath));
            test.addScreenCaptureFromPath(screenshotPath, stepName);
        } catch (IOException e) {
            test.log(Status.FAIL, "Error al tomar captura: " + e.getMessage());
        }
    }

    @Given("el usuario está en la página del formulario de OrangeHRM")
    public void elUsuarioEstaEnLaPaginaDelFormularioDeOrangeHRM() {
        driver.get("https://www.orangehrm.com/en/book-a-free-demo");
        test.log(Status.PASS, "Navegó a la página del formulario de OrangeHRM");
        takeScreenshot("pagina_formulario_OrangeHRM");
    }

    @When("el usuario da clic en cerrar alerta")
    public void elUsuarioDaClicEnElSegundoElementoConLaClase() {
        List<WebElement> elements = driver.findElements(By.className("CybotCookiebotBannerCloseButton"));
        if (elements.size() > 1) {
            elements.get(1).click();
            test.log(Status.PASS, "Cerró la alerta");
            takeScreenshot("cerrar_alerta");
        } else {
            throw new IllegalStateException("No hay suficientes elementos con la clase CybotCookiebotBannerCloseButton");
        }
    }

    @When("el usuario llena el campo nombre con {string}")
    public void llenarCampoNombres(String query) {
        WebElement searchBar = driver.findElement(By.id("Form_getForm_FullName"));
        searchBar.sendKeys(query);
        test.log(Status.PASS, "Llenó el campo nombre");
        takeScreenshot("llenar_campo_nombre");
    }

    @When("el usuario llena el campo telefono con {string}")
    public void llenarCampoTelefono(String query) {
        WebElement searchBar = driver.findElement(By.id("Form_getForm_Contact"));
        searchBar.sendKeys(query);
        test.log(Status.PASS, "Llenó el campo telefono");
        takeScreenshot("llenar_campo_telefono");
    }

    @When("el usuario llena el campo email con {string}")
    public void llenarCampoEmail(String query) {
        WebElement searchBar = driver.findElement(By.id("Form_getForm_Email"));
        searchBar.sendKeys(query);
        test.log(Status.PASS, "Llenó el campo email");
        takeScreenshot("llenar_campo_email");
    }

    @When("el usuario llena el campo empresa con {string}")
    public void llenarCampoEmpresa(String query) {
        WebElement searchBar = driver.findElement(By.id("Form_getForm_CompanyName"));
        searchBar.sendKeys(query);
        test.log(Status.PASS, "Llenó el campo empresa");
        takeScreenshot("llenar_campo_empresa");
    }

    @When("el usuario selecciona el pais {string}")
    public void seleccionarPais(String pais) {
        WebElement countryDropdown = driver.findElement(By.id("Form_getForm_Country"));
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(pais);
        test.log(Status.PASS, "Seleccionó el país");
        takeScreenshot("seleccionar_pais");
    }

    @When("el usuario selecciona el numero de empleados {string}")
    public void seleccionarNumeroDeEmpleados(String numeroEmpleados) {
        WebElement employeesDropdown = driver.findElement(By.id("Form_getForm_NoOfEmployees"));
        Select select = new Select(employeesDropdown);
        select.selectByVisibleText(numeroEmpleados);
        test.log(Status.PASS, "Seleccionó el número de empleados");
        takeScreenshot("seleccionar_numero_empleados");
    }
}
