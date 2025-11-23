package cl.walmart.qa.pages;

import cl.walmart.qa.utils.ConfigReader;
import cl.walmart.qa.utils.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Clase base mejorada para todas las páginas (Page Object Model)
 * Implementa mejores prácticas de esperas explícitas y logging
 */
public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WaitHelper waitHelper;
    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected static final ConfigReader config = ConfigReader.getInstance();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        int timeout = config.getExplicitTimeout();
        this.wait = new WebDriverWait(driver, timeout);
        this.waitHelper = new WaitHelper(driver, timeout);
        logger.debug("BasePage inicializada con timeout de {} segundos", timeout);
    }

    /**
     * Espera a que un elemento sea visible y lo devuelve
     */
    protected WebElement waitForElementVisible(By locator) {
        logger.debug("Esperando elemento visible: {}", locator);
        try {
            return waitHelper.waitForElementToBeVisible(locator);
        } catch (Exception e) {
            logger.error("Timeout esperando elemento visible: {}", locator);
            throw e;
        }
    }

    /**
     * Espera a que un elemento sea clickeable y lo devuelve
     */
    protected WebElement waitForElementClickable(By locator) {
        logger.debug("Esperando elemento clickeable: {}", locator);
        try {
            return waitHelper.waitForElementToBeClickable(locator);
        } catch (Exception e) {
            logger.error("Timeout esperando elemento clickeable: {}", locator);
            throw e;
        }
    }

    /**
     * Espera a que un elemento esté presente en el DOM
     */
    protected WebElement waitForElementPresent(By locator) {
        logger.debug("Esperando elemento presente: {}", locator);
        try {
            return waitHelper.waitForElementToBePresent(locator);
        } catch (Exception e) {
            logger.error("Timeout esperando elemento presente: {}", locator);
            throw e;
        }
    }

    /**
     * Espera a que todos los elementos estén presentes
     */
    protected List<WebElement> waitForElementsPresent(By locator) {
        logger.debug("Esperando elementos presentes: {}", locator);
        try {
            return waitHelper.waitForElementsToBePresent(locator);
        } catch (Exception e) {
            logger.error("Timeout esperando elementos presentes: {}", locator);
            throw e;
        }
    }

    /**
     * Hace clic en un elemento con espera explícita
     */
    protected void click(By locator) {
        logger.debug("Haciendo clic en: {}", locator);
        try {
            waitForElementClickable(locator).click();
            logger.debug("Clic exitoso en: {}", locator);
        } catch (Exception e) {
            logger.error("Error al hacer clic en: {}", locator, e);
            throw e;
        }
    }

    /**
     * Hace clic usando JavaScript (alternativa cuando click normal falla)
     */
    protected void clickWithJS(By locator) {
        logger.debug("Haciendo clic con JavaScript en: {}", locator);
        try {
            WebElement element = waitForElementPresent(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            logger.debug("Clic JS exitoso en: {}", locator);
        } catch (Exception e) {
            logger.error("Error al hacer clic con JS en: {}", locator, e);
            throw e;
        }
    }

    /**
     * Escribe texto en un campo con espera explícita
     */
    protected void type(By locator, String text) {
        logger.debug("Escribiendo '{}' en: {}", text, locator);
        try {
            WebElement element = waitForElementVisible(locator);
            element.clear();
            element.sendKeys(text);
            logger.debug("Texto ingresado exitosamente en: {}", locator);
        } catch (Exception e) {
            logger.error("Error al escribir en: {}", locator, e);
            throw e;
        }
    }

    /**
     * Obtiene el texto de un elemento
     */
    protected String getText(By locator) {
        logger.debug("Obteniendo texto de: {}", locator);
        try {
            String text = waitForElementVisible(locator).getText();
            logger.debug("Texto obtenido de {}: '{}'", locator, text);
            return text;
        } catch (Exception e) {
            logger.error("Error al obtener texto de: {}", locator, e);
            throw e;
        }
    }

    /**
     * Verifica si un elemento está visible
     */
    protected boolean isElementVisible(By locator) {
        try {
            logger.debug("Verificando visibilidad de: {}", locator);
            boolean visible = waitForElementVisible(locator).isDisplayed();
            logger.debug("Elemento {} es visible: {}", locator, visible);
            return visible;
        } catch (Exception e) {
            logger.debug("Elemento {} no visible", locator);
            return false;
        }
    }

    /**
     * Verifica si un elemento está visible (alias)
     */
    protected boolean isDisplayed(By locator) {
        return isElementVisible(locator);
    }

    /**
     * Verifica si un elemento está presente en el DOM
     */
    protected boolean isElementPresent(By locator) {
        try {
            logger.debug("Verificando presencia de: {}", locator);
            driver.findElement(locator);
            return true;
        } catch (Exception e) {
            logger.debug("Elemento {} no presente", locator);
            return false;
        }
    }

    /**
     * Espera hasta que la URL contenga el texto especificado
     */
    protected void waitForUrlContains(String urlFragment) {
        logger.debug("Esperando URL contenga: {}", urlFragment);
        try {
            waitHelper.waitForUrlContains(urlFragment);
            logger.debug("URL contiene: {}", urlFragment);
        } catch (Exception e) {
            logger.error("Timeout esperando URL contenga: {}", urlFragment);
            throw e;
        }
    }

    /**
     * Hace scroll hasta un elemento
     */
    protected void scrollToElement(By locator) {
        logger.debug("Haciendo scroll a: {}", locator);
        try {
            WebElement element = waitForElementPresent(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            logger.debug("Scroll exitoso a: {}", locator);
        } catch (Exception e) {
            logger.error("Error al hacer scroll a: {}", locator, e);
            throw e;
        }
    }

    /**
     * Obtiene el título de la página
     */
    public String getPageTitle() {
        String title = driver.getTitle();
        logger.debug("Título de página: {}", title);
        return title;
    }

    /**
     * Obtiene la URL actual
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        logger.debug("URL actual: {}", url);
        return url;
    }
}
