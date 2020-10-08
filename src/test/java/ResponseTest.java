import io.restassured.response.Response;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResponseTest {

    @Test
    public void listUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void singleUser() {
        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void singleUserNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .log().body()
                .statusCode(404);
    }

    @Test
    public void listResource() {
        given()
                .when()
                .get("https://reqres.in/api/unknown")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void singleResource() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/2")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void singleResourceNotFound() {
        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .log().body()
                .statusCode(404);
    }
    @Test
    public void create() {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\"name\": \"morpheus\", \"job\": \"leader\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().response();
    }

    @Test
    public void update() {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\"name\": \"morpheus\", \"job\": \"zion resident\"}")
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
    }

    @Test
    public void updateUsingPatch() {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\"name\": \"morpheus\", \"job\": \"zion resident\"}")
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
    }

    @Test
    public void delete() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().body()
                .statusCode(204);
    }

    @Test
    public void registerSuccessful() {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
    }

    @Test
    public void registerUnsuccessful() {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().body()
                .statusCode(400)
                .extract().response();
    }

    @Test
    public void loginSuccessful() {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().body()
                .statusCode(200)
                .extract().response();
    }

    @Test
    public void loginUnsuccessful() {
        Response response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().body()
                .statusCode(400)
                .extract().response();
    }

    @Test
    public void delayedResponse() {
        given()
                .when()
                .get("https://reqres.in/api/users?delay=3")
                .then()
                .log().body()
                .statusCode(200);
    }


}
