package com.uno.flashcash.controller;

import com.uno.flashcash.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class TransferController {


    private final UserService userService; // Injectez le service UserService approprié

    public TransferController(UserService userService) {
        this.userService = userService;
    }


//    private final TransferService transferService; // Injectez le service TransferService approprié

//    @GetMapping("/transfer")
//    public String showTransferForm(Model model) {
//        // Chargez les informations nécessaires pour afficher le formulaire de transfert
//        // Par exemple, récupérez la liste des utilisateurs
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("transfer", new Transfer()); // Créez un nouvel objet Transfer pour lier le formulaire
//        return "transfer_form"; // Assurez-vous d'avoir un fichier HTML correspondant
//    }


}
