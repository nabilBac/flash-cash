package com.uno.flashcash.repository;

import com.uno.flashcash.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Ajoutez ici des méthodes de requête personnalisées si nécessaire
    User findByEmail(String email);
}
