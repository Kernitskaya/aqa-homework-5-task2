package ru.netology.selenium.utils;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import ru.netology.selenium.domain.AuthInfo;

import static io.restassured.RestAssured.given;

public class AuthUtils {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static Gson gson = new Gson();


    public static AuthInfo createUser(boolean isActive) {
        AuthInfo authInfo = new AuthInfo(isActive);
        given()
                .spec(requestSpec)
                .body(gson.toJson(authInfo))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
        return authInfo;
    }
}
