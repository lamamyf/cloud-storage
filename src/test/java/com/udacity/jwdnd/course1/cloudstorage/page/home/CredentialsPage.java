package com.udacity.jwdnd.course1.cloudstorage.page.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class CredentialsPage extends HomePage {

    @FindBy(id = "add-credential-button")
    WebElement addCredentialButton;

    @FindBy(id = "credential-url")
    WebElement credentialUrl;

    @FindBy(id = "credential-username")
    WebElement credentialUsername;

    @FindBy(id = "credential-password")
    WebElement credentialPassword;

    @FindBy(id = "credential-submit")
    WebElement submitCredentialButton;

    @FindBy(className = "credentials")
    List<WebElement> credentials;

    @FindBy(className = "url")
    List<WebElement> urls;

    @FindBy(className = "username")
    List<WebElement> usernames;

    By editCredentialLocator = By.id("edit-credential-button");

    By deleteCredentialLocator = By.id("delete-credential-button");

    public CredentialsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addCredential(String url, String username, String password) {
        credentialsTab.click();

        wait.until(ExpectedConditions.elementToBeClickable(addCredentialButton)).click();
        wait.until(ExpectedConditions.visibilityOf(credentialUrl)).sendKeys(url);

        credentialUsername.sendKeys(username);
        credentialPassword.sendKeys(password);

        submitCredentialButton.click();
    }

    public void editCredential(int index, String url, String username, String password) {
        credentialsTab.click();

        wait.until(ExpectedConditions.visibilityOf(credentials.get(index))).findElement(editCredentialLocator).click();

        wait.until(ExpectedConditions.visibilityOf(credentialUrl)).clear();
        credentialUrl.sendKeys(url);

        credentialUsername.clear();
        credentialUsername.sendKeys(username);

        credentialPassword.clear();
        credentialPassword.sendKeys(password);

        submitCredentialButton.click();
    }

    public void deleteCredential(int index) {
        credentialsTab.click();
        wait.until(ExpectedConditions.visibilityOf(credentials.get(index))).findElement(deleteCredentialLocator).click();
    }

    public List<String> getUrls() {
        credentialsTab.click();
        return wait.until(ExpectedConditions.visibilityOfAllElements(urls))
                   .stream()
                   .map(e -> e.getText()).collect(Collectors.toList());
    }

    public List<String> getUsernames() {
        credentialsTab.click();
        return wait.until(ExpectedConditions.visibilityOfAllElements(usernames))
                   .stream()
                   .map(e -> e.getText()).collect(Collectors.toList());
    }
}
