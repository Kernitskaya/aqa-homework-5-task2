package ru.netology.selenium.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.withText;

public class PrivateOfficePage {
    SelenideElement title = $(withText("Личный кабинет"));

    private PrivateOfficePage(){}

    public static PrivateOfficePage newInstance() {
        return new PrivateOfficePage();
    };

    public SelenideElement getTitle() {
        return title;
    }
}
