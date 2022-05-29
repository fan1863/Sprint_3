package order;

import io.restassured.response.Response;
import lombok.Data;

import static io.restassured.RestAssured.given;

@Data
public class OrderClient {
    public Response create(OrderRequest order) {
        return given()
                .header("Content-Type", "application/json")
                .baseUri("http://qa-scooter.praktikum-services.ru/")
                .body(order)
                .when()
                .post("/api/v1/orders");
    }

    public Response getOrders(){
        return given()
                .header("Content-Type", "application/json")
                .baseUri("http://qa-scooter.praktikum-services.ru/")
                .when()
                .get("/api/v1/orders");
    }
}
