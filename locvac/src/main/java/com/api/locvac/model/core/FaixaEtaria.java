package com.api.locvac.model.core;

import jakarta.persistence.*;

@Entity
@Table(name = "faixa_etaria")
public class FaixaEtaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_faixa")
    private Long id;

    @Column(name = "ds_faixa")
    private String descricao;

    @Column(name = "nr_idade_min")
    private String idadeMinima;

    @Column(name = "nr_idade_max")
    private String idadeMaxima;

    public FaixaEtaria(){}

    public FaixaEtaria(String idadeMaxima, String descricao, String idadeMinima) {
        this.idadeMaxima = idadeMaxima;
        this.descricao = descricao;
        this.idadeMinima = idadeMinima;
    }

    public Long getId() {
        return id;
    }

    public String getIdadeMaxima() {
        return idadeMaxima;
    }

    public String getIdadeMinima() {
        return idadeMinima;
    }

    public String getDescricao() {
        return descricao;
    }
}
