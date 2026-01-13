package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EstoqueVacinaId implements Serializable {

    private Long cdVacina;
    private Long cdUnidade;

    public EstoqueVacinaId(Long cdVacina, Long cdUnidade) {
        this.cdVacina = cdVacina;
        this.cdUnidade = cdUnidade;
    }

    public EstoqueVacinaId() {}
}
