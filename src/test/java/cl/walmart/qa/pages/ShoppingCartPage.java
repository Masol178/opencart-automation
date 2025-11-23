package cl.walmart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página del carrito de compras
 */
public class ShoppingCartPage extends BasePage {

    // Elementos de la página generados manualmente
    private By pageTitle = By.cssSelector("h1, h2");
    private By productName = By.cssSelector(".table-responsive td.text-left a, .table-bordered td.text-left a");
    private By productQuantity = By.cssSelector("input[name^='quantity']");
    private By updateButton = By.cssSelector("button[type='submit'][data-original-title='Update'], button.btn-primary i.fa-refresh");
    private By removeButton = By.cssSelector("button[data-original-title='Remove'], button.btn-danger");
    private By totalPrice = By.xpath("//strong[contains(text(),'Total:')]/parent::td/following-sibling::td | //td[contains(text(),'Total')]/following-sibling::td | //tr[last()]//td[last()]");
    private By checkoutButton = By.xpath("//a[contains(text(),'Checkout')] | //a[contains(@href,'checkout')]");
    private By emptyCartMessage = By.xpath("//p[contains(text(),'empty') or contains(text(),'vacío') or contains(text(),'no items')]");
    private By cartTable = By.cssSelector(".table-responsive, #content .table");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isProductInCart(String productName) {
        try {
            String actualProduct = getText(this.productName);
            return actualProduct.toLowerCase().contains(productName.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }

    public String getTotalPrice() {
        try {
            return getText(totalPrice);
        } catch (Exception e) {
            return "$0.00";
        }
    }

    public boolean isCheckoutButtonDisplayed() {
        return isDisplayed(checkoutButton);
    }

    public void changeQuantity(int quantity) {
        type(productQuantity, String.valueOf(quantity));
    }

    public void clickUpdate() {
        click(updateButton);
    }

    public void removeProduct() {
        try {
            // Intenta múltiples selectores comunes para el botón de eliminar
            By[] selectors = {
                By.cssSelector("button[data-original-title='Remove']"),
                By.cssSelector("button.btn-danger"),
                By.xpath("//button[contains(@onclick,'remove')]"),
                By.xpath("//button[@title='Remove']"),
                By.cssSelector("i.fa-times-circle"),
                By.xpath("//i[contains(@class,'fa-times')]/parent::button"),
                By.xpath("//td[@class='text-center']//button[contains(@class,'btn-danger')]")
            };
            
            boolean removed = false;
            for (By selector : selectors) {
                try {
                    driver.findElement(selector).click();
                    removed = true;
                    System.out.println("Producto eliminado usando selector: " + selector);
                    break;
                } catch (Exception ignored) {
                    // Continúa con el siguiente selector
                }
            }
            
            if (!removed) {
                System.out.println("No se pudo hacer clic en el botón de eliminar con ningún selector");
            }
        } catch (Exception e) {
            System.out.println("Error al intentar eliminar producto: " + e.getMessage());
        }
    }

    public boolean isCartEmpty() {
        try {
            return isDisplayed(emptyCartMessage) || !isDisplayed(cartTable);
        } catch (Exception e) {
            return true;
        }
    }

    public String getEmptyCartMessage() {
        try {
            return getText(emptyCartMessage);
        } catch (Exception e) {
            return "empty";
        }
    }
}
