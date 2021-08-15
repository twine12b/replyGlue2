package com.replyglue.demo.service;

import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.RegistrationRepository;
import com.replyglue.demo.util.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.GregorianCalendar;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;

    private RegistrationService registrationService;
    private User testUser;

    @Before
    public void setUp() throws Exception {
        registrationService = new RegistrationService(registrationRepository);
        testUser = new User(
                "r1Chard",
                "passWord123",
                "rich@me.com",
                new GregorianCalendar(1984, 5, 9));
    }

    @Test
    public void findUsersByUsername_shouldReturn_userInfo() {
        given(registrationRepository.findUsersByUsername("r1Chard")).willReturn(testUser);

        User user = registrationService.findUsersByUsername("r1Chard");

        assertThat(user.getUsername()).isEqualTo("r1Chard");
        assertThat(user.getPassword()).isEqualTo("passWord123");
        assertThat(user.getEmail()).isEqualTo("rich@me.com");
    }

    @Test(expected = UserNotFoundException.class)
    public void findUsersByUsername_whenUserNotFound() throws Exception {
        given(registrationRepository.findUsersByUsername("r1Chard")).willReturn(null);

        registrationService.findUsersByUsername("r1Chard");
    }

    @Test
    public void test_isUserValid_shouldReturn_StatusCode() throws Exception {
        given(registrationService.isUserValid(testUser));
    }
}
