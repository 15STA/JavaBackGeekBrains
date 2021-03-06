package ru.geekbrains.javaback;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.MultiPartSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.io.FileUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static ru.geekbrains.javaback.Endpoints.UPLOAD_IMAGE;


public class ImageUploadTest extends BaseTest{
    private final String PATH_TO_IMAGE = "src/test/resources/rose.jpg";
    static String encodedFile;
    MultiPartSpecification base64MultiPartSpec;
    MultiPartSpecification multiPartSpecWithFile;
    static RequestSpecification requestSpecificationWithAuthAndMultipartImage;
    static RequestSpecification requestSpecificationWithAuthWithBase64;
    static ResponseImage responseImage;

    @BeforeEach
    void beforeTest(){
        byte[] byteArray = getFileContent();
        encodedFile = Base64.getEncoder().encodeToString(byteArray);

        base64MultiPartSpec = new MultiPartSpecBuilder(encodedFile)
                .controlName("image")
                .build();

        multiPartSpecWithFile = new MultiPartSpecBuilder(new File (PATH_TO_IMAGE))
                .controlName("image")
                .build();

        requestSpecificationWithAuthAndMultipartImage = new RequestSpecBuilder()
                .addHeader("Authorization", token)
                .addFormParam("title", "Rose")
              //  .addFormParam("type", "gif")
                .addMultiPart(multiPartSpecWithFile)
                .build();

        requestSpecificationWithAuthWithBase64 = new RequestSpecBuilder()
            .addHeader("Authorization", token)
            .addFormParam("name", "Rose")
            .addMultiPart(base64MultiPartSpec)
            .build();
    }
    @Test
    void uploadFileTest(){
        responseImage = given (requestSpecificationWithAuthWithBase64, positiveResponseSpecification)
           .post(UPLOAD_IMAGE)
           .prettyPeek()
           .then()
           .extract()
           .response()
           .as(ResponseImage.class);

    }

    @Test
    void uploadWithMultiPart() {
        responseImage = given(requestSpecificationWithAuthAndMultipartImage, positiveResponseSpecification)
                .post(UPLOAD_IMAGE)  //"https://api.imgur.com/3/image"
                .prettyPeek()
                .then()
                .extract()
                .response()
                .as(ResponseImage.class);

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
