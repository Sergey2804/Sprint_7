package ru.praktikum.services.scooter.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.services.scooter.api.OrderAPI;
import ru.praktikum.services.scooter.models.Order;
import ru.praktikum.services.scooter.utils.TestDataGenerator;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class OrderCreationTest {
    private final OrderAPI orderAPI = new OrderAPI();
    private final List<String> color;

    public OrderCreationTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {Arrays.asList("BLACK")},
                {Arrays.asList("GREY")},
                {Arrays.asList("BLACK", "GREY")},
                {null}
        });
    }

    @Test
    @DisplayName("Создание заказа с разными цветами")
    public void testCreateOrderWithDifferentColors() {
        Order order = TestDataGenerator.createOrderWithColor(color);

        Response response = orderAPI.createOrder(order);

        response.then()
                .statusCode(201)
                .body("track", notNullValue());

        int track = response.then().extract().path("track");
        assertTrue("Track number should be positive", track > 0);
    }
}
