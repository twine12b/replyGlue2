package com.replyglue.demo.web;

import com.replyglue.demo.domain.Payment;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@AllArgsConstructor
public class PaymentController {

    @PostMapping
    public ResponseEntity makePayment(@RequestBody Payment payment) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
