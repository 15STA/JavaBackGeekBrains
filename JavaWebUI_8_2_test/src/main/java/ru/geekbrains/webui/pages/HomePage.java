package ru.geekbrains.webui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.geekbrains.webui.pages.base.BaseView;
import ru.geekbrains.webui.pages.menu.NavigationBar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class HomePage extends BaseView {

    private SelenideElement userName = $(By.xpath("//li[@id = 'user-menu']"));
    private NavigationBar navigationBar;

    public HomePage() {
        this.navigationBar = new NavigationBar();
    }

            public HomePage seeHomePage(){
            userName.should(visible);
            return this;
        }


    //@Step("In navigation bar")
    public NavigationBar getPageNavigation() {
        return navigationBar;
    }
}

