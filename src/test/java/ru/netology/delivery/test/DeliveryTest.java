package ru.netology.delivery.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {
    private String date1 = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    private String date2 = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        val user = DataGenerator.Registration.generateUser("ru");

        val name = user.getName();
        val phone = user.getPhone();
        val city = user.getCity();
        System.out.println(name);
        System.out.println(phone);
        System.out.println(city);

        $("span[data-test-id='city'] input").setValue(city.substring(0, 2));
        $$("div.menu div.menu-item").find(exactText(city)).click();
        // $x("//*[@data-test-id = \"date\"]//self::input").doubleClick().sendKeys(Keys.DELETE + date1);
        $("span[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("span[data-test-id='date'] input.input__control").setValue(date1);

        $("span[data-test-id='name'] input").setValue(name);
        $("span[data-test-id='phone'] input").setValue(phone);

        $("label[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("div[data-test-id='success-notification'] button").waitUntil(visible, 12000).click();
        //  $x("//*[@data-test-id = \"date\"]//self::input").doubleClick().sendKeys(Keys.DELETE + date2);
        $("span[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("span[data-test-id='date'] input.input__control").setValue(date2);

        $$("button").find(exactText("Запланировать")).click();
        $("div[data-test-id='replan-notification'] button").waitUntil(visible, 12000).click();
        $("div.notification__content").waitUntil(text("Встреча успешно запланирована на " + date2),
                12000);
    }
}



