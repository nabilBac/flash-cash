package com.uno.flashcash.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToMany
    User user1;
    @ManyToOne
    User user2;
}
