package pages.selenide;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

/**
 * Класс `ProductPage` наследуется от `AbstractPage` и предоставляет методы для работы со страницей товара.
 *
 * @author Алейникова Александра
 */

public class ProductPage extends AbstractPage {
    String xpath = "//article[@data-auto='searchOrganic']";
    /**
     * Проверяет соответствие названия страницы переданному подразделу.
     *
     * @param catalogCategoriesName Название подраздела.
     * @return Ссылка на текущий объект `ProductPage`.
     */
    @Step("Проверяю соответствие страницы к переданному подразделу{catalogCategoriesName}")
    public ProductPage getPageTitleTextAndCheckCompliance(String catalogCategoriesName){
        if(($(By.xpath("//h1[@data-auto='title']")).getText()).contains(catalogCategoriesName)){
            return this;
        }
       Assertions.fail("Не удалось найти текст "+catalogCategoriesName+"в заголовке страницы");
        return this;
    }
    /**
     * Устанавливает фильтр по производителю.
     *
     * @param manufacturer Список названий производителей.
     * @return Ссылка на текущий объект `ProductPage`.
     */
    @Step("Устанавливаю фильтр по производителю {manufacturer}")
    public ProductPage setManufacturerFilters(List<String> manufacturer){
        for (String s :
                manufacturer) {
            $(By.xpath("//div[contains(@data-zone-data,'Производитель')]//fieldset//div[contains(@data-zone-data,'" + s + "')]")).click();
        }
        return this;
    }
    /**
     * Ждет загрузки результатов поиска.
     *
     * @return Ссылка на текущий объект `ProductPage`.
     */
    @Step("Жду результаты поиска")
    public ProductPage waitSearchResults(){
       $(By.xpath(xpath)).shouldBe(Condition.exist, Duration.ofSeconds(30));
        return this;
    }
    /**
     * Проверяет, что в результатах поиска на всех страницах присутствуют только товары от указанного производителя.
     *
     * @param manufacturer Список названий производителей.
     * @return Ссылка на текущий объект `ProductPage`.
     */
    @Step("Проверяю что в результатах поиска на всех страницах присутствует только товары от производителя {manufacturer} ")
    public ProductPage checkingFilteringByManufacturer(List<String> manufacturer) {
        Boolean allProductsComply = true;
        List<String> url = new ArrayList<>();
        url.add(getWebDriver().getCurrentUrl());
        int lastListSize = 0;
        for (int i = 0; i < 100; i++) {
            ElementsCollection elementsCollection = $$(By.xpath(xpath+"//h3"));
            int currentElementCollection = elementsCollection.size();
            if (currentElementCollection == lastListSize) {
                break;
            }
            for (int j = lastListSize ; j < currentElementCollection ; j++) {
                String getProductText = elementsCollection.get(j).getText();
                boolean found = false;
                for (String manufacturerItem : manufacturer
                ) {
                    if (getProductText.toLowerCase().contains(manufacturerItem.toLowerCase(Locale.ROOT))) {
                        found = true;
                        break;
                    }
                }
                if(!found){
                    elementsCollection.get(j).$(By.xpath("../..")).click();

                    System.out.println(getProductText + " заходим на страницу ноутбука не соответствующего фильтру");
                    allProductsComply = page(CheckingProductPage.class).searchForTextInTheProductDescription(manufacturer);
                    Selenide.open(url.get(url.size()-1));
                }
                if (!allProductsComply) {
                    System.out.println("найдено не соответствие фильтру");
                    break;
                }
                lastListSize++;
            }

            elementsCollection.get(elementsCollection.size()-1).hover();
            url.add(getWebDriver().getCurrentUrl());
            if (!allProductsComply) {
                Assertions.fail("Найден продукт не соответствующий выставленным фильтрам {manufacturer}");
                break;
            }
        }
        return this;
    }
    }

