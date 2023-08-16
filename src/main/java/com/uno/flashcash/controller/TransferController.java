package com.uno.flashcash.controller;

import com.uno.flashcash.model.User;
import com.uno.flashcash.service.SessionService;
import com.uno.flashcash.service.TransferService;
import com.uno.flashcash.service.form.TransferToBankForm;
import com.uno.flashcash.service.form.TransferToFlashCashForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransferController {

    private final TransferService transferService;
    private final SessionService sessionService;



    public TransferController(TransferService transferService, SessionService sessionService) {
        this.transferService = transferService;
        this.sessionService = sessionService;

    }

    @GetMapping("/transfer-to-bank") // Utilisez une méthode GET pour afficher le formulaire
    public String showTransferForm(Model model) {
        model.addAttribute("transferToBankForm", new TransferToBankForm());
        return "transfer-to-bank"; // Assurez-vous d'avoir un template "transfer-form.html"
    }

    @PostMapping("/transfer-to-bank")
    public ModelAndView transferCashToBank(Model model, @ModelAttribute("transferToBankForm") TransferToBankForm form) {
        transferService.transferToBank(form);
        User sessionUser = sessionService.sessionUser();
        model.addAttribute("user", sessionUser);

        return new ModelAndView("/profile"); // Redirige vers la page de profil après le transfert
    }


    @GetMapping("/transfer-to-flashcash")
    public String showBankPaymentForm(Model model) {
        model.addAttribute("transferToFlashCashForm", new TransferToFlashCashForm());
        User sessionUser = sessionService.sessionUser();
        model.addAttribute("user", sessionUser);
        return "transfer-to-flashcash"; // Le nom de la vue HTML sans l'extension (.html)
    }
//    @GetMapping("/transfer-to-flashcash")
//    public ModelAndView showWithdrawCash(Model model) {
//        User user = sessionService.sessionUser();
//        model.addAttribute("user", user);
//        return new ModelAndView("transfer-to-flashcash");
//    }

    @PostMapping("/transfer-to-flashcash")
    public ModelAndView showPaymentForm(Model model, @ModelAttribute("transferToFlashCashForm") TransferToFlashCashForm form){
        transferService.transferToFlashCashForm(form);
        User sessionUser = sessionService.sessionUser();
        model.addAttribute("user", sessionUser);

        return new ModelAndView("/profile");

    }



}
