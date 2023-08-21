package com.uno.flashcash.service;

import com.uno.flashcash.model.Link;
import com.uno.flashcash.model.Transfer;
import com.uno.flashcash.model.User;
import com.uno.flashcash.repository.LinkRepository;
import com.uno.flashcash.repository.TransferRepository;
import com.uno.flashcash.repository.UserAccountRepository;
import com.uno.flashcash.repository.UserRepository;
import com.uno.flashcash.service.form.AddContactForm;
import com.uno.flashcash.service.form.TransferForm;
import com.uno.flashcash.service.form.TransferToBankForm;
import com.uno.flashcash.service.form.TransferToFlashCashForm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferService {
    private final UserAccountRepository userAccountRepository;
    private final SessionService sessionService;
    private final UserRepository userRepository;
    private final TransferRepository transferRepository;

    private final LinkRepository linkRepository;



    public TransferService(UserAccountRepository userAccountRepository, SessionService sessionService, UserRepository userRepository, TransferRepository transferRepository, LinkRepository linkRepository) {
        this.userAccountRepository = userAccountRepository;
        this.sessionService = sessionService;
        this.userRepository = userRepository;
        this.transferRepository = transferRepository;
        this.linkRepository = linkRepository;
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

    public void transfer(TransferForm form) {
        if (form!= null){
            User to = userRepository.findUserByMail(form.getContactEmail())
                    .orElseThrow(()-> new RuntimeException("user with email not found"));
            Transfer transfer = new Transfer();
            transfer.setDate(LocalDateTime.now());
            transfer.setAmountBeforeFee(form.getAmount());
            transfer.setAmountAfterFee(form.getAmount() + form.getAmount() * 0.0005);
            transfer.setFrom(sessionService.sessionUser());
            transfer.setTo(to);



            userAccountRepository.save(sessionService.sessionUser().getUserAccount().minus(transfer.getAmountAfterFee()));
            userAccountRepository.save(to.getUserAccount().plus(transfer.getAmountBeforeFee()));
            transferRepository.save(transfer);


       }
    }

  public List<Transfer> findTransactions() {
       return transferRepository.findAll(); // Supposons que vous ayez une méthode findAll dans TransferRepository
  }
  public void addContactForm(AddContactForm form){

        Link link=new Link();
        link.setUser1(sessionService.sessionUser());
        link.setUser2(userRepository.findUserByMail(form.getEmail()).get());




        linkRepository.save(link);
  }
}