import order.OrderRequest;
import order.OrderClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;



@RunWith(Parameterized.class)
public class CreateOrderTest {

    private final List<String> color;
    private final int expectedCode;

    public CreateOrderTest(List<String> color, int expectedCode) {
        this.color = color;
        this.expectedCode = expectedCode;
    }

    @Parameterized.Parameters
    public static Object[][] getColorData() {
        return new Object[][]{
                {List.of("BLACK"), 201},
                {List.of("GREY"), 201},
                {List.of("BLACK", "GREY"), 201},
                {null, 201},
        };
    }

    @Test
    public void isOrderCreated () {
        OrderClient orderClient = new OrderClient();
        OrderRequest order = new OrderRequest("volodja", "putin", "Moscow Kremlin", 1, "89666666666", 5, "12.01.1999", "mir", List.of("BLACK"));
        order = order.changeColor(order, color);
        orderClient.create(order)
                .then().assertThat()
                .statusCode(201);
    }
}
