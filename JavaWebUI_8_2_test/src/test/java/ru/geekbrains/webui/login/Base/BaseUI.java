package ru.geekbrains.webui.login.Base;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.open;
import static ru.geekbrains.webui.login.configuration.Configuration.BASE_URL;
import static ru.geekbrains.webui.login.configuration.Configuration.LOGIN_PATH;

public abstract class BaseUI {

    @BeforeEach
    public void goToCrm() {
        open(BASE_URL + LOGIN_PATH);
    }
}
