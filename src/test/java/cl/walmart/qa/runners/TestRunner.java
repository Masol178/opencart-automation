package cl.walmart.qa.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Test Runner mejorado para ejecutar las pruebas de Cucumber
 * Los tags se pueden configurar desde línea de comandos con:
 * mvn test -Dcucumber.filter.tags="@smoke"
 * mvn test -Dcucumber.filter.tags="@regression"
 * mvn test -Dcucumber.filter.tags="@smoke or @regression"
 * mvn test -Dcucumber.filter.tags="@carrito"
 * 
 * Si no se especifica tag desde línea de comandos, se ejecutarán todos (@smoke or @regression)
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "cl.walmart.qa.steps",
    plugin = {
        "pretty",
        "html:target/cucumber-reports/cucumber-html-report.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml"
    },
    monochrome = true,
    tags = "@smoke or @regression"  // Por defecto ejecuta todos los tests smoke y regression
)
public class TestRunner {
}
