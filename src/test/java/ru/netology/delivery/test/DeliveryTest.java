package ru.netology.delivery.test;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


class DeliveryTest {


    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");


        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        var name = DataGenerator.generateName("ru");
        var phone = DataGenerator.generatePhone("ru");
        var city = DataGenerator.generateCity("ru");


        $("span[data-test-id='city'] input").setValue(city.substring(0, 2));
        $$("div.menu div.menu-item").find(exactText(city)).click();
        // $x("//*[@data-test-id = \"date\"]//self::input").doubleClick().sendKeys(Keys.DELETE + planningDate1);
        $("span[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("span[data-test-id='date'] input.input__control").setValue(firstMeetingDate);

        $("span[data-test-id='name'] input").setValue(name);
        $("span[data-test-id='phone'] input").setValue(phone);

        $("label[data-test-id='agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("div[data-test-id='success-notification'] button").waitUntil(visible, 12000).click();
        //  $x("//*[@data-test-id = \"date\"]//self::input").doubleClick().sendKeys(Keys.DELETE + planningDate2);
        $("span[data-test-id='date'] input.input__control").sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        $("span[data-test-id='date'] input.input__control").setValue(secondMeetingDate);

        $$("button").find(exactText("Запланировать")).click();
        $("div[data-test-id='replan-notification'] button").waitUntil(visible, 12000).click();
        $("div.notification__content").waitUntil(text("Встреча успешно запланирована на " + secondMeetingDate),
                12000);
    }


}


