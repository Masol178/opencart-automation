package cl.walmart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página principal de OpenCart
 */
public class HomePage extends BasePage {

    // Elementos de la página generados manualmente
    private By searchInput = By.name("search");
    private By searchButton = By.cssSelector("button.btn-default");
    private By myAccountDropdown = By.xpath("//a[@title='My Account']");
    private By registerOption = By.linkText("Register");
    private By loginOption = By.linkText("Login");
    private By shoppingCartLink = By.linkText("Shopping Cart");
    private By logo = By.id("logo");
    private By successMessage = By.cssSelector(".alert-success");

    public HomePage(WebDriver driver) {
        super(driver);
        // Esperar a que la página esté completamente cargada
        waitHelper.waitForElementToBePresent(logo);
    }

    public void searchProduct(String productName) {
        // Esperar explícitamente a que el campo de búsqueda esté visible
        waitForElementVisible(searchInput);
        type(searchInput, productName);
        // Esperar a que el botón de búsqueda sea clickeable
        waitForElementClickable(searchButton);
        click(searchButton);
    }

    public void clickMyAccount() {
        click(myAccountDropdown);
    }

    public void clickRegister() {
        clickMyAccount();
        click(registerOption);
    }

    public void clickLogin() {
        clickMyAccount();
        click(loginOption);
    }

    public void goToShoppingCart() {
        click(shoppingCartLink);
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(logo);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
}
