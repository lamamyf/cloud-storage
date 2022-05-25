package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.page.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.page.LogInPage;
import com.udacity.jwdnd.course1.cloudstorage.page.SignUpPage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthenticationTests {

    public static final String SIGN_UP_PAGE_URL = "http://localhost:%d/signup";
    public static final String LOGIN_PAGE_URL = "http://localhost:%d/login";
    public static final String HOME_PAGE_URL = "http://localhost:%d/home";

    @LocalServerPort
    private int port;

    @DisplayName("When a user tries to access a restricted resource without being authenticated") @Nested class UnauthorizedAccessTest extends BaseTest {
        @BeforeEach void when(){
            driver.get(HOME_PAGE_URL.formatted(port));
        }
        @DisplayName("Then got redirected to login page")
        @Test void then() {
            assertEquals(LOGIN_PAGE_URL.formatted(port), driver.getCurrentUrl());
        }
    }

    @DisplayName("When a user tries to sign up") @Nested class SignUpTest extends BaseTest {
        @BeforeEach void when(){
            driver.get(SIGN_UP_PAGE_URL.formatted(port));

            SignUpPage signUpPage = new SignUpPage(driver);
            signUpPage.signUp("lama", "mohammed", "lamamyf", "112233");
        }
        @DisplayName("Then, signed up successfully and got redirected to login page")
        @Test void then() {
            assertEquals(LOGIN_PAGE_URL.formatted(port), driver.getCurrentUrl());
        }
    }

    @DisplayName("Given (lama) as a user") @Nested class LogInTests {
        @DisplayName("When she tries to log in") @Nested class LogInTest extends BaseTest {
            @BeforeEach void when(){
                driver.get(LOGIN_PAGE_URL.formatted(port));

                LogInPage logInPage = new LogInPage(driver);
                logInPage.logIn("lama", "112233");
            }
            @DisplayName("Then got redirected to home page")
            @Test void then() {
                assertEquals(HOME_PAGE_URL.formatted(port), driver.getCurrentUrl());
            }
        }
    }

    @DisplayName("Given (lama) as a logged in user") @Nested class LogOutTests {
        @DisplayName("When she tries to log out") @Nested class LogOutTest extends BaseTest {
            @BeforeEach void when(){
                driver.get(LOGIN_PAGE_URL.formatted(port));

                LogInPage logInPage = new LogInPage(driver);
                logInPage.logIn("lama", "112233");

                HomePage homePage = new HomePage(driver);
                homePage.loggOut();
            }
            @DisplayName("Then got redirected to login page, and the home page is no longer accessible")
            @Test void then() {
                assertEquals(LOGIN_PAGE_URL.formatted(port) + "?logout", driver.getCurrentUrl());

                driver.get(HOME_PAGE_URL.formatted(port));
                assertEquals(LOGIN_PAGE_URL.formatted(port), driver.getCurrentUrl());
            }
        }
    }
}
