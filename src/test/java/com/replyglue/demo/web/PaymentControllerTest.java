package com.replyglue.demo.web;

import com.google.gson.Gson;
import com.replyglue.demo.domain.Payment;
import com.replyglue.demo.domain.User;
import com.replyglue.demo.service.PaymentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    PaymentService paymentService;

    private User testUser;

    @Before
    public void setUp() {
        testUser = new User(
                "r1Chard", "passWord123",
                "rich@me.com", "1984, 5, 9", 1111222233334444L
        );
    }

    @Test
    public void testPaymentWhenBad_returns_statusCode_404() throws Exception {
        long paymentCardDetails = 4444111122223333L;
        double paymentAmount = 0.00;
        Payment payment = new Payment(paymentCardDetails, paymentAmount);

        given(paymentService.makePayment(payment))
                .willReturn(true);

        //Act
        mockMvc.perform(MockMvcRequestBuilders.post("/payments")
                        .content(new Gson().toJson(payment))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}
