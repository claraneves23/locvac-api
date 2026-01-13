package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TipoVacinaFaixaId implements Serializable {

    private Long cdTipoVacina;
    private Long cdFaixaEtaria;

    public TipoVacinaFaixaId() {}

    public TipoVacinaFaixaId(Long cdTipoVacina, Long cdFaixaHetaria) {
        this.cdTipoVacina = cdTipoVacina;
        this.cdFaixaEtaria = cdFaixaHetaria;
    }

}
