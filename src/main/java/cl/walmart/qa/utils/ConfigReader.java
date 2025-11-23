package cl.walmart.qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase para leer configuraciones desde el archivo config.properties
 * Implementa el patrón Singleton para una única instancia
 */
public class ConfigReader {
    
    private static ConfigReader instance;
    private Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";
    
    private ConfigReader() {
        properties = new Properties();
        loadProperties();
    }
    
    /**
     * Obtiene la instancia única de ConfigReader (Singleton)
     */
    public static ConfigReader getInstance() {
        if (instance == null) {
            synchronized (ConfigReader.class) {
                if (instance == null) {
                    instance = new ConfigReader();
                }
            }
        }
        return instance;
    }
    
    /**
     * Carga las propiedades desde el archivo
     */
    private void loadProperties() {
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el archivo de configuración: " + CONFIG_FILE_PATH, e);
        }
    }
    
    /**
     * Obtiene una propiedad como String
     */
    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("La propiedad '" + key + "' no existe en el archivo de configuración");
        }
        return value;
    }
    
    /**
     * Obtiene una propiedad con valor por defecto
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Obtiene una propiedad como entero
     */
    public int getPropertyAsInt(String key) {
        return Integer.parseInt(getProperty(key));
    }
    
    /**
     * Obtiene una propiedad como booleano
     */
    public boolean getPropertyAsBoolean(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }
    
    // Métodos de conveniencia para propiedades comunes
    
    public String getApplicationUrl() {
        return getProperty("app.url");
    }
    
    public int getImplicitTimeout() {
        return getPropertyAsInt("timeout.implicit");
    }
    
    public int getExplicitTimeout() {
        return getPropertyAsInt("timeout.explicit");
    }
    
    public int getPageLoadTimeout() {
        return getPropertyAsInt("timeout.page.load");
    }
    
    public String getBrowserType() {
        return getProperty("browser.type");
    }
    
    public boolean shouldMaximizeBrowser() {
        return getPropertyAsBoolean("browser.maximize");
    }
    
    public boolean isHeadless() {
        return getPropertyAsBoolean("browser.headless");
    }
}
