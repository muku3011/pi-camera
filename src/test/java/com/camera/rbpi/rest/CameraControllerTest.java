package com.camera.rbpi.rest;

import com.camera.rbpi.schema.CameraCommand;
import com.camera.rbpi.type.BadTestCase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CameraControllerTest {

    @LocalServerPort
    private int port;

    private static TestRestTemplate restTemplate;

    @BeforeAll
    static void init() {
        restTemplate=  new TestRestTemplate("TODO", "TODO");
    }

    @BadTestCase
    @DisplayName("Invalid quality value in the body: above maximum")
    public void invalidQualityValueMax() {
        String url = "http://localhost:" + port + "/camera/image";

        CameraCommand cameraCommand = new CameraCommand();
        cameraCommand.setQuality(101);

        ResponseEntity<String> response = restTemplate.postForEntity(url, cameraCommand, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"quality\":\"Quality maximum value should be 100\"}", response.getBody());
    }

    @BadTestCase
    @DisplayName("Invalid quality value in the body: below minimum")
    public void invalidQualityValueMin() {
        String url = "http://localhost:" + port + "/camera/image";

        CameraCommand cameraCommand = new CameraCommand();
        cameraCommand.setQuality(9);

        ResponseEntity<String> response = restTemplate.postForEntity(url, cameraCommand, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"quality\":\"Quality minimum value should be 10\"}", response.getBody());
    }

    @BadTestCase
    @DisplayName("Invalid timeout value in the body: above maximum")
    public void invalidTimeoutValueMax() {
        String url = "http://localhost:" + port + "/camera/image";

        CameraCommand cameraCommand = new CameraCommand();
        cameraCommand.setTimeout(10001);

        ResponseEntity<String> response = restTemplate.postForEntity(url, cameraCommand, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"timeout\":\"Timeout maximum value should be 10000\"}", response.getBody());
    }

    @BadTestCase
    @DisplayName("Invalid timeout value in the body: below minimum")
    public void invalidTimeoutValueMin() {
        String url = "http://localhost:" + port + "/camera/image";

        CameraCommand cameraCommand = new CameraCommand();
        cameraCommand.setTimeout(99);

        ResponseEntity<String> response = restTemplate.postForEntity(url, cameraCommand, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"timeout\":\"Timeout minimum value should be 100\"}", response.getBody());
    }
}
