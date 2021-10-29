package ru.geekbrains.javaback;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
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
import static ru.geekbrains.javaback.Endpoints.UPLOAD_IMAGE;


public class ImageFavouriteTest extends BaseTest{
    static String encodedFile;
    String imageId;
    final String PATH_TO_IMAGE = "src/test/resources/rose.jpg";
    MultiPartSpecification base64MultiPartSpec;
    MultiPartSpecification multiPartSpecWithFile;
    static RequestSpecification requestSpecificationWithAuthAndMultipartImage;
    static RequestSpecification requestSpecificationWithAuthWithBase64;
    static ResponseImage responseImage;
    static RequestSpecification requestSpecificationForFavouriteImageTest;
    static ResponseFavouriteImage responseFavouriteImage;

    @BeforeEach
    void beforeFavouriteImage(){
        byte[] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray);

        base64MultiPartSpec = new MultiPartSpecBuilder(encodedFile)
                .controlName("image")
                .build();

        requestSpecificationWithAuthWithBase64 = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addFormParam("name", "Rose")
                .addMultiPart(base64MultiPartSpec)
                .build();

        responseImage = given (requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
                .post(UPLOAD_IMAGE)
                .prettyPeek()
                .then()
                .extract()
                .response()
                .as(ResponseImage.class);
    }

    @BeforeEach
    void beforeTest() {
        requestSpecificationForFavouriteImageTest = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .build();
    }

    @Test
    void postImageFavourite(){
        responseFavouriteImage = given (requestSpecificationForFavouriteImageTest, positiveResponseSpecification)
                .post("https://api.imgur.com/3/image/{imageHash}/favorite", responseImage.getImageData().getId())
                .prettyPeek()
                .then()
                .extract()
                .response()
                .as(ResponseFavouriteImage.class);
    }

    @AfterEach
    void tearDown(){
        given()
                .headers("Authorization", token)
                .log()
                .method()
                .when()
                .delete("https://api.imgur.com/3/account/{username}/image/{deleteHash}", username,
                        responseImage.getImageData().getDeletehash())
                .prettyPeek()
                .then()
                .body("success", CoreMatchers.equalTo(true))
                .statusCode(200);
    }

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
