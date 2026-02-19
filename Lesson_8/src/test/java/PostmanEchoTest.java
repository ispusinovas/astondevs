import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {

    @BeforeAll
    static void setup() {
        RestAssured.config = RestAssured.config().encoderConfig(
                EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)
        );
    }

    @Test
    void testGetRequest() {
        given()
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get("https://postman-echo.com/get")
                .then()
                .statusCode(200)
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"));
    }

    @Test
    void testPostRawText() {
        given()
                .contentType("text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."));
    }

    @Test
    void testPostFormData() {
        given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post("https://postman-echo.com/post")
                .then()
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }

    @Test
    void testPutRequest() {
        given()
                .contentType("text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .put("https://postman-echo.com/put")
                .then()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."));
    }

    @Test
    void testPatchRequest() {
        given()
                .contentType("text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .patch("https://postman-echo.com/patch")
                .then()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."));
    }

    @Test
    void testDeleteRequest() {
        given()
                .contentType("text/plain")
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete("https://postman-echo.com/delete")
                .then()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."));
    }
}
