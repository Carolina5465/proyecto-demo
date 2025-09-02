package demo.steps;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import demo.RunCucumberTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class OperacionesSteps {
    private WebDriver driver = Hooks.driver;
    private ExtentTest test = RunCucumberTest.getExtentReports().createTest("Operaciones en DuckDuckGo");

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

    @Given("el usuario está en la página de DuckDuckGo")
    public void elUsuarioEstaEnLaPaginaDeGoogle() {
        driver.get("https://duckduckgo.com");
        test.log(Status.PASS, "Navegó a la página de DuckDuckGo");
        takeScreenshot("pagina_duckduckgo");
    }

    @When("el usuario busca {string} en la barra de búsqueda")
    public void elUsuarioBuscaEnLaBarraDeBusqueda(String query) {
        WebElement searchBar = driver.findElement(By.name("q"));
        searchBar.sendKeys(query);
        searchBar.sendKeys(Keys.RETURN);
        test.log(Status.PASS, "Realizó la búsqueda de: " + query);
        takeScreenshot("buscar_" + query);
    }

    @Then("el usuario debería ver resultados relacionados con {string}")
    public void elUsuarioDeberiaVerResultadosRelacionadosCon(String resultadoEsperado) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isResultDisplayed = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), resultadoEsperado));
        if (isResultDisplayed) {
            test.log(Status.PASS, "Se encontraron resultados relacionados con: " + resultadoEsperado);
        } else {
            test.log(Status.FAIL, "No se encontraron resultados relacionados con: " + resultadoEsperado);
        }
        takeScreenshot("resultados_" + resultadoEsperado);
    }

    @When("el usuario realiza la operación {string} con {int} y {int}")
    public void elUsuarioRealizaLaOperacion(String operacion, int numero1, int numero2) {
        try {
            // Ingresar el primer número
            for (char digit : String.valueOf(numero1).toCharArray()) {
                WebElement button = driver.findElement(By.cssSelector("button[value='" + digit + "']"));
                button.click();
            }

            // Seleccionar la operación
            String operationSelector;
            switch (operacion.toLowerCase()) {
                case "suma":
                    operationSelector = "button[value='+']";
                    break;
                case "resta":
                    operationSelector = "button[value='-']";
                    break;
                case "multiplicacion":
                    operationSelector = "button[value='x']";
                    break;
                case "division":
                    operationSelector = "button[value='%']";
                    break;
                default:
                    throw new IllegalArgumentException("Operación no soportada: " + operacion);
            }

            WebElement operationButton = driver.findElement(By.cssSelector(operationSelector));
            operationButton.click();

            // Ingresar el segundo número
            for (char digit : String.valueOf(numero2).toCharArray()) {
                WebElement button = driver.findElement(By.cssSelector("button[value='" + digit + "']"));
                button.click();
            }

            // Presionar el botón "="
            WebElement equalsButton = driver.findElement(By.cssSelector("button[value='=']"));
            equalsButton.click();

            test.log(Status.PASS, "Realizó la operación: " + numero1 + " " + operacion + " " + numero2);
            takeScreenshot("operacion_" + operacion + "_" + numero1 + "_" + numero2);
        } catch (Exception e) {
            test.log(Status.FAIL, "Error al realizar la operación: " + e.getMessage());
        }
    }

    @Then("el resultado debería ser {int}")
    public void elResultadoDeberiaSer(int resultado) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement resultElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#display")));
        String resultText = resultElement.getText();

        if (resultText.equals(String.valueOf(resultado))) {
            test.log(Status.PASS, "El resultado de la operación es correcto: " + resultado);
        } else {
            test.log(Status.FAIL, "El resultado de la operación es incorrecto. Esperado: " + resultado + ", pero fue: " + resultText);
        }

        takeScreenshot("resultado_" + resultado);
    }
}
