package com.uno.flashcash.controller;

import com.uno.flashcash.model.Transfer;
import com.uno.flashcash.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.uno.flashcash.service.TransferService;
import com.uno.flashcash.service.UserService;

@Controller
public class TransferController {

    @Autowired
    private UserService userService; // Injectez le service UserService approprié

    @Autowired
    private TransferService transferService; // Injectez le service TransferService approprié

    @GetMapping("/transfer")
    public String showTransferForm(Model model) {
        // Chargez les informations nécessaires pour afficher le formulaire de transfert
        // Par exemple, récupérez la liste des utilisateurs
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("transfer", new Transfer()); // Créez un nouvel objet Transfer pour lier le formulaire
        return "transfer_form"; // Assurez-vous d'avoir un fichier HTML correspondant
    }

    @PostMapping("/transfer")
    public String processTransferForm(@ModelAttribute("transfer") Transfer transfer) {
        // Implémentez la logique pour effectuer le transfert et mettre à jour les soldes
        // Utilisez les services appropriés et les méthodes de modèle pour cela

        // Assurez-vous que les informations sur l'expéditeur et le destinataire sont correctes
        User sender = transfer.getSender();
        User receiver = transfer.getReceiver();

        if (sender != null && receiver != null) {
            transferService.performTransfer(transfer, sender, receiver);
        }

        return "redirect:/dashboard"; // Redirigez vers la page d'accueil ou une autre page
    }

}
