package ru.praktikum.services.scooter.utils;

import ru.praktikum.services.scooter.models.Courier;
import ru.praktikum.services.scooter.models.Order;

import java.util.List;
import java.util.UUID;

public class TestDataGenerator {

    public static Courier createRandomCourier() {
        String login = "courier_" + UUID.randomUUID().toString().substring(0, 8);
        String password = "password_" + UUID.randomUUID().toString().substring(0, 8);
        String firstName = "FirstName_" + UUID.randomUUID().toString().substring(0, 8);

        return new Courier(login, password, firstName);
    }

    public static Order createOrderWithColor(List<String> color) {
        Order order = new Order();
        order.setFirstName("Иван");
        order.setLastName("Иванов");
        order.setAddress("Москва, ул. Ленина, 1");
        order.setMetroStation("5");
        order.setPhone("+79991234567");
        order.setRentTime(3);
        order.setDeliveryDate("2024-12-31");
        order.setComment("Комментарий");
        order.setColor(color);

        return order;
    }
}
