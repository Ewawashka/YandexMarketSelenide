package pages.selenide;


import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
/**
 * Класс `YandexMarketHomePage` наследуется от `AbstractPage` и предоставляет методы для работы с главной страницей Яндекс.Маркета.
 *
 * @author Алейникова Александра
 */
public class YandexMarketHomePage extends AbstractPage {
    /**
     * Нажимает на кнопку "Каталог" и переходит во всплывающее меню каталога.
     *
     * @return Объект страницы `CatalogPopUpMenu`.
     */
    @Step("Нажимаю на Каталог")
    public CatalogPopUpMenu openCatalog() {
        $(By.xpath("//*[contains(text(),'Каталог')]/..")).click();
        return page(CatalogPopUpMenu.class);
    }
}
