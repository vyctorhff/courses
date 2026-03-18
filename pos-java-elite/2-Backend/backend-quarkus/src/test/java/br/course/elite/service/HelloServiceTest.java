package br.course.elite.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class HelloServiceTest {

    @Inject
    private HelloService service;

    @InjectMock
    private OutroService outroService;

    @Test
    void testHello() {
        String msg = service.getHello();
        assertEquals("Hello in service", msg);
    }

    @Test
    void testOutro() {
        doNothing().when(outroService).outro();
        service.outro();
    }
}
