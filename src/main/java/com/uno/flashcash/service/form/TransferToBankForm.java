package com.uno.flashcash.service.form;
import lombok.Data;

@Data

public class TransferToBankForm {

    private String iban;

    private double amount;
}
