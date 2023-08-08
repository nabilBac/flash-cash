/*package com.uno.flashcash.service;

import com.uno.flashcash.model.Transfer;
import com.uno.flashcash.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uno.flashcash.repository.TransferRepository; // Assurez-vous d'importer le repository approprié

@Service
public class TransferService {

    private final TransferRepository transferRepository;

    @Autowired
    public TransferService(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    public void performTransfer(Transfer transfer, User sender, User receiver) {
        // Vérifiez si le solde de l'expéditeur est suffisant pour effectuer le transfert
        if (sender.getUserAccount().getBalance().compareTo(transfer.getAmount()) >= 0) {
            // Mettez à jour les soldes des comptes
            sender.getUserAccount().setBalance(sender.getUserAccount().getBalance().subtract(transfer.getAmount()));
            receiver.getUserAccount().setBalance(receiver.getUserAccount().getBalance().add(transfer.getAmount()));

            // Enregistrez le transfert dans la base de données
            transferRepository.save(transfer);
        }
    }

    // Ajoutez d'autres méthodes de service si nécessaire pour gérer les transferts
}*/
