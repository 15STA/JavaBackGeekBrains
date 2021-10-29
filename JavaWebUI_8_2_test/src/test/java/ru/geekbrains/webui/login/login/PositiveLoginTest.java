package ru.geekbrains.webui.login.login;


import io.qameta.allure.Description;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.login.Base.BaseUI;
import ru.geekbrains.webui.pages.HomePage;
import ru.geekbrains.webui.pages.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.geekbrains.webui.login.configuration.Configuration.*;

//@Feature("Login")
//@Severity(SeverityLevel.BLOCKER)
public class PositiveLoginTest extends BaseUI {

   // @Disabled
    @Test
    @Description("Login with an existing user")
    @DisplayName("Login test")
    public void loginWithBaseUserTest() {
        new LoginPage()
                .enterLogin(LOGIN)
                .enterPassword(PASSWORD)
                .clickLoginButton()
                .seeHomePage();
    }
}
