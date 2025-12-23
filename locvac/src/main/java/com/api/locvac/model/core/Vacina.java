package com.api.locvac.model.core;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "vacina")
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_vacina")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_fabricante")
    private Fabricante fabricante;

    private String nm_vacina;
    private LocalDate dt_fabricacao;
    private LocalDate dt_validade;

    @Column(columnDefinition = "TEXT")
    private String ds_vacina;
}

