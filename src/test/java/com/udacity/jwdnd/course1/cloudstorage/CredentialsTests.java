package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.LogInPage;
import com.udacity.jwdnd.course1.cloudstorage.page.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.page.home.CredentialsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialsTests {

    public static final String LOGIN_PAGE_URL = "http://localhost:%d/login";

    @LocalServerPort
    private int port;

    @DisplayName("Given (lama) as a logged in user") @Nested class AddCredentialTests {
        @DisplayName("When she tries to add a credential") @Nested class AddCredentialTest extends BaseTest {
            private CredentialsPage credentialsPage;

            @BeforeEach
            void when(){
                driver.get(LOGIN_PAGE_URL.formatted(port));
                LogInPage loginPage = new LogInPage(driver);
                loginPage.logIn("lama", "112233");

                credentialsPage = new CredentialsPage(driver);
                credentialsPage.addCredential("http://google.com", "lamamyf", "112233");
            }
            @DisplayName("Then the credential got added successfully to the credentials list")
            @Test void then() {
                ResultPage resultPage = new ResultPage(driver);
                assertEquals("Credential added successfully.", resultPage.getSuccessMessage());

                resultPage.goToHome();

                assertTrue(credentialsPage.getUrls().contains("http://google.com"));
                assertTrue(credentialsPage.getUsernames().contains("lamamyf"));
            }
        }
    }

    @DisplayName("Given (lama) as a logged in user ") @Nested class EditCredentialTests {
        @DisplayName("When she tries to edit an existent credential") @Nested class EditCredentialTest extends BaseTest {
            private CredentialsPage credentialsPage;
            private int credentialToBeEditedIndex = 0;

            @BeforeEach void when(){
                driver.get(LOGIN_PAGE_URL.formatted(port));
                LogInPage loginPage = new LogInPage(driver);
                loginPage.logIn("lama", "112233");

                credentialsPage = new CredentialsPage(driver);
                credentialsPage.editCredential(credentialToBeEditedIndex, "url edited", "username edited", "passwordEdited");
            }
            @DisplayName("Then the credential got edited successfully")
            @Test void then() {
                ResultPage resultPage = new ResultPage(driver);

                assertEquals("Credential updated successfully.", resultPage.getSuccessMessage());

                resultPage.goToHome();

                assertEquals("url edited", credentialsPage.getUrls().get(credentialToBeEditedIndex));
                assertEquals("username edited", credentialsPage.getUsernames().get(credentialToBeEditedIndex));
            }
        }
    }

    @DisplayName("Given (lama) as a logged in user ") @Nested class DeleteCredentialTests {
        @DisplayName("When she tries to delete an existent credential") @Nested class DeleteCredentialTest extends BaseTest {
            private CredentialsPage credentialsPage;
            private int credentialsLength;

            @BeforeEach void when(){
                driver.get(LOGIN_PAGE_URL.formatted(port));
                LogInPage loginPage = new LogInPage(driver);
                loginPage.logIn("lama", "112233");

                credentialsPage = new CredentialsPage(driver);

                credentialsLength = credentialsPage.getUrls().size();

                credentialsPage.deleteCredential(1);
            }
            @DisplayName("Then the Credential got deleted successfully")
            @Test void then() {
                ResultPage resultPage = new ResultPage(driver);

                assertEquals("Credential deleted successfully.", resultPage.getSuccessMessage());

                resultPage.goToHome();

                assertEquals(credentialsLength - 1, credentialsPage.getUrls().size());
            }
        }
    }
}
