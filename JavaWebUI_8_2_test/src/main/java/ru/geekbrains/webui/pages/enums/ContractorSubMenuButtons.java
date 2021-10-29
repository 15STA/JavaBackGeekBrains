package ru.geekbrains.webui.pages.enums;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.geekbrains.webui.pages.base.SubMenuButtons;

import static com.codeborne.selenide.Selenide.$;

public enum ContractorSubMenuButtons implements SubMenuButtons {

    CONTACT_PERSON_REQUEST(".//span[@class='title' and text()='Контактные лица']");

    private final SelenideElement by;

    ContractorSubMenuButtons(String xpath) {
        this.by = $(By.xpath(xpath));
    }

    public SelenideElement getBy() {
        return by;
    }
}
