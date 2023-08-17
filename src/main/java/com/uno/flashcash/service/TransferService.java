package com.uno.flashcash.service;

import com.uno.flashcash.repository.UserAccountRepository;
import com.uno.flashcash.service.form.TransferToBankForm;
import com.uno.flashcash.service.form.TransferToFlashCashForm;
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

//    public void transfer(TransferForm form) {
//        User sessionUser = sessionService.sessionUser();
//
//        if (sessionUser.getUserAccount().getAmount() >= form.getAmount()) {
//            sessionUser.getUserAccount().minus(form.getAmount());
//            userAccountRepository.save(sessionUser.getUserAccount());
//
//            Transfer transfer = new Transfer();
////            transfer.setSender(sessionUser);
////            transfer.setRecipientEmail(form.getRecipientEmail());
////            transfer.setAmount(form.getAmount());
////            transferRepository.save(transfer);
//        } else {
//            // Gérer le cas de solde insuffisant
//        }
//    }
//
//    public List<Transfer> findTransactions() {
//        return transferRepository.findAll(); // Supposons que vous ayez une méthode findAll dans TransferRepository
//    }
}