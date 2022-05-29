package order;

import io.restassured.response.Response;
import lombok.Data;

import java.net.URL;

import static io.restassured.RestAssured.given;

@Data
public class OrderClient {

    private final String URL = "http://qa-scooter.praktikum-services.ru/";
    private final String ORDERS = "/api/v1/orders";

    public Response create(OrderRequest order) {
        return given()
                .header("Content-Type", "application/json")
                .baseUri(URL)
                .body(order)
                .when()
                .post(ORDERS);
    }

    public Response getOrders(){
        return given()
                .header("Content-Type", "application/json")
                .baseUri(URL)
                .when()
                .get(ORDERS);
    }
}
