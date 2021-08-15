package com.replyglue.demo.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    @Nullable
    private Long card;

    public User(String username, String password, String email, String dob) {
        this.username = username;
        this.password = password;
        this.email = email;
//        this.dob = convertStringToDate(dob);
    }

    // Optional Card details constructor
    public User(String username, String password, String email, String dob, Long card) {
        this.username = username;
        this.password = password;
        this.email = email;
//        this.dob = convertStringToDate(dob);
        this.card = card;
    }

    private GregorianCalendar convertStringToDate(String dob){
        String[] splits = dob.split("((?=-))");
        int year = Integer.parseInt(splits[0]);
        int month = Integer.parseInt(splits[1]);
        int day = Integer.parseInt(splits[2]);
        return new GregorianCalendar(year, month, day);
    }
}