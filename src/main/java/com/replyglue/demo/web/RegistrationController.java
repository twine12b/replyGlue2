package com.replyglue.demo.web;

import com.replyglue.demo.domain.User;
import com.replyglue.demo.service.RegistrationService;
import com.replyglue.demo.util.UserNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return registrationService.findUsersByUsername(username);
    }

    @GetMapping()
    public List<User> getUsersByFilter(@RequestParam("CreditCard") Optional<String> yesNoAll){
        return registrationService.filterUsersByCreditCard(yesNoAll.toString());
    }


    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity validateUser(@RequestBody User user){
        //TODO - Cannot deserialize instance  of `java.util.Calendar

        var responseEntity = registrationService.isUserValid(user) == true ?
                registrationService.isRegisteredUser(user.getUsername()) ? new ResponseEntity(HttpStatus.CONFLICT) :
                        registrationService.registerUser(user) ? new ResponseEntity(HttpStatus.CREATED) : new ResponseEntity(HttpStatus.BAD_REQUEST)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void userNotFoundHandler(UserNotFoundException ex){}
}
