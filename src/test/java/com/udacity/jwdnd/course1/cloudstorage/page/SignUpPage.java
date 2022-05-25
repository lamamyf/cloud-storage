package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    @FindBy(id = "inputFirstName")
    WebElement firstNameInput;

    @FindBy(id = "inputLastName")
    WebElement lastNameInput;

    @FindBy(id = "inputUsername")
    WebElement usernameInput;

    @FindBy(id = "inputPassword")
    WebElement passwordInput;

    @FindBy(id = "buttonSignUp")
    WebElement signUpButton;

    public SignUpPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void signUp(String firstName, String lastName, String username, String password) {
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        signUpButton.click();
    }
}
