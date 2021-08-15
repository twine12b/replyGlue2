package com.replyglue.demo;

import com.replyglue.demo.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private User testUser1;
    private User testUser2;

    @Before
    public void setUp() throws Exception {
        testUser1 = new User(
                "r1Chard", "passWord123",
                "rich@me.com", new GregorianCalendar(1984, 5, 9)
//                "rich@me.com", new GregorianCalendar(1984, 5, 9)
        );

        testUser2 = new User(
                "simonE1B", "seCret11",
                "simon@hotmail.co.uk", new GregorianCalendar(1996, 12, 12)
        );
    }

    @Test
    public void getAllUser_shouldReturn_listOfUsers() throws Exception{

        //act
        ResponseEntity<User> response = restTemplate.getForEntity("/users/r1Chard", User.class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getUsername()).isEqualTo("r1Chard");
        assertThat(response.getBody().getPassword()).isEqualTo("passWord123");
        assertThat(response.getBody().getEmail()).isEqualTo("rich@me.com");
        //TODO - fix test for date
//        assertThat(response.getBody().getDob()).isEqualTo(new GregorianCalendar(1984, 4, 9));
    }

    @Test
    public void testFailingRequestBody_shouldReturnStatus_400() throws Exception {
        URI uri = new URI("/users/");
        ResponseEntity<User> result = restTemplate.postForEntity(uri, testUser1, User.class);

        assertEquals(201, result.getStatusCode());
    }

}

