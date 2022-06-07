import order.OrderClient;
import order.OrderResponse;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersTest {

    @Test
    public void getOrders() {
        OrderClient orderClient = new OrderClient();
        OrderResponse orderResponse = orderClient.getOrders()
                .body().as(OrderResponse.class);
        MatcherAssert.assertThat(orderResponse, notNullValue());

        orderClient.getOrders()
                .then().assertThat()
                .statusCode(200);
    }
}
