package com.uno.flashcash.model;

import com.uno.flashcash.model.User;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal balance;

    // Constructeur par défaut (nécessaire pour JPA)
    public UserAccount() {
    }

    // Constructeur avec l'utilisateur et le solde initial
    public UserAccount(User user, BigDecimal balance) {
        this.user = user;
        this.balance = balance;
    }

    // Getters et Setters pour tous les attributs

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    // Ajoutez d'autres méthodes spécifiques au compte utilisateur si nécessaire

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", user=" + user +
                ", balance=" + balance +
                '}';
    }
}
