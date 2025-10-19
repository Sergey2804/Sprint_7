package ru.praktikum.services.scooter.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.services.scooter.models.Courier;
import ru.praktikum.services.scooter.models.CourierCredentials;

import static io.restassured.RestAssured.given;

public class CourierAPI {
    static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private static final String COURIER_ENDPOINT = "/api/v1/courier";
    private static final String LOGIN_ENDPOINT = "/api/v1/courier/login";

    @Step("Создание курьера")
    public Response createCourier(Courier courier) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_ENDPOINT);
    }

    @Step("Авторизация курьера")
    public Response loginCourier(CourierCredentials credentials) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(credentials)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    @Step("Удаление курьера с ID: {courierId}")
    public Response deleteCourier(int courierId) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_ENDPOINT + "/" + courierId);
    }
}
