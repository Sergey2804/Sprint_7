package ru.praktikum.services.scooter.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import ru.praktikum.services.scooter.api.OrderAPI;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class OrderListTest {
    private final OrderAPI orderAPI = new OrderAPI();

    @Test
    @DisplayName("В теле ответа возвращается список заказов")
    public void testGetOrdersListReturnsOrders() {
        Response response = orderAPI.getOrdersList();

        response.then()
                .statusCode(200)
                .body("orders", notNullValue());

        assertNotNull("Orders list should not be null",
                response.then().extract().path("orders"));
    }
}
