package com.replyglue.demo;

import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.RegistrationRepository;
import com.replyglue.demo.service.RegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment  = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureTestDatabase
public class CachingTest {

    @Autowired
    private RegistrationService service;

    @MockBean
    private RegistrationRepository repository;

    private User testUser;

    @Before
    public void setUp() throws Exception {
        testUser =  new User(
                "r1Chard",
                "passWord123",
                "rich@me.com",
                "1984, 5, 9",
                null);

    }

    @Test
    public void caching() throws Exception {
        given(repository.findUsersByUsername(anyString())).willReturn(testUser);

        service.findUsersByUsername("r1Chard");
        service.findUsersByUsername("r1Chard");

        verify(repository, times(1)).findUsersByUsername("r1Chard");

    }
}
