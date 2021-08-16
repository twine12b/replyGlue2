package com.replyglue.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor @ToString
@Getter  @Setter
public class Payment {

    @Id
    @GeneratedValue
    private long id;

//    @NotNull private User user;
    @NotNull private long payment_card;
    @NotNull private Double amount;

    public Payment(long payment_card, double amount) {
        this.payment_card = payment_card;
        this.amount = amount;
    }
}
