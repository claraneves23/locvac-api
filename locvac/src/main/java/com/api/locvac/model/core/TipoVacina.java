package com.api.locvac.model.core;

import jakarta.persistence.*;

@Entity
@Table
public class TipoVacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdTipoVacina;

    private String nmVacina;

    public TipoVacina(){}

    public TipoVacina(Long cdTipoVacina, String nmVacina) {
        this.cdTipoVacina = cdTipoVacina;
        this.nmVacina = nmVacina;
    }

    public Long getCdTipoVacina() {
        return cdTipoVacina;
    }

    public String getNmVacina() {
        return nmVacina;
    }
}