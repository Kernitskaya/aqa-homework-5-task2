package ru.netology.selenium.domain;


import com.github.javafaker.Faker;

public class AuthInfo {
    private String login;
    private String password;
    private String status;
    private transient Faker faker = new Faker();

    public AuthInfo(boolean isActive) {
        login = faker.internet().emailAddress();
        password = faker.internet().password();
        status = isActive ? "active" : "blocked";
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
