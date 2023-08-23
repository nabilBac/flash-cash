package com.uno.flashcash.controller;

import com.uno.flashcash.model.Transfer;
import com.uno.flashcash.model.User;
import com.uno.flashcash.service.LinkService;
import com.uno.flashcash.service.SessionService;
import com.uno.flashcash.service.TransferService;
import com.uno.flashcash.service.form.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TransferController {

    private final TransferService transferService;
    private final SessionService sessionService;
    private final LinkService linkService;



    public TransferController(TransferService transferService, SessionService sessionService, LinkService linkService) {
        this.transferService = transferService;
        this.sessionService = sessionService;

        this.linkService = linkService;
    }

    @GetMapping("/transfer-to-bank")
    public String showTransferForm(Model model) {
        model.addAttribute("transferToBankForm", new TransferToBankForm());
        return "transfer-to-bank";
    }

    @PostMapping("/transfer-to-bank")
    public ModelAndView transferCashToBank(Model model, @ModelAttribute("transferToBankForm") TransferToBankForm form) {
        transferService.transferToBank(form);
        User sessionUser = sessionService.sessionUser();
        model.addAttribute("user", sessionUser);

        return new ModelAndView("/profile"); // Redirige vers la page de profil après le transfert
    }
    @GetMapping("/transfer-to-contact")
    public ModelAndView transferToContact(Model model){
        List<String> linksEmail = linkService.findLinksEmail();
        model.addAttribute("linksEmail", linksEmail);
        return new ModelAndView("transfer-to-contact","transferForm", new TransferForm());
    }
    @PostMapping("/transfer-to-contact")
    public ModelAndView transfer(Model model, @ModelAttribute("transferForm")TransferForm form){
        transferService.transfer(form);
        List<Transfer> transfers = transferService.findTransactions();
        model.addAttribute("transfer", transfers);
        return new ModelAndView("transfer");

    }
    @GetMapping("/add-friend")
    public ModelAndView transfer(Model model){
        model.addAttribute("addContactForm ", new AddContactForm());
        return new ModelAndView("add-friend");
    }



    @PostMapping("/add-friend")
    public ModelAndView showContactForm(Model model, @ModelAttribute("addContactForm") AddContactForm form){
//       transferService. addContactForm(form);

        return new ModelAndView("/profile");

    }


    @GetMapping("/transfer-to-flashcash")
    public String showBankPaymentForm(Model model) {
        model.addAttribute("transferToFlashCashForm", new TransferToFlashCashForm());
        User sessionUser = sessionService.sessionUser();
        model.addAttribute("user", sessionUser);
        return "transfer-to-flashcash"; // Le nom de la vue HTML sans l'extension (.html)
    }


    @PostMapping("/transfer-to-flashcash")
    public ModelAndView showPaymentForm(Model model, @ModelAttribute("transferToFlashCashForm") TransferToFlashCashForm form){
        transferService.transferToFlashCashForm(form);
        User sessionUser = sessionService.sessionUser();
        model.addAttribute("user", sessionUser);

        return new ModelAndView("/profile");

    }




}
