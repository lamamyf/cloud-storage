package com.udacity.jwdnd.course1.cloudstorage.page.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(id = "nav-notes-tab")
    WebElement notesTab;

    @FindBy(id = "logout-button")
    WebElement logOutButton;

    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void loggOut() {
        logOutButton.click();
    }
}
