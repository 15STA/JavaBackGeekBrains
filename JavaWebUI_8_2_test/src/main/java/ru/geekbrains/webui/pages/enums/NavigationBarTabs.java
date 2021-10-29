package ru.geekbrains.webui.pages.enums;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public enum NavigationBarTabs {
    COUNTER_PARTIES(".//span[@class='title' and text()='Контрагенты']"),
    PROJECTS(".//span[@class='title' and text()='Проекты']"),
    PRE_SALES(".//span[@class='title' and text()='Предпродажи']"),
    EXPENSES(".//span[@class='title' and text()='Расходы']"),
    PREMIUMS(".//span[@class='title' and text()='Премии']"),
    DICTIONARIES(".//span[@class='title' and text()='Справочники']"),
    REPORTS(".//span[@class='title' and text()='Отчеты']"),
    SYSTEM(".//li[@class='mobile-hide dropdown']/a/span[@class='title']"),
    HELP(".//span[@class='title' and text()='Помощь']");

    private final SelenideElement by;
    NavigationBarTabs(String xpath) {
        this.by = $(By.xpath(xpath));
    }

    public SelenideElement getBy() {
        return by;
    }
}
