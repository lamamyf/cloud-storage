package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    @FindBy(id = "inputUsername")
    WebElement usernameInput;

    @FindBy(id = "inputPassword")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement logInButton;

    public LogInPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void logIn(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);

        logInButton.click();
    }
}
