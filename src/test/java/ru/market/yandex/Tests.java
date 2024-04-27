package ru.market.yandex;


import io.qameta.allure.Feature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pages.selenide.*;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static helpers.Properties.testsProperties;

/**
 * @author Алейникова Александра
 */
public class Tests  extends BaseTest{
    @Feature("Выполнение задания 2.1 по YandexMarket" )
    @DisplayName("Прохождение чек листа YandexMarketSelenide с помощью PO")
    @ParameterizedTest(name="{displayName}: {arguments}")
    @MethodSource("helpers.DataProvider#setParameters")
    public void yandexMarketTests( String catalogSectionName,
                                  String catalogCategoriesName,
                                  List<String> manufactures){
        open(testsProperties.yandexMarketUrl(),YandexMarketHomePage.class)
        .openCatalog()
                .hoverCursorOnElement(catalogSectionName)
                .clickOnElement(catalogCategoriesName)
                .getPageTitleTextAndCheckCompliance(catalogCategoriesName)
                .setManufacturerFilters(manufactures)
                .waitSearchResults()
                .checkingFilteringByManufacturer(manufactures);
    }
}
