package ru.geekbrains.webui.pages.menu;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import ru.geekbrains.webui.pages.base.BaseView;
import ru.geekbrains.webui.pages.enums.NavigationBarTabs;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.actions;

public class NavigationBar {

     public BaseView moveCursorToNavigationTab(NavigationBarTabs tab) {
       actions()
                .moveToElement(tab.getBy())
                .build()
                .perform();
        switch (tab) {
            case COUNTER_PARTIES:
                Selenide.Wait();
                return new CountersPartiesSubMenu();

//            case PROJECTS:
//                Selenide.Wait();
//                return new ProjectSubMenu();
            default:
                throw new IllegalArgumentException("Another tabs currently not implemented");
        }
    }

    @Step("Check visibility of tab {tab}")
    public NavigationBar checkTabVisibility(NavigationBarTabs tab) {
        tab.getBy().shouldBe(visible);
        return this;
    }

}
