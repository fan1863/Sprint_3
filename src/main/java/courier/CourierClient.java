package courier;

import io.restassured.response.Response;
import lombok.Data;
import org.apache.http.client.utils.URIUtils;

import java.net.URL;

import static io.restassured.RestAssured.given;

@Data

public class CourierClient {

    private final String URL = "http://qa-scooter.praktikum-services.ru/";
    private  final String COURIER = "/api/v1/courier";
    public Response create(Courier courier) {
        return given()
                .header("Content-Type", "application/json")
                .baseUri(URL)
                .body(courier)
                .when()
                .post(COURIER);
    }

    public int getCourierId(CourierCredentials courierCredentials) {
        return given()
                .header("Content-Type", "application/json")
                .baseUri(URL)
                .body(courierCredentials)
                .when()
                .post(COURIER+"/login")
                .then()
                .extract()
                .path("id");

    }

    public void delete(int courierId) {
        given()
                .header("Content-Type", "application/json")
                .baseUri(URL)
                .pathParam("courierId", courierId)
                .when()
                .delete(COURIER+"/{courierId}");
    }

    public Response login(CourierCredentials courierCredentials) {
        return given()
                .header("Content-Type", "application/json")
                .baseUri(URL)
                .body(courierCredentials)
                .when()
                .post(COURIER+"/login");
    }
}
