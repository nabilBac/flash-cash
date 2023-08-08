package com.uno.flashcash.controller;


import ch.qos.logback.core.model.Model;
import com.uno.flashcash.service.UserService;
import com.uno.flashcash.service.form.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {


    private  final UserService userService;

    public UserController (UserService userService) {
        this.userService = userService;
    }
    @GetMapping ("/")
    public ModelAndView home(Model model){
        return new ModelAndView("index");


    }

    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute("signupForm") SignUpForm form) {

        userService.registration(form);{
            // L'utilisateur existe déjà, afficher un message d'erreur ou rediriger vers une autre page
            return new ModelAndView("signin");
        }

    }


    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }
    @GetMapping("/home")
    public String logOff(Model model){
        return "home";
    }


}
