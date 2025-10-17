package ru.praktikum.services.scooter.api;

import io.restassured.response.Response;
import ru.praktikum.services.scooter.models.Courier;
import ru.praktikum.services.scooter.models.CourierCredentials;

import static io.restassured.RestAssured.given;

public class CourierAPI {
    private static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private static final String COURIER_ENDPOINT = "/api/v1/courier";
    private static final String LOGIN_ENDPOINT = "/api/v1/courier/login";

    public Response createCourier(Courier courier) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_ENDPOINT);
    }

    public Response loginCourier(CourierCredentials credentials) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(credentials)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    public Response deleteCourier(int courierId) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_ENDPOINT + "/" + courierId);
    }
}
