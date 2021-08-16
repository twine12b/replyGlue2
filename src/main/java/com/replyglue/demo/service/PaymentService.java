package com.replyglue.demo.service;

import com.replyglue.demo.domain.Payment;
import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.PaymentRepository;
import com.replyglue.demo.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService extends RegistrationService {

    private RegistrationRepository registrationRepository;

    public PaymentService(RegistrationRepository registrationRepository) {
        super(registrationRepository);
    }

    public boolean makePayment(Payment payment) {
        return creditcardIsValid(payment.getPayment_card()) &&
                    isValidAmount(String.valueOf(payment.getAmount()))
                         ? true  // check card exists
                : false;
    }

    public boolean isValidAmount(String amount) {
        String regex = "(.*\\d){3}";
        return doValidation(regex, amount);
    }

    public boolean checkCardExists(String cardNumber) {
        User user =  findUsersByUsername("simonE1B");
        System.out.println(user);

//        return user.getUsername().equals(null) ? false : true;
        return false;
    }

}
