package com.replyglue.demo.repository;

import com.replyglue.demo.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegistrationRepositoryTest {

    @Autowired
    private RegistrationRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    private User testUser;

    @Before
    public void setUp() throws Exception {
        testUser = new User(
                "JPAtestUser1",
                "passWord123",
                "rich@me.com",
                new GregorianCalendar(1984, 5, 9));
    }

    @Test
    public void getUserByUsername_shouldReturn_userInfo() throws Exception{
        User savedUser = entityManager.persistFlushFind(testUser);
        User user = repository.findUsersByUsername("JPAtestUser1");

        assertThat(user.getUsername()).isEqualTo(savedUser.getUsername());
        assertThat(user.getPassword()).isEqualTo(savedUser.getPassword());
        assertThat(user.getEmail()).isEqualTo(savedUser.getEmail());
        assertThat(user.getDob()).isEqualTo(savedUser.getDob());
    }
}
