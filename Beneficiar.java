package com.Gestionare.Contracte.demo;

import jakarta.persistence.*;
import org.springframework.lang.Contract;

import java.util.List;

@Entity
public class Beneficiar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "beneficiar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contract;
}

