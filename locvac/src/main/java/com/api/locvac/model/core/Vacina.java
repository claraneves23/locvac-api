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

    private String nmVacina;
    private LocalDate dtFabricacao;
    private LocalDate dtValidade;

    @Column(columnDefinition = "TEXT")
    private String dsVacina;

    public Vacina(){}

    public Vacina(Fabricante fabricante, String nmVacina, LocalDate dtFabricacao, LocalDate dtValidade, String dsVacina) {
        this.fabricante = fabricante;
        this.nmVacina = nmVacina;
        this.dtFabricacao = dtFabricacao;
        this.dtValidade = dtValidade;
        this.dsVacina = dsVacina;
    }

    public Long getId() {
        return id;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public LocalDate getDtFabricacao() {
        return dtFabricacao;
    }

    public String getNmVacina() {
        return nmVacina;
    }

    public LocalDate getDtValidade() {
        return dtValidade;
    }

    public String getDsVacina() {
        return dsVacina;
    }
}

