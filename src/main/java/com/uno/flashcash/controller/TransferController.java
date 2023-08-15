package com.uno.flashcash.controller;

import com.uno.flashcash.service.TransferService;
import com.uno.flashcash.service.SessionService;
import com.uno.flashcash.service.form.TransferToBankForm;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public TransferController(TransferService transferService, SessionService sessionService) {
        this.transferService = transferService;
        this.sessionService = sessionService;
    }

    @GetMapping("transfer-form") // Utilisez une méthode GET pour afficher le formulaire
    public String showTransferForm(Model model) {
        model.addAttribute("transferToBankForm", new TransferToBankForm());
        return "transfer-form"; // Assurez-vous d'avoir un template "transfer-form.html"
    }

    @PostMapping("transfer-to-bank")
    public ModelAndView transferCashToBank(@ModelAttribute("transferToBankForm") TransferToBankForm form) {
        transferService.transferToBank(form);

        return new ModelAndView("redirect:/profile"); // Redirige vers la page de profil après le transfert
    }
}
