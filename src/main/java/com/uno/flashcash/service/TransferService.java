package com.uno.flashcash.service;
import com.uno.flashcash.repository.UserAccountRepository;


import com.uno.flashcash.service.form.TransferToFlashCashForm;
import com.uno.flashcash.service.form.TransferToBankForm;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private final UserAccountRepository userAccountRepository;
    private final SessionService sessionService;



    public TransferService(UserAccountRepository userAccountRepository, SessionService sessionService) {
        this.userAccountRepository = userAccountRepository;
        this.sessionService = sessionService;
    }


    public void transferToBank(TransferToBankForm form) {
        if (form != null) {
            userAccountRepository.save(sessionService.sessionUser().getUserAccount().minus(form.getAmount()));

        } else {
        }
    }

    public void transferToFlashCashForm(TransferToFlashCashForm form) {
        if (form != null) {
            userAccountRepository.save(sessionService.sessionUser().getUserAccount().plus(form.getAmount()));

        } else {
        }


    }
}