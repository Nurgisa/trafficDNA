package com.nurgisa.demo;

import com.nurgisa.demo.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@Slf4j
class DemoSpringApplicationTests {

    @Autowired
    UserController userController;
    TestRestTemplate restTemplate = new TestRestTemplate();

	@Value("${server.port}")
    private Integer port;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }


    @Test
    public void init() {
        HttpEntity<String> entity = new HttpEntity<String>(null, null);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/userinfo/100"),
                HttpMethod.GET, entity, String.class);

        String expected = "200";
        assertTrue("Response code", response.getStatusCodeValue() == 200);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
