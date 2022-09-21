import com.codeborne.selenide.Condition;
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

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    void shouldTest() {

        open("http://0.0.0.0:9999");
        String planningDate = generateDate(3);
        SelenideElement form = $("#root");
        form.$("[data-test-id=city] input").setValue("Москва");
        form.$("[data-test-id='date'] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id='date'] input").setValue(planningDate);
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79094653782");
        form.$("[data-test-id=agreement]").click();
        form.$$("button").find(exactText("Забронировать")).click();


        form.$("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);


    }


}



