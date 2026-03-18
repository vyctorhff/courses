package br.course.elite.resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.junit.jupiter.api.Test;

import br.course.elite.domain.StarWarFan;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

@QuarkusTest
public class ExampleResourceTest {
    
    private static final String ROOT_PATH = "/examples";

    // ####################################################################
    // GET

    @Test
    void testGet() {
        given()
            .when().get(ROOT_PATH)
            .then()
                .statusCode(200)
                .body(is("0"));
    }

    @Test
    void testGet2() {
        given()
            .when()
                .get(ROOT_PATH + "/get2")
            .then()
                .body(is("0"));
    }

    @Test
    void testGet3() {
        String number = "3";

        given()
            .when()
                .get("%s/%s".formatted(ROOT_PATH, number))
            .then()
                .body(is(number));
    }

    // ####################################################################
    // POST

    @Test
    void testPost() {
        given()
            .contentType(ContentType.JSON)
            .when()
                .post(ROOT_PATH, Map.of())
            .then()
                .statusCode(200);
    }

    @Test
    void testPostObject() {
        StarWarFan fan = new StarWarFan("aaa", "III");

        given()
            .contentType(ContentType.JSON)
            .body(fan)
            .when()
                .post(ROOT_PATH + "/object")
            .then()
                .statusCode(200)
                .body(is("All Ok"));
    }

    @Test
    void testPostCheese() {
        var cookies = Map.of(
            "level", "2", 
            "created", "data tal e tal"
        );
        given()
            .contentType(ContentType.JSON)
            .param("age", 20)
            .cookies(cookies)
            .header("X-Cheese-Secret", "secret 1 2 3")
            .when()
                .post(ROOT_PATH + "/cheeses/typeee")
            .then()
                .statusCode(200);
    }

    // ####################################################################
    // DELETE

    @Test
    void testDelete() {
        ExtractableResponse<Response> response = given()
            .when()
                .get(ROOT_PATH + "/get2")
            .then()
                .extract();
        
        String numberBeforeStr = response.asString();
        Integer numberBefore = Integer.parseInt(numberBeforeStr);

        given()
            .when()
                .delete(ROOT_PATH)
            .then()
                .statusCode(202);
        
        given()
            .when()
                .get(ROOT_PATH + "/get2")
            .then()
                .body(is(String.valueOf(--numberBefore)));
    }
}
