package com.replyglue.demo.service;

import com.replyglue.demo.domain.Payment;
import com.replyglue.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentService(RegistrationService registrationService, PaymentRepository paymentRepository) {
        this.registrationService = registrationService;
        this.paymentRepository = paymentRepository;
    }

    public boolean makePayment(Payment payment) {
        return registrationService.creditcardIsValid(payment.getPayment_card()) &&
                    isValidAmount(String.valueOf(payment.getAmount()))  ?
                         checkCardExists(String.valueOf(payment.getPayment_card()))  ?
                                 savePayment(payment).isPresent() ? true : false  // save file
                         : false
                : false;
    }

    public boolean isValidAmount(String amount) {
        String regex = "(.*\\d){3}";
        return registrationService.doValidation(regex, amount);
    }

    public boolean checkCardExists(String cardNumber) {
        return registrationService.findUserByCreditCard(cardNumber).isPresent() ? true : false;
    }

    public Optional<Payment> savePayment(Payment payment){
        long paymentCount = paymentRepository.count();
        paymentRepository.saveAndFlush(payment);
        return paymentRepository.findById(paymentCount +1);
    }


}
