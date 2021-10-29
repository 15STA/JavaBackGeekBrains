package ru.geekbrains.webui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import ru.geekbrains.webui.pages.base.BaseView;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BaseView {

    private SelenideElement inputLogin = $(By.xpath(".//input[@name= '_username']"));
    private SelenideElement inputPassword = $(By.xpath(".//input[@name= '_password']"));
    private SelenideElement buttonSignIn = $(By.xpath(".//button[@name='_submit']"));
    private SelenideElement loginHeader = $(By.xpath("//h2[@class = 'title']"));

    public LoginPage enterLogin(String login) {
        inputLogin.setValue(login);
        return this;
    }

    public LoginPage enterPassword(String password) {
    inputPassword.setValue(password);
    return this;
    }

    public HomePage clickLoginButton() {
        buttonSignIn.click();
        return page(HomePage.class);
    }

    public HomePage authorize(String login, String password){
        inputLogin.setValue(login);
        inputPassword.setValue(password);
        buttonSignIn.click();
        loginHeader.should(disappear);
        return page(HomePage.class);
    }

    public LoginPage notSeeLoginHeader(){
        loginHeader.should(disappear);
        return this;
    }

}

