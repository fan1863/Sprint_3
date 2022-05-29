import courier.Courier;
import courier.CourierClient;
import courier.CourierCredentials;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;


public class LoginCourierTest {

    private CourierClient courierClient;
    private int courierId;
    private Courier courier;
    private CourierCredentials courierCredentials;

    @Before
    public void setup() {
        courierClient = new CourierClient();
        courier = new Courier("Neo", "1234", "jonny");
        courierClient.create(courier);
        courierCredentials = new CourierCredentials(courier);
        courierId = courierClient.getCourierId(courierCredentials);
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

    @Test
    public void isLogin() {
        courierClient.login(courierCredentials)
                .then().log().all().assertThat()
                .statusCode(200)
                .body("id", equalTo(courierId));

    }

    @Test
    public void isNotCorrectLogin() {
        courierCredentials.setLogin("Hasan");
        courierClient.login(courierCredentials)
                .then().log().all()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));


    }

    @Test
    public void isNotCorrectPassword() {
        courierCredentials.setPassword("152434");
        courierClient.login(courierCredentials)
                .then().log().all()
                .assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));


    }

    @Test
    public void loginWithNullLogin() {

        courierCredentials = new CourierCredentials(null, courier.getPassword());
        courierClient.login(courierCredentials)
                .then().log().all()
                .assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));


    }

}
