package com.replyglue.demo.web;

import com.replyglue.demo.domain.Payment;
import com.replyglue.demo.domain.User;
import com.replyglue.demo.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(value = "/payments", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity makePayment(@RequestBody Payment payment) {
        return paymentService.makePayment(payment)?
                new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/payments/findcard")
    public ResponseEntity findUserByCreditCard(@RequestParam("number") String card_details){
        System.out.println(card_details + "  :   " +paymentService.checkCardExists(card_details));
        return paymentService.checkCardExists(card_details)?
              new ResponseEntity("Payment Received", HttpStatus.CREATED) : new ResponseEntity("Card Number Not Valid", HttpStatus.NOT_FOUND);
    }

}
