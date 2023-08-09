package com.uno.flashcash.service;

import com.uno.flashcash.model.User;
import com.uno.flashcash.model.UserAccount;
import com.uno.flashcash.repository.UserAccountRepository;
import com.uno.flashcash.repository.UserRepository;
import com.uno.flashcash.service.form.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service ("userService")
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserAccountRepository userAccountRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;


        this.userAccountRepository = userAccountRepository;
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


}