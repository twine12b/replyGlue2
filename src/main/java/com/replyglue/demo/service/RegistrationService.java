package com.replyglue.demo.service;

import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.RegistrationRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class RegistrationService extends UserValidationService {

    private final RegistrationRepository registrationRepository;

    @Cacheable("findUsersByUsername")
    public User findUsersByUsername(String username) {
        return registrationRepository.findUsersByUsername(username);
        }

    @Cacheable("isUserValid")
    public boolean isUserValid(User user) {
        boolean isValid;

        //TODO - refactor using combinator pattern

        isValid = usernameIsValid(user.getUsername());
        isValid = isValid ? passwordIsValid(user.getPassword()) : false;
        isValid = isValid ? emailIsValid(user.getEmail()) : false;

        if(user.getCard() !=null){
            isValid = isValid  ? creditcardIsValid(user.getCard()) : false;
        }

        return isValid;
    }

    public boolean isRegisteredUser(String username) {
        return registrationRepository.findUsersByUsername(username) == null? false : true;
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
}
