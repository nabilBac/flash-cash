package com.uno.flashcash.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }



    @ManyToOne
    private User from;

    @ManyToOne
    private User to;

    public double getAmountBeforeFee() {
        return amountBeforeFee;
    }

    public void setAmountBeforeFee(double amountBeforeFee) {
        this.amountBeforeFee = amountBeforeFee;
    }

    private double amountBeforeFee;

    public double getAmountAfterFee() {
        return amountAfterFee;
    }

    public void setAmountAfterFee(double amountAfterFee) {
        this.amountAfterFee = amountAfterFee;
    }

    private double amountAfterFee;

}