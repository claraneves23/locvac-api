package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class FaixaVacinaId implements Serializable {

    private Long cdFaixa;
    private Long cdVacina;

    public FaixaVacinaId(){}

    public FaixaVacinaId(Long cdFaixa, Long cdVacina) {
        this.cdFaixa = cdFaixa;
        this.cdVacina = cdVacina;
    }
}
