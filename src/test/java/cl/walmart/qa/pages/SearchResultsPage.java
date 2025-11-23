package cl.walmart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object para la página de resultados de búsqueda
 */
public class SearchResultsPage extends BasePage {

    // Elementos de la página generados manualmente
    private By pageTitle = By.cssSelector("h1");
    private By productItems = By.cssSelector(".product-layout");
    private By firstProductTitle = By.cssSelector(".product-layout:first-child h4 a");
    private By firstProductImage = By.cssSelector(".product-layout:first-child .image img");
    private By firstProductPrice = By.cssSelector(".product-layout:first-child .price");
    private By addToCartButtons = By.cssSelector("button[onclick*='cart.add']");
    private By firstAddToCartButton = By.cssSelector(".product-layout:first-child button[onclick*='cart.add']");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean hasResults() {
        return isDisplayed(productItems);
    }

    public int getProductCount() {
        List<WebElement> products = driver.findElements(productItems);
        return products.size();
    }

    public String getFirstProductName() {
        return getText(firstProductTitle);
    }

    public void clickFirstProduct() {
        click(firstProductTitle);
    }

    public void addFirstProductToCart() {
        click(firstAddToCartButton);
    }

    public boolean isFirstProductDisplayed() {
        return isDisplayed(firstProductImage);
    }
}
