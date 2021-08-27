package ru.geekbrains.javaback;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public abstract class BaseTest {
  static Properties properties = new Properties();
  static String token;
  static String username;
  static String deletehash;
  static RequestSpecification requestSpecificationWithAuth;
  static ResponseSpecification positiveResponseSpecification;

  @BeforeAll
  static void beforeAll(){
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.filters(new AllureRestAssured());
    RestAssured.baseURI = "https://api.imgur.com/3";
    getProperties();
    token = properties.getProperty("token");
    username = properties.getProperty("username");
//    deletehash = properties.getProperty("deletehash");
    positiveResponseSpecification = new ResponseSpecBuilder()
            .expectBody("status", equalTo(200))
            .expectBody("success", is(true))
            .expectContentType(ContentType.JSON)
            .expectStatusCode(200)
            .build();

    requestSpecificationWithAuth = new RequestSpecBuilder()
            .addHeader("Authorization", token)
            .build();


  }



  private static void getProperties(){
    try(InputStream output = new FileInputStream("src/test/resources/hw.properties")) {
      properties.load(output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

