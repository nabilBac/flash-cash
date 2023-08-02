package com.uno.flashcash.repository;

import com.uno.flashcash.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {

    // Ajoutez des méthodes personnalisées ici si nécessaire
}
