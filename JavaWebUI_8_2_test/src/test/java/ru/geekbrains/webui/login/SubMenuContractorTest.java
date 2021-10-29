package ru.geekbrains.webui.login;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.geekbrains.webui.login.Base.BaseUI;
import ru.geekbrains.webui.login.configuration.Configuration;
import ru.geekbrains.webui.pages.LoginPage;
import ru.geekbrains.webui.pages.enums.NavigationBarTabs;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class SubMenuContractorTest extends BaseUI {
    @Disabled
    @ParameterizedTest
    @MethodSource("navigationTabProvider")
    public void checkMainNavigationTabsTest(NavigationBarTabs barTab) {
        new LoginPage()
                .authorize(Configuration.LOGIN, Configuration.PASSWORD)
                .seeHomePage()
                .getPageNavigation()
                .checkTabVisibility(barTab);

    }

    static NavigationBarTabs[] navigationTabProvider() {
        return NavigationBarTabs.values();
    }
}
