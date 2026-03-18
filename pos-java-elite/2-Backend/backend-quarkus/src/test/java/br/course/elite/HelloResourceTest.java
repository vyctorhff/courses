package br.course.elite;

import org.junit.jupiter.api.Test;

import br.course.elite.service.HelloService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
public class HelloResourceTest {

    private static final String ROOT_PATH = "/hello";

    @InjectMock
    private HelloService service;

    @Test
    void testHello() {
        given()
          .when().get(ROOT_PATH)
          .then()
             .statusCode(200)
             .body(is("Hello from Quarkus REST"));
    }

    @Test
    void testHelloWithService() {
        String serviceReturn = "Hello in service";

        when(service.getHello())
            .thenReturn(serviceReturn);
        
        given()
            .when().get(ROOT_PATH + "/service")
            .then()
                .statusCode(200)
                .body(is(serviceReturn));
    }
}
