package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id = "success-message")
    WebElement successMessage;

    @FindBy(id = "home-anchor")
    WebElement homeAnchor;

    public ResultPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public void goToHome() {
        homeAnchor.click();
    }
}
