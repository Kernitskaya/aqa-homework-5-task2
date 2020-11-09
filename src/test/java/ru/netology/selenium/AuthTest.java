package ru.netology.selenium;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.netology.selenium.domain.AuthInfo;
import ru.netology.selenium.pages.AuthPage;
import ru.netology.selenium.pages.PrivateOfficePage;
import ru.netology.selenium.utils.AuthUtils;

import static com.codeborne.selenide.Selenide.open;


public class AuthTest {
    private String startUrl = "http://localhost:9999";
    private String notRegisteredUser = "notregistre@gmail.com";
    private String wrongPassword = "12345";
    private String authErrorText = "Неверно указан логин или пароль";

    @Test
    void testSuccessAuth() {
        AuthInfo authInfo = AuthUtils.createUser(true);
        open(startUrl);
        AuthPage authPage = new AuthPage();
        authPage.logIn(authInfo.getLogin(), authInfo.getPassword());

        PrivateOfficePage privateOfficePage = new PrivateOfficePage();
        privateOfficePage.getTitle().shouldBe(Condition.visible);
    }

    @Test
    void testBlockedUser() {
        AuthInfo authInfo = AuthUtils.createUser(false);
        open(startUrl);
        AuthPage authPage = new AuthPage();
        authPage.logIn(authInfo.getLogin(), authInfo.getPassword());
        authPage.getNotificationContent().shouldHave(Condition.text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void testNotRegisteredUser() {
        open(startUrl);
        AuthPage authPage = new AuthPage();
        authPage.logIn(notRegisteredUser, wrongPassword);
        authPage.getNotificationContent().shouldHave(Condition.text(authErrorText));
    }

    @Test
    void testNotValidUser() {
        AuthInfo authInfo = AuthUtils.createUser(true);
        open(startUrl);
        AuthPage authPage = new AuthPage();
        authPage.logIn(notRegisteredUser, authInfo.getPassword());
        authPage.getNotificationContent().shouldHave(Condition.text(authErrorText));
    }

    @Test
    void testNotValidPassword() {
        AuthInfo authInfo = AuthUtils.createUser(true);
        open(startUrl);
        AuthPage authPage = new AuthPage();
        authPage.logIn(authInfo.getLogin(), wrongPassword);
        authPage.getNotificationContent().shouldHave(Condition.text(authErrorText));
    }
}
