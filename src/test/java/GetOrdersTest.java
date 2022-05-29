import order.OrderClient;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrdersTest {

    @Test
    public void getOrders() {
        OrderClient orderClient = new OrderClient();
        orderClient.getOrders()
                .then().assertThat()
                .statusCode(200)
                .and()
                .body("orders", notNullValue());
    }
}
