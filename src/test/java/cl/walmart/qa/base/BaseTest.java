package cl.walmart.qa.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Clase base para inicializar y configurar el WebDriver
 */
public class BaseTest {
    protected static WebDriver driver;
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected static final String BASE_URL = "https://opencart.abstracta.us/";
    protected static final int IMPLICIT_WAIT = 10;

    /**
     * Inicializa el WebDriver según el navegador especificado
     * @param browser Nombre del navegador (chrome, firefox, edge)
     */
    public static void setUp(String browser) {
        logger.info("Iniciando configuración del navegador: " + browser);
        
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--start-maximized");
                    chromeOptions.addArguments("--disable-notifications");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                    
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    break;
                    
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    break;
                    
                default:
                    logger.warn("Navegador no reconocido. Usando Chrome por defecto.");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
            }
            
            driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
            logger.info("WebDriver inicializado correctamente");
            
        } catch (Exception e) {
            logger.error("Error al inicializar WebDriver: " + e.getMessage());
            throw new RuntimeException("No se pudo inicializar el WebDriver", e);
        }
    }

    /**
     * Cierra el navegador y limpia los recursos
     */
    public static void tearDown() {
        if (driver != null) {
            logger.info("Cerrando el navegador");
            driver.quit();
            driver = null;
        }
    }

    /**
     * Navega a la URL base
     */
    public static void navigateToBaseUrl() {
        logger.info("Navegando a: " + BASE_URL);
        driver.get(BASE_URL);
    }
}
