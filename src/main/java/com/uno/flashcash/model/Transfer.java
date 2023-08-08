package com.uno.flashcash.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;

    @ManyToOne
    private User from;

    @ManyToOne
    private User to;
    private double amountBeforeFee;
    private double amountAfterFee;

}