package com.udacity.jwdnd.course1.cloudstorage.page.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class NotesPage extends HomePage{

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

    By deleteNoteLocator = By.id("delete-note-button");

    public NotesPage(WebDriver driver) {
        super(driver);
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
        wait.until(ExpectedConditions.visibilityOf(notes.get(noteIndex))).findElement(deleteNoteLocator).click();
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
}
