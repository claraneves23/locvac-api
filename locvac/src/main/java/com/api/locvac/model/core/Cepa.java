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
}
