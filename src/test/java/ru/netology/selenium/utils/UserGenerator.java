package ru.netology.selenium.utils;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.selenium.domain.AuthInfo;

import static io.restassured.RestAssured.given;

public class UserGenerator {
    private static Faker faker = new Faker();
    private static Gson gson = new Gson();
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private UserGenerator() {
    }

    private static void sendCreateUserRequest(AuthInfo authInfo) {
        given()
                .spec(requestSpec)
                .body(gson.toJson(authInfo))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static AuthInfo createUser(boolean isActive) {
        AuthInfo authInfo = new AuthInfo(faker.internet().emailAddress(), faker.internet().password(), isActive);
        sendCreateUserRequest(authInfo);
        return authInfo;
    }

    public static AuthInfo notRegisteredUser() {
        return new AuthInfo(faker.internet().emailAddress(), faker.internet().password(), true);
    }

    public static AuthInfo userWithNotValidLogin() {
        AuthInfo authInfo = new AuthInfo(faker.internet().emailAddress(), faker.internet().password(), true);
        sendCreateUserRequest(authInfo);
        authInfo.setLogin(faker.internet().emailAddress());
        return authInfo;
    }

    public static AuthInfo userWithNotValidPassword() {
        AuthInfo authInfo = new AuthInfo(faker.internet().emailAddress(), faker.internet().password(), true);
        sendCreateUserRequest(authInfo);
        authInfo.setPassword(faker.internet().password());
        return authInfo;
    }

}
