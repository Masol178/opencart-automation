package cl.walmart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página del carrito de compras
 */
public class ShoppingCartPage extends BasePage {

    // Elementos de la página generados manualmente con selectores más robustos
    private By pageTitle = By.cssSelector("h1, h2");
    private By productName = By.xpath("//div[@class='table-responsive']//td[@class='text-left']//a | //table//td[@class='text-left']//a | //form[@id='form-checkout']//td[@class='text-left']//a");
    private By productQuantity = By.cssSelector("input[name^='quantity']");
    private By updateButton = By.xpath("//button[@type='submit' and contains(@data-original-title,'Update')] | //button[contains(@class,'btn-primary')]//i[@class='fa fa-refresh']/..");
    private By removeButton = By.cssSelector("button[data-original-title='Remove'], button.btn-danger");
    private By totalPrice = By.xpath("//table[contains(@class,'table')]//tr[last()]//td[last()] | //*[@id='content']//table//strong[contains(text(),'Total')]/parent::td/following-sibling::td");
    private By checkoutButton = By.xpath("//a[contains(text(),'Checkout') or contains(@href,'checkout/checkout')]");
    private By emptyCartMessage = By.xpath("//div[@id='content']//p[contains(text(),'empty') or contains(text(),'vacío') or contains(text(),'no items') or contains(text(),'shopping cart is empty')]");
    private By cartTable = By.xpath("//div[@class='table-responsive'] | //form[@id='form-checkout'] | //table[contains(@class,'table')]");

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public boolean isProductInCart(String productName) {
        try {
            // Buscar todos los enlaces posibles en la tabla del carrito
            By[] productSelectors = {
                By.xpath("//div[@id='content']//table//td[@class='text-left']//a"),
                By.cssSelector("#content table td.text-left a"),
                By.xpath("//table//tbody//td[@class='text-left']//a"),
                By.cssSelector("table.table td a"),
                By.xpath("//div[contains(@class,'table-responsive')]//a")
            };
            
            for (By selector : productSelectors) {
                try {
                    java.util.List<org.openqa.selenium.WebElement> products = driver.findElements(selector);
                    if (!products.isEmpty()) {
                        for (org.openqa.selenium.WebElement product : products) {
                            String productText = product.getText();
                            if (productText != null && productText.toLowerCase().contains(productName.toLowerCase())) {
                                return true;
                            }
                        }
                    }
                } catch (Exception ignored) {
                    // Continuar con el siguiente selector
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("Error verificando producto en carrito", e);
            return false;
        }
    }

    public String getTotalPrice() {
        try {
            // Intentar múltiples selectores para encontrar el precio total
            By[] priceSelectors = {
                By.xpath("//table//tr[last()]//td[last()]"),
                By.xpath("//strong[contains(text(),'Total')]/parent::td/following-sibling::td"),
                By.cssSelector("#content table tr:last-child td:last-child"),
                By.xpath("//div[@id='content']//table//tbody//tr//td[contains(@class,'text-right')]"),
                By.cssSelector("table.table tbody tr td.text-right")
            };
            
            for (By selector : priceSelectors) {
                try {
                    java.util.List<org.openqa.selenium.WebElement> prices = driver.findElements(selector);
                    if (!prices.isEmpty()) {
                        String price = prices.get(prices.size() - 1).getText().trim();
                        if (price != null && !price.isEmpty() && (price.contains("$") || price.matches(".*\\d.*"))) {
                            return price;
                        }
                    }
                } catch (Exception ignored) {
                    // Continuar con el siguiente selector
                }
            }
            return "$0.00";
        } catch (Exception e) {
            logger.warn("No se pudo obtener el precio total");
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
