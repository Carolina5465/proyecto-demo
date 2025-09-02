package demo;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        glue = "demo.steps",
        tags = "@OrangeHRM",
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class RunCucumberTest {
    private static ExtentReports extent;

    static {
        // Replace ExtentHtmlReporter with ExtentSparkReporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("target/extent-reports.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static ExtentReports getExtentReports() {
        return extent;
    }

    @AfterClass
    public static void tearDown() {
        extent.flush();
    }
}
