package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage {

    @FindBy(id = "nav-notes-tab")
    WebElement notesTab;

    @FindBy(id = "add-note-button")
    WebElement addNoteButton;

    @FindBy(id = "note-title")
    WebElement noteTitle;

    @FindBy(id = "note-description")
    WebElement noteDescription;

    @FindBy(id = "note-submit")
    WebElement submitNoteButton;

    @FindBy(className = "notes")
    List<WebElement> notes;

    @FindBy(className = "title")
    List<WebElement> noteTitles;

    @FindBy(className = "description")
    List<WebElement> noteDescriptions;

    By editNoteLocator = By.id("edit-note-button");

    By editDeleteLocator = By.id("delete-note-button");

    @FindBy(id = "logout-button")
    WebElement logOutButton;

    WebDriverWait wait;

    public HomePage(WebDriver driver) {
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void addNote(String title, String description) {
        notesTab.click();

        wait.until(ExpectedConditions.elementToBeClickable(addNoteButton)).click();
        wait.until(ExpectedConditions.visibilityOf(noteTitle)).sendKeys(title);

        noteDescription.sendKeys(description);
        submitNoteButton.click();
    }

    public void editNote(int noteIndex, String title, String description) {
        notesTab.click();

        wait.until(ExpectedConditions.visibilityOf(notes.get(noteIndex))).findElement(editNoteLocator).click();

        wait.until(ExpectedConditions.visibilityOf(noteTitle)).clear();
        noteTitle.sendKeys(title);

        noteDescription.clear();
        noteDescription.sendKeys(description);

        submitNoteButton.click();
    }

    public void deleteNote(int noteIndex) {
        notesTab.click();

        wait.until(ExpectedConditions.visibilityOf(notes.get(noteIndex))).findElement(editDeleteLocator).click();
    }

    public List<String> getNoteTitles() {
        notesTab.click();
        return wait.until(ExpectedConditions.visibilityOfAllElements(noteTitles))
                   .stream()
                   .map(e -> e.getText()).collect(Collectors.toList());
    }

    public List<String> getNoteDescriptions() {
        notesTab.click();
        return wait.until(ExpectedConditions.visibilityOfAllElements(noteDescriptions))
                   .stream()
                   .map(e -> e.getText()).collect(Collectors.toList());
    }

    public void loggOut() {
        logOutButton.click();
    }
}
