package ru.geekbrains.javaback;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static ru.geekbrains.javaback.Endpoints.UPLOAD_IMAGE;

public class AccountTests extends BaseTest {
    static RequestSpecification requestSpecificationForAccountTest;
    static ResponseAccount responseAccount;

    @BeforeEach
    void beforeTest() {
        requestSpecificationForAccountTest = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .build();
    }

    @Test
    void getAccountInfoTest() {
        responseAccount = given(requestSpecificationForAccountTest, positiveResponseSpecification)
                .get("https://api.imgur.com/3/account/{username}", username)
                .prettyPeek()
                .then()
                .extract()
                .response()
                .as(ResponseAccount.class);
    }

}
