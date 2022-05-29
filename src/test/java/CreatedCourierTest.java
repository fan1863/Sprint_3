import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreatedCourierTest {

    private CourierClient courierClient;
    private int courierId;

    @Before
    public void setup() {
        courierClient = new CourierClient();
    }

    @After
    public void tearDown() {
       courierClient.delete(courierId);
    }

    @Test
    public void isCreatedWithCorrectParameters() {
        Courier courier = new Courier("timur", "1234", "saske");
        courierClient.create(courier)
                .then().log().all()
                .assertThat()
                .statusCode(201)
                .body("ok", equalTo(true));

        CourierCredentials courierCredentials = new CourierCredentials(courier);
        courierId = courierClient.getCourierId(courierCredentials);

    }

    @Test
    public void isNotCreatedWithoutLogin() {
        Courier courier = new Courier(null, "1234", "saske");

        courierClient.create(courier)
                .then().log().all()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));


    }

    @Test
    public void isNotCreatedWithoutPassword() {
        Courier courier = new Courier("alfredo1", null, "saske");

        courierClient.create(courier)
                .then().log().all()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));


    }

    @Test
    public void isNotCreatedWithExistingLogin() {
        Courier courier = new Courier("burak09", "1234", "saske");
        courierClient.create(courier);
        courierClient.create(courier)
                .then().log().all()
                .assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));

        CourierCredentials courierCredentials = new CourierCredentials(courier);
        courierId = courierClient.getCourierId(courierCredentials);


    }





}

