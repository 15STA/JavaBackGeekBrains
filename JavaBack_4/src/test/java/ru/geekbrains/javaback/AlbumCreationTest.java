package ru.geekbrains.javaback;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;


public class AlbumCreationTest extends BaseTest{
    static RequestSpecification requestSpecificationForAlbumTest;
    static ResponseAlbum responseAlbum;

    @BeforeEach
    void beforeTest() {
        requestSpecificationForAlbumTest = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addFormParam("title", "My flowers")
                .build();
    }


    @Test
    void postAlbumCreationTest(){
       responseAlbum = given(requestSpecificationForAlbumTest, positiveResponseSpecification)
               .post("https://api.imgur.com/3/album")
               .prettyPeek()
               .then()
               .extract()
               .response()
               .as(ResponseAlbum.class);
    }

    @AfterEach
    void tearDown(){
        given()
                .headers("Authorization", token)
                .log()
                .method()
                .when()
                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username,
                        responseAlbum.getAlbumData().getDeletehash())
                .prettyPeek()
                .then()
                .body("success", CoreMatchers.equalTo(true))
                .statusCode(200);
    }

}
