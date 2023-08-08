package com.uno.flashcash.model;

import jakarta.persistence.*;

@Entity
public class FlashCashAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double totalAmount;
    private double flashCashAccount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
