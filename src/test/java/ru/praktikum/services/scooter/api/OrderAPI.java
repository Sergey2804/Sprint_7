package ru.praktikum.services.scooter.api;

import io.restassured.response.Response;
import ru.praktikum.services.scooter.models.Order;

import static io.restassured.RestAssured.given;

public class OrderAPI {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private static final String ORDERS_ENDPOINT = "/api/v1/orders";

    public Response createOrder(Order order) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(order)
                .when()
                .post(ORDERS_ENDPOINT);
    }

    public Response getOrdersList() {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .when()
                .get(ORDERS_ENDPOINT);
    }
}
