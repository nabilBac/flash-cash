package com.uno.flashcash.service.form;

import lombok.Data;

@Data
public class TransferForm {
    private String contactEmail;
    private double amount;
     public String getContactEmail(){return contactEmail;}
    public void setContactEmail(String contactEmail){this.contactEmail = contactEmail;}

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
