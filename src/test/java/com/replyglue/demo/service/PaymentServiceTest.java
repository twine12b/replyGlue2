package com.replyglue.demo.service;

import com.replyglue.demo.domain.Payment;
import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.PaymentRepository;
import com.replyglue.demo.repository.RegistrationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class PaymentServiceTest {
    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentService paymentService;

    private User testUser;

    @Before
    public void setUp() {
//        paymentService = new PaymentService(registrationService, paymentRepository);
        paymentService = new PaymentService(registrationRepository, paymentRepository);
        testUser = new User(
                "kidAccount",
                "k1dPwd456",
                "kid.rock@bbc.co.uk",
                "2008, 5, 9",
                1111111111111111L);
    }

    @Test
    public void testPaymentAmount_returnsBoolean() {
        //Given
        boolean validAmount = paymentService.isValidAmount("25.80");
        assertTrue(validAmount);

        validAmount = paymentService.isValidAmount("1225.80");
        assertTrue(validAmount);

        validAmount = paymentService.isValidAmount("5.80");
        assertTrue(validAmount);

        validAmount = paymentService.isValidAmount("5");
        assertFalse(validAmount);

        validAmount = paymentService.isValidAmount("5.0");
        assertFalse(validAmount);
    }

    @Test
    public void testCheckCardIsRegistered_returnsBoolean() {
//        given(paymentService.checkCardExists(anyString())).willReturn(true);
        boolean cardExists = paymentService.checkCardExists("1111222233334444");
        assertFalse(cardExists);

        cardExists = paymentService.checkCardExists("1111222233389444");
        assertFalse( cardExists);
    }

    @Test
    public void testMakeSuccessFullPayment_shouldReturnBoolean() {
        //Given
        Payment payment = new Payment(1234123412341234L, 28.00d);
        boolean successfulPayment = paymentService.makePayment(payment);

        assertTrue(successfulPayment);

    }


}
