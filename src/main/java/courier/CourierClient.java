package courier;

import io.restassured.response.Response;
import lombok.Data;

import static io.restassured.RestAssured.given;

@Data

public class CourierClient {
    public Response create(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .baseUri("http://qa-scooter.praktikum-services.ru/")
                .body(courier)
                .when()
                .post("/api/v1/courier");
    }

    public int getCourierId(CourierCredentials courierCredentials) {
        return given()
                .header("Content-Type", "application/json")
                .baseUri("http://qa-scooter.praktikum-services.ru/")
                .body(courierCredentials)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .extract()
                .path("id");

    }

    public void delete(int courierId) {
        given()
                .header("Content-Type", "application/json")
                .baseUri("http://qa-scooter.praktikum-services.ru/")
                .pathParam("courierId", courierId)
                .when()
                .delete("/api/v1/courier/{courierId}");
    }

    public Response login(CourierCredentials courierCredentials) {
        return given()
                .header("Content-Type", "application/json")
                .baseUri("http://qa-scooter.praktikum-services.ru/")
                .body(courierCredentials)
                .when()
                .post("/api/v1/courier/login");
    }
}
