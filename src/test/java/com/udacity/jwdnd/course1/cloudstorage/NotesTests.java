package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.LogInPage;
import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.home.NotesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotesTests {

    public static final String LOGIN_PAGE_URL = "http://localhost:%d/login";

    @LocalServerPort
    private int port;

    @DisplayName("Given (lama) as a logged in user") @Nested class AddNoteTests {
        @DisplayName("When she tries to add a note") @Nested class AddNoteTest extends BaseTest {
            private NotesPage notesPage;

            @BeforeEach void when(){
                driver.get(LOGIN_PAGE_URL.formatted(port));
                LogInPage loginPage = new LogInPage(driver);
                loginPage.logIn("lama", "112233");

                notesPage = new NotesPage(driver);
                notesPage.addNote("title", "description");
            }
            @DisplayName("Then the note got added successfully to the note list")
            @Test void then() {
                ResultPage resultPage = new ResultPage(driver);
                assertEquals("Note added successfully.", resultPage.getSuccessMessage());

                resultPage.goToHome();

                assertTrue(notesPage.getNoteTitles().contains("title"));
                assertTrue(notesPage.getNoteDescriptions().contains("description"));
            }
        }
    }

    @DisplayName("Given (lama) as a logged in user ") @Nested class EditNoteTests {
        @DisplayName("When she tries to edit an existent note") @Nested class EditNoteTest extends BaseTest {
            private NotesPage notesPage;
            private int noteToBeEditedIndex = 0;

            @BeforeEach void when(){
                driver.get(LOGIN_PAGE_URL.formatted(port));
                LogInPage loginPage = new LogInPage(driver);
                loginPage.logIn("lama", "112233");

                notesPage = new NotesPage(driver);
                notesPage.editNote(noteToBeEditedIndex, "title edited", "description edited");
            }
            @DisplayName("Then the note got edited successfully")
            @Test void then() {
                ResultPage resultPage = new ResultPage(driver);

                assertEquals("Note updated successfully.", resultPage.getSuccessMessage());

                resultPage.goToHome();

                assertEquals("title edited", notesPage.getNoteTitles().get(noteToBeEditedIndex));
                assertEquals("description edited", notesPage.getNoteDescriptions().get(noteToBeEditedIndex));
            }
        }
    }

    @DisplayName("Given (lama) as a logged in user ") @Nested class DeleteNoteTests {
        @DisplayName("When she tries to delete an existent note") @Nested class DeleteNoteTest extends BaseTest {
            private NotesPage notesPage;
            private int notesLength;

            @BeforeEach void when(){
                driver.get(LOGIN_PAGE_URL.formatted(port));
                LogInPage loginPage = new LogInPage(driver);
                loginPage.logIn("lama", "112233");

                notesPage = new NotesPage(driver);

                notesLength = notesPage.getNoteTitles().size();

                notesPage.deleteNote(1);
            }
            @DisplayName("Then the note got deleted successfully")
            @Test void then() {
                ResultPage resultPage = new ResultPage(driver);

                assertEquals("Note deleted successfully.", resultPage.getSuccessMessage());

                resultPage.goToHome();

                assertEquals(notesLength - 1, notesPage.getNoteTitles().size());
            }
        }
    }
}
