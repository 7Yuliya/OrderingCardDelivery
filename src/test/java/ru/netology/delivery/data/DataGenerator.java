package ru.netology.delivery.data;


import com.github.javafaker.Faker;


import java.util.Locale;
import java.util.Random;


public class DataGenerator {


    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            Faker faker = new Faker(new Locale(locale));

            String[] cities = {"Казань", "Москва", "Ставрополь", "Хабаровск"};
            Random rand = new Random();

            return new UserInfo(faker.name().fullName(),
                    faker.phoneNumber().phoneNumber(),
                    cities[rand.nextInt(cities.length)]
            );
        }
    }

}







