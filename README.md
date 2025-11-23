# 🛒 OpenCart Automation Framework

Framework de automatización de pruebas para OpenCart utilizando Selenium WebDriver, Cucumber BDD y Maven.

## 📋 Descripción

Este proyecto implementa pruebas automatizadas end-to-end para el sitio de e-commerce OpenCart, cubriendo funcionalidades críticas como búsqueda de productos, registro de usuarios y proceso de compra.

## 🛠️ Tecnologías Utilizadas

- **Java 8** - Lenguaje de programación
- **Selenium WebDriver 3.141.59** - Automatización del navegador
- **Cucumber 7.14.1** - Framework BDD (Behavior-Driven Development)
- **Maven 3.9.6** - Gestión de dependencias y build
- **JUnit 4.13.2** - Framework de testing
- **Log4j2** - Gestión de logs
- **WebDriverManager** - Gestión automática de drivers

## 🏗️ Arquitectura

El proyecto sigue el patrón **Page Object Model (POM)** y **BDD** con la siguiente estructura:

```
opencart-automation/
├── src/
│   ├── main/java/
│   │   └── cl/walmart/qa/utils/
│   │       ├── ConfigReader.java      # Lector de configuración
│   │       └── WaitHelper.java        # Esperas explícitas
│   └── test/
│       ├── java/cl/walmart/qa/
│       │   ├── pages/                 # Page Objects
│       │   │   ├── BasePage.java
│       │   │   ├── HomePage.java
│       │   │   ├── ProductPage.java
│       │   │   ├── RegisterPage.java
│       │   │   ├── SearchResultsPage.java
│       │   │   └── ShoppingCartPage.java
│       │   ├── steps/
│       │   │   └── StepDefinitions.java
│       │   └── runners/
│       │       └── TestRunner.java
│       └── resources/
│           ├── config.properties
│           └── features/              # Escenarios Gherkin
│               ├── busqueda_productos.feature
│               ├── proceso_compra.feature
│               └── registro_usuario.feature
└── pom.xml
```

## ✅ Casos de Prueba

### 🔍 Búsqueda de Productos
- Búsqueda exitosa de productos existentes
- Validación de múltiples resultados
- Navegación a detalles del producto

### 👤 Registro de Usuario
- Registro exitoso con datos válidos
- Validación de política de privacidad obligatoria
- Validación de campos obligatorios

### 🛍️ Proceso de Compra (Carrito)
- Agregar productos al carrito
- Ver productos en el carrito
- Modificar cantidad de productos
- Eliminar productos del carrito

## 🚀 Requisitos Previos

- Java JDK 8 o superior
- Maven 3.9.6 o superior
- Google Chrome (última versión)
- Conexión a Internet

## ⚙️ Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/Masol178/opencart-automation.git
cd opencart-automation
```

2. Compilar el proyecto:
```bash
mvn clean compile
```

## 🎯 Ejecución de Pruebas

### Ejecutar todas las pruebas:
```bash
mvn clean test
```

### Ejecutar por tags específicos:

**Pruebas de Carrito:**
```bash
mvn test -Dcucumber.filter.tags=@carrito
```

**Pruebas de Búsqueda:**
```bash
mvn test -Dcucumber.filter.tags=@busqueda
```

**Pruebas de Registro:**
```bash
mvn test -Dcucumber.filter.tags=@registro
```

**Solo Smoke Tests:**
```bash
mvn test -Dcucumber.filter.tags=@smoke
```

**Solo Regression Tests:**
```bash
mvn test -Dcucumber.filter.tags=@regression
```

## 📊 Reportes

Después de ejecutar las pruebas, los reportes se generan en:

- **HTML:** `target/cucumber-reports/cucumber-html-report.html`
- **JSON:** `target/cucumber-reports/cucumber.json`
- **XML:** `target/cucumber-reports/cucumber.xml`

## 🏷️ Tags Disponibles

| Tag | Descripción | Escenarios |
|-----|-------------|------------|
| `@smoke` | Pruebas críticas | 7 |
| `@regression` | Suite completa | 4 |
| `@carrito` | Tests de carrito | 4 |
| `@busqueda` | Tests de búsqueda | 3 |
| `@registro` | Tests de registro | 3 |

## ⚙️ Configuración

El archivo `config.properties` permite personalizar:

```properties
# URL de la aplicación
app.url=https://opencart.abstracta.us/

# Timeouts (en segundos)
timeout.implicit=10
timeout.explicit=10
timeout.page.load=30

# Configuración del navegador
browser.type=chrome
browser.maximize=true
browser.headless=false
```

## 🎨 Patrones de Diseño

- **Page Object Model (POM):** Separación de localizadores y lógica de prueba
- **Singleton:** ConfigReader para configuración centralizada
- **Factory:** BasePage para métodos comunes reutilizables

## 📝 Mejores Prácticas Implementadas

✅ Esperas explícitas (WebDriverWait)  
✅ Configuración centralizada  
✅ Logging estructurado con Log4j2  
✅ Manejo robusto de excepciones  
✅ WebDriverManager para gestión automática de drivers  
✅ Hooks @Before y @After para setup/teardown  
✅ Gherkin en español para mejor legibilidad

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia MIT.

## 👤 Autor

**Maria Loreto Solorza**  
QA Automation Engineer

---

**Nota:** Este framework fue desarrollado como parte de una prueba técnica para demostrar conocimientos en automatización de pruebas web.
