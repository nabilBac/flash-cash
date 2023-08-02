package com.uno.flashcash.controller;


import com.uno.flashcash.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.uno.flashcash.service.UserService;
import com.uno.flashcash.service.form.SignUpForm;

@Controller
public class UserController {

    @Autowired
    private UserService userService; // Injectez le service UserService approprié

    // ...

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute("user") User user) {
        // Vérifier si l'utilisateur existe déjà dans la base de données
        if (userService.findByEmail(user.getEmail()) != null) {
            // L'utilisateur existe déjà, afficher un message d'erreur ou rediriger vers une autre page
            return "redirect:/register?error";
        }

        // Enregistrer le nouvel utilisateur dans la base de données
        userService.saveUser(user);

        // Rediriger vers la page de connexion après l'enregistrement réussi
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute User user) {
        // Traiter les données du formulaire de connexion
        // Vérifier l'authentification de l'utilisateur
        if (userService.authenticateUser(user.getEmail(), user.getPassword())) {
            // L'authentification est réussie, rediriger vers la page d'accueil
            return "redirect:/dashboard";
        } else {
            // L'authentification a échoué, rediriger vers la page de connexion avec un message d'erreur
            return "redirect:/login?error";
        }
    }
    @GetMapping("/registration")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("registration", "signUpForm", new SignUpForm());
    }

    // ...
}
