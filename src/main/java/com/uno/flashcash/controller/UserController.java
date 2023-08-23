package com.uno.flashcash.controller;


import com.uno.flashcash.model.User;
import com.uno.flashcash.service.SessionService;
import com.uno.flashcash.service.UserService;
import com.uno.flashcash.service.form.AddIbanForm;
import com.uno.flashcash.service.form.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {


    private  final UserService userService;
    private final SessionService sessionService;

    public UserController (UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }




    @GetMapping ("/")
    public ModelAndView home(Model model){
        return new ModelAndView("index");
    }

    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute("signupForm") SignUpForm form) {
        userService.registration(form);
            return new ModelAndView("signin");
        }

    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signUpForm", new SignUpForm());
    }
    @GetMapping("/home")
    public String logOff(Model model){
        return "home";
    }

    @GetMapping("/profile")
    public ModelAndView profile(Model model){
        User user = sessionService.sessionUser();
        model.addAttribute("user",user);
        return new ModelAndView("profile");
    }
    @GetMapping("/add-iban")
    public ModelAndView getAddConnectionForm(Model model) {
        return new ModelAndView("add-iban", "addIbanForm", new AddIbanForm()); // Renvoie la vue "add-iban"
    }

    @PostMapping("/add-iban")
    public ModelAndView addIban(Model model, @ModelAttribute("addIbanForm") AddIbanForm form) {
        userService.addIban(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user",user);

        return new ModelAndView("profile");
    }

}
