package ru.netology.delivery.data;


import com.github.javafaker.Faker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class DataGenerator {

    private static final Faker faker = new Faker(new Locale("ru"));

    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity(String locale) {

        String city = faker.address().cityName();
        return city;
    }
    // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
    // с помощью Faker, либо используя массив валидных городов и класс Random

    public static String generateName(String locale) {

        String name = faker.name().fullName();
        return name;
    }
    // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
    // использовать Faker

    public static String generatePhone(String locale) {

        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }
    // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
    // использовать Faker

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {

            return new UserInfo(generateName(locale), generatePhone(locale), generateCity(locale)
            );
        }


    }
}








