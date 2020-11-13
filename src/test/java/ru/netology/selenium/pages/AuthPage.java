package ru.netology.selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static ru.netology.selenium.pages.AuthPage.ErrorType.WRONG_CREDENTIALS;

public class AuthPage {

    public enum ErrorType {
        WRONG_CREDENTIALS,
        BLOCKED_USER
    }

    private SelenideElement login = $("[name='login']");
    private SelenideElement password = $("[name='password']");
    private SelenideElement confirm = $("[type='button']");
    private SelenideElement notificationContent = $(".notification__content");

    private String authErrorText = "Неверно указан логин или пароль";
    private String blockedUser = "Пользователь заблокирован";

    private AuthPage(){};

    public static AuthPage newInstance() {
        return new AuthPage();
    }

    public AuthPage logIn(String login, String password) {
        this.login.setValue(login);
        this.password.setValue(password);
        confirm.click();
        return this;
    }

    public SelenideElement content() {
        return notificationContent;
    }

    public void checkAuthErrorText(ErrorType errorType) {
        content().shouldHave(Condition.text(errorType.equals(WRONG_CREDENTIALS) ? authErrorText : blockedUser));
    }
}
