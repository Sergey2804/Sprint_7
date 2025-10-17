package ru.praktikum.services.scooter.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.services.scooter.models.Order;

import static io.restassured.RestAssured.given;
import static ru.praktikum.services.scooter.api.CourierAPI.BASE_URL;

public class OrderAPI {

    private static final String ORDERS_ENDPOINT = "/api/v1/orders";

    @Step("Создание заказа")
    public Response createOrder(Order order) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDERS_ENDPOINT);
    }

    @Step("Получение списка заказов")
    public Response getOrdersList() {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .when()
                .get(ORDERS_ENDPOINT);
    }
}
