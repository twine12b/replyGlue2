package com.replyglue.demo.service;

import com.replyglue.demo.domain.User;
import com.replyglue.demo.repository.RegistrationRepository;
import com.replyglue.demo.util.UserNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Cacheable("findUsersByUsername")
    public User findUsersByUsername(String username) {
        User user = registrationRepository.findUsersByUsername(username);
        if(user == null) throw new UserNotFoundException();
            return user;
        }

    public boolean isUserValid(User user) {
        System.out.println(user.toString());
        return true;
    }
}
