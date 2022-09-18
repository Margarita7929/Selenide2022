import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {



        @Test
        void shouldTest() {

            open("http://0.0.0.0:9999");
            SelenideElement form = $("#root");
            form.$("[data-test-id=city] input").setValue("Москва");
            form.$("[data-test-id='date'] input").doubleClick().sendKeys("30.09.2022");
            form.$("[data-test-id=name] input").setValue("Быковская Маргарита");
            form.$("[data-test-id=phone] input").setValue("+79094653782");
            form.$("[data-test-id=agreement]").click();
            form.$$("button").find(exactText("Забронировать")).click();


            form.$("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
           form.$x(".//div[@class='notification__title']").should(text("Успешно!"));
            form.$x(".//div[@class='notification__content']").should(text("Встреча успешно забронирована на "));


    }}



