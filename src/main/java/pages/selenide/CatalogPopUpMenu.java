package pages.selenide;


import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

/**
 * Класс `CatalogPopUpMenu` наследуется от `AbstractPage` и предоставляет методы для взаимодействия с всплывающим меню каталога.
 *
 * @author Алейникова Александра
 */
public class CatalogPopUpMenu extends AbstractPage  {
    /**
     * Наводит курсор на раздел каталога с названием `categoriesName`.
     *
     * @param categoriesName Название раздела каталога.
     * @return Ссылка на текущий объект `CatalogPopUpMenu`.
     */
    @Step("Навожу на раздел {categoriesName}")
    public CatalogPopUpMenu hoverCursorOnElement(String categoriesName){
        $(By.xpath("//span[contains(text(),'" + categoriesName + "')]/..")).hover();
        return this;
    }
    /**
     * Нажимает на подраздел каталога с названием `categoriesSectionName`.
     *
     * @param categoriesSectionName Название подраздела каталога.
     * @return Объект страницы `ProductPage`.
     */
    @Step("Нажимаю на подраздел {categoriesSectionName}")
    public ProductPage clickOnElement(String categoriesSectionName){
        $(By.xpath("//div[@aria-level='2']//a[text() = '" + categoriesSectionName + "']")).click();
        return page(ProductPage.class);
    }
}
