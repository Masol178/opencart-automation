package cl.walmart.qa.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Helper para capturar screenshots durante la ejecución de pruebas
 */
public class ScreenshotHelper {
    
    private static final Logger logger = LogManager.getLogger(ScreenshotHelper.class);
    private static final String SCREENSHOT_DIR = "target/screenshots/";
    
    /**
     * Captura un screenshot y lo guarda con un nombre específico
     * 
     * @param driver WebDriver activo
     * @param screenshotName Nombre descriptivo del screenshot
     * @return Ruta del archivo guardado
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Crear directorio si no existe
            File directory = new File(SCREENSHOT_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
            // Generar timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            
            // Limpiar nombre del screenshot
            String cleanName = screenshotName.replaceAll("[^a-zA-Z0-9]", "_");
            
            // Nombre del archivo
            String fileName = cleanName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;
            
            // Capturar screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destFile = new File(filePath);
            
            // Copiar archivo
            FileUtils.copyFile(srcFile, destFile);
            
            logger.info("Screenshot capturado: {}", filePath);
            return filePath;
            
        } catch (IOException e) {
            logger.error("Error al capturar screenshot: {}", e.getMessage(), e);
            return null;
        }
    }
    
    /**
     * Captura un screenshot con nombre basado en el escenario actual
     * 
     * @param driver WebDriver activo
     * @param scenarioName Nombre del escenario
     * @param stepName Nombre del paso
     * @return Ruta del archivo guardado
     */
    public static String captureScenarioScreenshot(WebDriver driver, String scenarioName, String stepName) {
        String screenshotName = scenarioName + "_" + stepName;
        return captureScreenshot(driver, screenshotName);
    }
    
    /**
     * Captura un screenshot para un test fallido
     * 
     * @param driver WebDriver activo
     * @param scenarioName Nombre del escenario que falló
     * @return Ruta del archivo guardado
     */
    public static String captureFailureScreenshot(WebDriver driver, String scenarioName) {
        String screenshotName = "FAILED_" + scenarioName;
        return captureScreenshot(driver, screenshotName);
    }
    
    /**
     * Obtiene la ruta relativa para embedder en reportes
     * 
     * @param absolutePath Ruta absoluta del screenshot
     * @return Ruta relativa para reportes
     */
    public static String getRelativePath(String absolutePath) {
        if (absolutePath == null) {
            return null;
        }
        return absolutePath.replace("target/", "../");
    }
}
