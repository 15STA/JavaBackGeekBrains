package ru.geekbrains.javaback;

import static org.junit.Assert.assertTrue;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
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

  @BeforeAll
  static void beforeAll(){
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.filters(new AllureRestAssured());
    getProperties();
    token = properties.getProperty("token");
    username = properties.getProperty("username");
//    deletehash = properties.getProperty("deletehash");
  }

  private static void getProperties(){
    try(InputStream output = new FileInputStream("src/test/resources/hw.properties")) {
      properties.load(output);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

