package cl.walmart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página de detalles del producto
 */
public class ProductPage extends BasePage {

    // Elementos de la página generados manualmente
    private By productTitle = By.cssSelector("h1");
    private By productPrice = By.cssSelector("h2");
    private By addToCartButton = By.id("button-cart");
    private By quantityInput = By.id("input-quantity");
    private By productDescription = By.cssSelector("#tab-description");
    private By productImage = By.cssSelector(".thumbnails img");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        return getText(productTitle);
    }

    public boolean isAddToCartButtonDisplayed() {
        return isDisplayed(addToCartButton);
    }

    public void clickAddToCart() {
        click(addToCartButton);
    }

    public void setQuantity(int quantity) {
        type(quantityInput, String.valueOf(quantity));
    }

    public String getProductPrice() {
        return getText(productPrice);
    }

    public boolean isProductImageDisplayed() {
        return isDisplayed(productImage);
    }
}
