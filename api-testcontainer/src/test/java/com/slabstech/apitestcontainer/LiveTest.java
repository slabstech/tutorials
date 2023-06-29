package com.slabstech.apitestcontainer;

import static java.util.concurrent.TimeUnit.MINUTES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;


@Testcontainers
@SpringBootTest(classes = ApiTestcontainerApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class LiveTest {

    private final BlockingQueue<String> output = new LinkedBlockingQueue<>();

  //  @Container
    //private static final KafkaContainer KAFKA = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"));



    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {

    }

    @Test
    void givenInputMessages_whenPostToEndpoint_thenWordCountsReceivedOnOutput() throws Exception {
//sachin@com.mail @scchin sachin@mail
        String eventStream = "mail@sachin.com ";
        postMessage(eventStream);

        startOutputTopicConsumer();


/*
        // assert correct count from REST service
        assertThat(getCountFromRestServiceFor("mail@sachin.com")).isEqualTo(1);
        assertThat(getCountFromRestServiceFor("sachin@com.mail")).isEqualTo(1);

        assertThat(getCountFromRestServiceFor("@scchin")).isEqualTo(0);

*/
       /* postMessage("another test message");

        // assert correct counts from output topic
        assertThat(output.poll(2, MINUTES)).isEqualTo("another:1");
        assertThat(output.poll(2, MINUTES)).isEqualTo("test:2");
        assertThat(output.poll(2, MINUTES)).isEqualTo("message:2");

        // assert correct count from REST service
        assertThat(getCountFromRestServiceFor("another")).isEqualTo(1);
        assertThat(getCountFromRestServiceFor("test")).isEqualTo(2);
        assertThat(getCountFromRestServiceFor("message")).isEqualTo(2);

        */
        //  output.clear();
    }

    private void postMessage(String message) {
        HttpEntity<String> request = new HttpEntity<>(message, new HttpHeaders());
        restTemplate.postForEntity(createURLWithPort("/message"), request, null);
    }

    private int getCountFromRestServiceFor(String word) {
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/count/" + word),
                HttpMethod.GET, entity, String.class
        );
        return Integer.parseInt(Objects.requireNonNull(response.getBody()));
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    private void createConsumer() {

    }

    private void startOutputTopicConsumer() {

    }


}

