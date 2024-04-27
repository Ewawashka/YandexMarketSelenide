package ru.market.yandex;

import allure.selenide.CustomAllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;

/**
 * @author Алейникова Александра
 */
public class BaseTest {
    @BeforeAll
    public static void setup(){
        SelenideLogger.addListener("AllureSelenide",new CustomAllureSelenide().screenshots(true).savePageSource(true));
    }
}


