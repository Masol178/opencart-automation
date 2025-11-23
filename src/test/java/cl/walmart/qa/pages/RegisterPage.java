package cl.walmart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object para la página de registro
 */
public class RegisterPage extends BasePage {

    // Elementos de la página generados manualmente
    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By confirmPasswordInput = By.id("input-confirm");
    private By privacyPolicyCheckbox = By.name("agree");
    private By continueButton = By.cssSelector("input[value='Continue']");
    private By successMessage = By.cssSelector("#content h1");
    private By privacyPolicyError = By.cssSelector(".alert-danger");
    private By firstNameError = By.cssSelector("input[name='firstname'] + .text-danger");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, 
                                     String telephone, String password) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(emailInput, email);
        type(telephoneInput, telephone);
        type(passwordInput, password);
        type(confirmPasswordInput, password);
    }

    public void acceptPrivacyPolicy() {
        click(privacyPolicyCheckbox);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isPrivacyPolicyErrorDisplayed() {
        return isDisplayed(privacyPolicyError);
    }

    public boolean areFieldErrorsDisplayed() {
        return isDisplayed(privacyPolicyError);
    }
}
