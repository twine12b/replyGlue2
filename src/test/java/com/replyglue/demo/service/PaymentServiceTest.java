package com.replyglue.demo.service;

import com.replyglue.demo.domain.Payment;
import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.RegistrationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class PaymentServiceTest {
    @Mock
    private RegistrationRepository registrationRepository;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentService service;

    private User testUser;

    @Before
    public void setUp() throws Exception {
        service = new PaymentService(registrationRepository);
        testUser = new User(
                "kidAccount",
                "k1dPwd456",
                "kid.rock@bbc.co.uk",
                "2008, 5, 9",
                1111111111111111L);
    }

    @Test
    public void testPaymentAmount_returnsBoolean() throws Exception{
        given(paymentService.isValidAmount(anyString())).willReturn(true);
        boolean validAmount = paymentService.isValidAmount("25.80");
        assertTrue(validAmount);

        validAmount = paymentService.isValidAmount("1225.80");
        assertTrue(validAmount);

        validAmount = service.isValidAmount("5.80");
        assertTrue(validAmount);

        validAmount = service.isValidAmount("5");
        assertFalse(validAmount);

        validAmount = service.isValidAmount("5.0");
        assertFalse(validAmount);
    }

    @Test
    public void testCheckCardIsRegistered_returnsBoolean() throws Exception{
//        Payment payment = new Payment(1111222233334444L, 28.00d);
        given(paymentService.checkCardExists(anyString())).willReturn(true);
        boolean cardExists = paymentService.checkCardExists("1111222233334444");
        assertTrue(cardExists);

        cardExists = service.checkCardExists("1111222233389444");
        assertFalse( cardExists);

        cardExists = service.checkCardExists("1111111111111111");
        assertFalse(cardExists);

//        assertTrue(cardExists);
//        assertFalse(cardExists);


    }

    @Test
    public void testMakeSuccessFullPayment_shouldReturnBoolean() throws Exception {
        Payment payment = new Payment(1111222233334444L, 28.00d);
        given(paymentService.makePayment(payment)).willReturn(true);

        boolean successfulPayment = paymentService.makePayment(payment);
        assertTrue(successfulPayment);
        successfulPayment = service.makePayment(payment);
        assertTrue(successfulPayment);
    }


}
