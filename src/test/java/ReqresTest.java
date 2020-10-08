import io.restassured.response.Response;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqresTest {

    @Test
    public void apiTest() {
        given()
        .when()
                .get("https://www.onliner.by/sdapi/kurs/api/bestrate?currency={curr}&type={type}", "USD", "nbrb")
        .then()
                .log().body()
                .statusCode(200)
                .body("amount", equalTo("2,6150"))
                .body("scale", equalTo(1));
    }

    @Test
    public void postTest() {
        Response responce = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\n" +
                        "    \"login\":\"aaa\",\n" +
                        "    \"password\":\"vvv\"\n" +
                        "}")
        .when()
                .post("https://www.onliner.by/sdapi/user.api/login")
        .then()
                .log().body()
                .statusCode(400)
                .body("errors[0].message", equalTo("Неверный логин или пароль"))
                .extract().response();
        System.out.println(responce.getBody().asString());
    }
}
