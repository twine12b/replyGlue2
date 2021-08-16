package com.replyglue.demo.service;

import com.replyglue.demo.domain.Payment;
import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.PaymentRepository;
import com.replyglue.demo.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("paymentService")
public class PaymentService extends RegistrationService {


    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentService(RegistrationRepository registrationRepository, PaymentRepository paymentRepository) {
        super(registrationRepository);
        this.paymentRepository = paymentRepository;
    }


    public boolean makePayment(Payment payment) {
        if(creditcardIsValid(payment.getPayment_card()) &&
                isValidAmount(String.valueOf(payment.getAmount())))
        {
            if(checkCardExists(String.valueOf(payment.getPayment_card()))){
                if(savePayment(payment).isPresent()){
                    savePayment(payment);
                    return true;
                }
            }
        }
        return false;

//        return registrationService.creditcardIsValid(payment.getPayment_card()) &&
//                    isValidAmount(String.valueOf(payment.getAmount()))  ?
//                         checkCardExists(String.valueOf(payment.getPayment_card()))  ?
//                                 savePayment(payment).isPresent() ? true : false  // save file
//                         : false
//                : false;
    }

    public boolean isValidAmount(String amount) {
        String regex = "(.*\\d){3}";
        return doValidation(regex, amount);
    }

    public boolean checkCardExists(String cardNumber) {
        return findUserByCreditCard(cardNumber).isPresent();
    }

    @Transactional
    public Optional<Payment> savePayment(Payment payment){
        long paymentCount = paymentRepository.count();
        paymentRepository.saveAndFlush(payment);
        return paymentRepository.findById(paymentCount +1);
        // TODO - refactor
    }

}
