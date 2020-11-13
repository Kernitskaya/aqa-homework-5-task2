package ru.netology.selenium;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.netology.selenium.domain.AuthInfo;
import ru.netology.selenium.pages.AuthPage;
import ru.netology.selenium.pages.PrivateOfficePage;
import ru.netology.selenium.utils.UserGenerator;

import static com.codeborne.selenide.Selenide.open;


public class AuthTest {
    private String startUrl = "http://localhost:9999";

    @Test
    void testSuccessAuth() {
        AuthInfo authInfo = UserGenerator.createUser(true);
        open(startUrl);
        AuthPage.newInstance().logIn(authInfo.getLogin(), authInfo.getPassword());
        PrivateOfficePage.newInstance().getTitle().shouldBe(Condition.visible);
    }

    @Test
    void testBlockedUser() {
        AuthInfo authInfo = UserGenerator.createUser(false);
        open(startUrl);
        AuthPage.newInstance().logIn(authInfo.getLogin(), authInfo.getPassword())
                .checkAuthErrorText(AuthPage.ErrorType.BLOCKED_USER);
    }

    @Test
    void testNotRegisteredUser() {
        AuthInfo authInfo = UserGenerator.notRegisteredUser();
        open(startUrl);
        AuthPage.newInstance().logIn(authInfo.getLogin(), authInfo.getPassword())
                .checkAuthErrorText(AuthPage.ErrorType.WRONG_CREDENTIALS);
    }

    @Test
    void testNotValidLogin() {
        AuthInfo authInfo = UserGenerator.userWithNotValidLogin();
        open(startUrl);
        AuthPage.newInstance().logIn(authInfo.getLogin(), authInfo.getPassword())
                .checkAuthErrorText(AuthPage.ErrorType.WRONG_CREDENTIALS);
    }

    @Test
    void testNotValidPassword() {
        AuthInfo authInfo = UserGenerator.userWithNotValidPassword();
        open(startUrl);
        AuthPage.newInstance().logIn(authInfo.getLogin(), authInfo.getPassword())
                .checkAuthErrorText(AuthPage.ErrorType.WRONG_CREDENTIALS);
    }
}
