package com.replyglue.demo.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserValidationService {

    public boolean usernameIsValid(String username) {
        String regex = "^[a-zA-Z0-9]*$";
        return doValidation(regex, username);
    }

    public boolean passwordIsValid(String password) {
        String regex = "(?=.*[A-Z])(?=.*\\d)^[a-zA-Z0-9]{8,}$";
        return doValidation(regex, password);
    }

    public boolean emailIsValid(String email) {
        String regex = "^\\S+@\\S+$";   //TODO - make this expression more robust
        return doValidation(regex, email);
    }

    private boolean doValidation(String regex, String stringToValidate) {
        if(stringToValidate == null) return false;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(stringToValidate);

        return stringToValidate.length() > 0
                ? matcher.matches()  ? true : false
                : false;
    }

    public boolean creditcardIsValid(long creditcard) {
        String regex = "^[0-9]{16,}$";
        return doValidation(regex, Long.toString(creditcard));
    }

    public boolean dobIsValid(String dob) {
        String regex = "^([0-9]{4})(-?)(1[0-2]|0[1-9])\\2(3[01]|0[1-9]|[12][0-9])$";
        return doValidation(regex, dob);
    }

}
