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
        User user = registrationRepository.findUsersByUsername(username);
            return user;
        }

    @Cacheable("isUserValid")
    public boolean isUserValid(User user) {
        boolean isValid = true;

        isValid = usernameIsValid(user.getUsername());
        isValid = isValid == true ? passwordIsValid(user.getPassword()) : false;
        isValid = isValid == true ? emailIsValid(user.getEmail()) : false;

        if(user.getCard() !=null){
            isValid = isValid == true ? creditcardIsValid(user.getCard()) : false;
        }

        return isValid;
    }

    public boolean isRegisteredUser(String username) {
        return registrationRepository.findUsersByUsername(username) == null? false : true;
    }

    public List<User> filterUsersByCreditCard(String yesNoAll) {
        if(yesNoAll.equalsIgnoreCase("Optional[yes]")) {
            return (List<User>) registrationRepository.findUsersWithCreditCard();
        } else if(yesNoAll.equalsIgnoreCase("Optional[no]")) {
            return (List<User>) registrationRepository.findUsersWithOutCreditCard();
        } else {
            return (List<User>) registrationRepository.findAll();
        }
    }

    public boolean registerUser(User newUser) {
        boolean isChecked = false;

        if(isUserValid(newUser)
                && isRegisteredUser(newUser.getUsername()) == false)
        {
            registrationRepository.saveAndFlush(newUser);
            isChecked = true;
        }
        // TODO - check age - refactor code

        return isChecked;
    }
}
