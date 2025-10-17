package ru.praktikum.services.scooter.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.services.scooter.api.CourierAPI;
import ru.praktikum.services.scooter.models.Courier;
import ru.praktikum.services.scooter.models.CourierCredentials;
import ru.praktikum.services.scooter.utils.TestDataGenerator;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class CourierCreationTest {
    private CourierAPI courierAPI;
    private Courier courier;
    private int courierId;

    @Before
    public void setUp() {
        courierAPI = new CourierAPI();
        courier = TestDataGenerator.createRandomCourier();
    }

    @After
    public void tearDown() {
        if (courierId != 0) {
            courierAPI.deleteCourier(courierId)
                    .then()
                    .statusCode(200);
        }
    }

    @Test
    @DisplayName("Курьера можно создать")
    public void testCourierCanBeCreated() {
        Response response = courierAPI.createCourier(courier);

        response.then()
                .statusCode(201)
                .body("ok", equalTo(true));

        courierId = getCourierId();
        assertTrue("Courier ID should be positive", courierId > 0);
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void testCannotCreateDuplicateCourier() {
        courierAPI.createCourier(courier)
                .then()
                .statusCode(201);

        courierAPI.createCourier(courier)
                .then()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        courierId = getCourierId();
    }

    @Test
    @DisplayName("Создание курьера без логина возвращает ошибку")
    public void testCreateCourierWithoutLogin() {
        courier.setLogin(null);

        courierAPI.createCourier(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без пароля возвращает ошибку")
    public void testCreateCourierWithoutPassword() {
        courier.setPassword(null);

        courierAPI.createCourier(courier)
                .then()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    private int getCourierId() {
        return courierAPI.loginCourier(
                        new CourierCredentials(courier.getLogin(), courier.getPassword()))
                .then()
                .statusCode(200)
                .extract()
                .path("id");
    }
}