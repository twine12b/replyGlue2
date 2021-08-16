package com.replyglue.demo.service;

import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.RegistrationRepository;
import com.replyglue.demo.util.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class RegistrationServiceTest {

    @Mock
    private RegistrationRepository registrationRepository;

    @InjectMocks
    private RegistrationService registrationService;
    private User testUser;

    @Before
    public void setUp() throws Exception {
        registrationService = new RegistrationService(registrationRepository);
        testUser = new User(
                "r1Chard",
                "passWord123",
                "rich@me.com",
                "2008, 5, 9",
                null);
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
        given(registrationRepository.findUsersByUsername(anyString())).willThrow(new UserNotFoundException());

        registrationService.findUsersByUsername("r1Chard");
    }

    @Test
    public void test_isUserValid_shouldReturn_boolean() throws Exception {
        given(registrationRepository.findUsersByUsername(anyString())).willReturn(testUser);
        User user = registrationService.findUsersByUsername("tomHanks");

        boolean isValid = registrationService.isRegisteredUser("r1Chard");

        assertTrue(isValid);
    }

    //TODO - sort dob and deserialization
//    @Test
//    public void test_userYoungerThan18_shouldReturn_statusCode403() {
//        boolean isOfAge = registrationService.isAdult(testUser);
//    }

    @Test
    public void test_isAlreadyRegistered_returns_boolean(){
        given(registrationService.findUsersByUsername(anyString())).willReturn(testUser);
        assertTrue(registrationService.isRegisteredUser("r1Chard"));
    }

    @Test
    public void testSaveUser_whenValidateSuccess()throws Exception {
        User newUser = new User("newUser1","newUser1Password","newuser@outlook.com", "2001-12-17");
        boolean isRegistered = registrationService.registerUser(newUser);

        assertTrue(isRegistered);
    }
}
