package courier;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor


public class CourierCredentials {


    private String login;
    private String password;

    public CourierCredentials(Courier courier) {
        this.login = courier.getLogin();
        this.password = courier.getPassword();
    }
}
