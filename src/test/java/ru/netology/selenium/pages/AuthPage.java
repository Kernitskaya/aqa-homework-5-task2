package ru.netology.selenium.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class AuthPage {

    private SelenideElement login = $("[name='login']");
    private SelenideElement password = $("[name='password']");
    private SelenideElement confirm = $("[type='button']");
    private SelenideElement notificationContent = $(".notification__content");

    public void logIn(String login, String password) {
        this.login.setValue(login);
        this.password.setValue(password);
        confirm.click();
    }

    public SelenideElement getNotificationContent() {
        return notificationContent;
    }
}
