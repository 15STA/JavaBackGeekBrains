package ru.geekbrains.javaback;

import io.restassured.mapper.ObjectMapperType;
import org.apache.commons.io.FileUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;


public class AlbumCreationTest extends BaseTest{
    String albumHash;

    @Test
    void postAlbumCreationTest(){
       // albumHash = given()
        properties.setProperty("albumHash", given()
                .headers("Authorization", token)
                .formParam("title", "Flowers")
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post("https://api.imgur.com/3/album")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.id"));
    }

    @AfterEach
    void tearDown(){
        given()
                .headers("Authorization", token)
                .log()
                .method()
                .when()
                //.delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username, imageDeleteHash)
                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username,
                        properties.getProperty("albumDeletehash"))
                .prettyPeek()
                .then()
                .body("success", CoreMatchers.equalTo(true))
                .statusCode(200);
    }

}
