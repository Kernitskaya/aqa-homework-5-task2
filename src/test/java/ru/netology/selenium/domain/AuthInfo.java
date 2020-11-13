package ru.netology.selenium.domain;

public class AuthInfo {
    private String login;
    private String password;
    private String status;

    public AuthInfo(String login, String password, boolean isActive) {
        this.login = login;
        this.password = password;
        status = isActive ? "active" : "blocked";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
