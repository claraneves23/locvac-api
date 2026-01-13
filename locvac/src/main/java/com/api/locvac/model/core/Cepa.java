package com.api.locvac.model.core;

import jakarta.persistence.*;

@Entity
@Table(name = "cepa")

public class Cepa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_cepa")
    private Long id;

    @Column(name = "nm_cepa", nullable = false)
    private String nome;

    public Cepa() {}

    public Cepa(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
