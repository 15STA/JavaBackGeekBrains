package ru.geekbrains.webui.pages.menu;

import ru.geekbrains.webui.pages.LoginPage;
import ru.geekbrains.webui.pages.base.BaseView;
import ru.geekbrains.webui.pages.base.SubMenuButtons;
import ru.geekbrains.webui.pages.enums.ContractorSubMenuButtons;

public class CountersPartiesSubMenu extends BaseView {

 //   @Step("click on sub menu button {buttons}")

    public BaseView clickSubMenuButton(SubMenuButtons buttons) {
        if (buttons instanceof ContractorSubMenuButtons) {
            switch ((ContractorSubMenuButtons) buttons) {
//                case CONTACT_PERSON_REQUEST:
//                    ((ContractorSubMenuButtons) buttons).getBy().click();
//                    return new LoginPage();                                 //!!!!!!!!!!!!!!!!!!!!!!
                default:
                    throw new IllegalArgumentException("Not implemented yet");
            }
        } else {
            throw new IllegalArgumentException("Incorrect button type");
        }
    }
}
