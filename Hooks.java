package demo.steps;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to the local chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\MA02035\\IdeaProjects\\demo-project\\src\\test\\java\\demo\\support\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            try {
                // Capturar captura de pantalla antes de cerrar el navegador
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotPath = "c:\\Users\\MA02035\\IdeaProjects\\demo-project\\screenshots\\screenshot_" + System.currentTimeMillis() + ".png";
                Files.createDirectories(Paths.get("c:\\Users\\MA02035\\IdeaProjects\\demo-project\\screenshots"));
                Files.copy(screenshot.toPath(), Paths.get(screenshotPath));

                TimeUnit.SECONDS.sleep(6); // Espera de 6 segundos para visualizar el resultado
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                driver.quit();
            }
        }
    }
}
