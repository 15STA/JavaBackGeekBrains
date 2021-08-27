package ru.geekbrains.javaback;

import io.restassured.response.Response;
import org.apache.commons.io.FileUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;


public class AddImageToAlbumTest extends BaseTest{
    static String encodedFile;
  //  String imageDeleteHash;
    final String PATH_TO_IMAGE = "src/test/resources/rose.jpg";

    @BeforeEach
    void addImage(){
        byte[] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray);
        properties.setProperty("deletehash", given()
                .headers("Authorization", token)
                .multiPart("image", encodedFile)  //Передаваемые параметры
                .formParam("name", "Rose")
                .expect()
                .body("success", is(true))
                .body("data.id", is(notNullValue()))
                .when()
                .post("https://api.imgur.com/3/image")
                .prettyPeek()
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("data.deletehash"));
    }

    @BeforeEach
    void postAlbumCreation(){
        // albumHash = given()
        properties.setProperty("albumDeleteHash", given()
                .headers("Authorization", token)
                .formParam("title", "Flowers")
                .formParam("description", "Beautiful flowers")
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
                .getString("data.deletehash"));
    }

    @Test
    void postImageToAlbum(){
        // imageDeleteHash = given()
     //   String [] deletehashArray = {imageDeleteHash};
        given()
                .headers("Authorization", token)
                .log()
                .method()
                .log()
                .body()
                //.body(deletehashArray)
               // .formParam("deletehashes[]",properties.getProperty(deletehash))
                .param(properties.getProperty(deletehash))
                .expect()
                .body("success", is(true))
                .body("status", equalTo(200))
                .when()
                .post("https://api.imgur.com/3/album/{albumDeleteHash}/add",
                        properties.getProperty("albumDeleteHash"))
                .prettyPeek();
    }


//    @AfterEach
//    void tearDown(){
//        given()
//                .headers("Authorization", token)
//                .log()
//                .method()
//                .when()
//                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username,
//                        deletehash)
//                .prettyPeek()
//                .then()
//                .body("success", CoreMatchers.equalTo(true))
//                .statusCode(200);
//    }

       private byte[] getFileContent(){
        byte [] byteArray = new byte[0];
        try {
            byteArray = FileUtils.readFileToByteArray(new File(PATH_TO_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArray;
    }

}
