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
    private Integer idadeMinima;

    @Column(name = "nr_idade_max")
    private Integer idadeMaxima;

}
