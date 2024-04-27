package pages.selenide;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс `CheckingProductPage` наследуется от `AbstractPage` и предоставляет методы для проверки элементов на странице товара.
 *
 * @author Алейникова Александра
 */
public class CheckingProductPage extends AbstractPage {
    /**
     * Ищет совпадение текста из списка производителей (`manufacturer`) в описании товара.
     *
     * @param manufacturer Список названий производителей.
     * @return true - если найдено хотя бы одно совпадение, иначе false.
     */
    public Boolean searchForTextInTheProductDescription(List<String> manufacturer) {
        $(By.xpath("//a[contains(text(),'Все товары')]")).shouldBe(Condition.exist, Duration.ofSeconds(30));
        SelenideElement allProductOfManufacturer = $(By.xpath("//a[contains(text(),'Все товары')]"));

        for (String s : manufacturer) {
            if (allProductOfManufacturer.getText().toLowerCase().contains(s.toLowerCase(Locale.ROOT))) {

                return true;

            }
        }
        Assertions.fail("Не найдено соответствия фильтру в разделе все товары");
        return false;
    }

}
