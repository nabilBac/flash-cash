package com.uno.flashcash.service;

import com.uno.flashcash.model.User;
import com.uno.flashcash.model.UserAccount;
import com.uno.flashcash.repository.UserAccountRepository;
import com.uno.flashcash.repository.UserRepository;
import com.uno.flashcash.service.form.AddIbanForm;
import com.uno.flashcash.service.form.SignUpForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service ("userService")
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;
    private final SessionService sessionService;


    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserAccountRepository userAccountRepository, SessionService sessionService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userAccountRepository = userAccountRepository;
        this.sessionService = sessionService;

    }

    public User registration(SignUpForm form) {
        User user = new User();
        UserAccount account = new UserAccount();
        account.setAmount(0.0);
        user.setUserAccount(account);
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        return userRepository.save(user);
    }




    public Iterable<User> getUser(){return userRepository.findAll();}


    public void addIban(final AddIbanForm form) {


        UserAccount account = sessionService.sessionUser().getUserAccount();
        account.setIban(form.getIban());
        userAccountRepository.save(account);
    }
}