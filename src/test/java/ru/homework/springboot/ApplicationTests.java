package ru.homework.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;


    private static final GenericContainer<?> myDevapp = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);

    private static final GenericContainer<?> myProdapp = new GenericContainer<>("prodapp:latest ")
            .withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        myDevapp.start();
        myProdapp.start();
    }

    @Test
    void testByDev() {
        String dev = "Current profile is Dev";
        ResponseEntity<String> forEntityDev = restTemplate.getForEntity("http://localhost:" + myDevapp.getMappedPort(8080) + "/profile", String.class);
        Assertions.assertEquals(dev, forEntityDev.getBody());
    }

    @Test
    void testByProd() {
        String prod = "Current profile is production";
        ResponseEntity<String> forEntityProd = restTemplate.getForEntity("http://localhost:" + myProdapp.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals(prod, forEntityProd.getBody());
    }

}
