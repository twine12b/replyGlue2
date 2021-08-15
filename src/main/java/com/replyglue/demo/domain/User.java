package com.replyglue.demo.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;

@Entity
@Getter  @Setter
@NoArgsConstructor @ToString
public class User {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private Calendar dob;

    public User(String username, String password, String email, Calendar dob) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }
}

//TODO - user inheritance to resolve the optional credit card