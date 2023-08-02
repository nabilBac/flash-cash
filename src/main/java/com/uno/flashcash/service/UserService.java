package com.uno.flashcash.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uno.flashcash.model.User;
import com.uno.flashcash.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Méthode pour enregistrer un nouvel utilisateur dans la base de données
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // Méthode pour trouver un utilisateur par son adresse e-mail
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Méthode pour authentifier un utilisateur par son adresse e-mail et son mot de passe
    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);

        // L'utilisateur existe et le mot de passe correspond
        return user != null && user.getPassword().equals(password);
    }

    // Méthode pour obtenir la liste de tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Ajoutez d'autres méthodes de service si nécessaire pour gérer les utilisateurs
}
