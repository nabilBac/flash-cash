package com.uno.flashcash.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @OneToOne(mappedBy = "user")
    private UserAccount userAccount;

    @OneToMany(mappedBy = "sender")
    private List<Transfer> sentTransfers;

    @OneToMany(mappedBy = "receiver")
    private List<Transfer> receivedTransfers;

    // Constructeur par défaut (nécessaire pour JPA)
    public User() {
    }

    // Constructeur avec tous les attributs sauf l'ID (l'ID sera généré automatiquement par la base de données)
    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters et Setters pour tous les attributs

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public List<Transfer> getSentTransfers() {
        return sentTransfers;
    }

    public void setSentTransfers(List<Transfer> sentTransfers) {
        this.sentTransfers = sentTransfers;
    }

    public List<Transfer> getReceivedTransfers() {
        return receivedTransfers;
    }

    public void setReceivedTransfers(List<Transfer> receivedTransfers) {
        this.receivedTransfers = receivedTransfers;
    }

    // Ajoutez d'autres méthodes spécifiques à l'utilisateur si nécessaire

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
