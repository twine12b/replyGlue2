package com.replyglue.demo.service;

import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Primary
public class RegistrationService extends UserValidationService {

    @Autowired
//    @Qualifier("registrationService")
    private final RegistrationRepository registrationRepository;

    @Cacheable("findUsersByUsername123")
    public User findUsersByUsername(String username) {
        return registrationRepository.findUsersByUsername(username);
        }

    @Cacheable("isUserValid")
    public boolean isUserValid(User user) {
        boolean isValid;

        //TODO - refactor using combinator pattern

        isValid =
                usernameIsValid(user.getUsername())
                        && passwordIsValid(user.getPassword())
                        && emailIsValid(user.getEmail());

        if(user.getCard() !=null){
            isValid = isValid && creditcardIsValid(user.getCard());
        }

        return isValid;
    }

    public boolean isRegisteredUser(String username) {
        return registrationRepository.findUsersByUsername(username) != null;
    }

    public List<User> filterUsersByCreditCard(String yesNoAll) {
        switch(yesNoAll) {
            case "Optional[yes]" : return registrationRepository.findUsersWithCreditCard();
            case "Optional[no]"  : return registrationRepository.findUsersWithOutCreditCard();
            default: return registrationRepository.findAll();
        }
    }

    public boolean registerUser(User newUser) {
        boolean isChecked = false;

        if(isUserValid(newUser)
                && !isRegisteredUser(newUser.getUsername()) )
        {
            registrationRepository.save(newUser);
            isChecked = true;
        }
        // TODO - check age - refactor code - use combinator pattern

        return isChecked;
    }

    public Optional<User> findUserByCreditCard(String cardNumber) {
        return registrationRepository.findUserByCreditCard(cardNumber);
    }

    public Optional<User> findUserByCreditCard2(String cardNumber) {
        return registrationRepository.findUserByCreditCard(cardNumber);
    }
}
