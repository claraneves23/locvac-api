package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CampanhaUnidadeId implements Serializable {

    private Long cdUnidade;
    private Long cdCampanha;

    public CampanhaUnidadeId() {}

    public CampanhaUnidadeId(Long cdCampanha, Long cdUnidade) {
        this.cdCampanha = cdCampanha;
        this.cdUnidade = cdUnidade;
    }
}