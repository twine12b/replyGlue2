package com.replyglue.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.replyglue.demo.domain.User;
import com.replyglue.demo.service.RegistrationService;
import com.replyglue.demo.util.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class RegistrationController {

    @Autowired
    private ObjectMapper objectMapper;

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //TODO - clean code
//    @GetMapping("/users/")
//    public List<User> getAllUsers(){
//        return registrationService.getAllUers();
//    }

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return registrationService.findUsersByUsername(username);
    }

    @PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> validateUser(@RequestBody User user){
        //TODO - Cannot deserialize instance of `java.util.Calendar
//        User user = userS
//        registrationService.isUserValid(user);
        return ResponseEntity.ok().body(HttpStatus.OK.toString());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void userNotFoundHandler(UserNotFoundException ex){}
}
