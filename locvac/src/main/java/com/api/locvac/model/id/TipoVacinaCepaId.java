package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TipoVacinaCepaId implements Serializable {

    private Long cdTipoVacina;
    private Long cdCepa;

    public TipoVacinaCepaId(Long cdTipoVacina, Long cdCepa) {
        this.cdTipoVacina = cdTipoVacina;
        this.cdCepa = cdCepa;
    }

    public TipoVacinaCepaId() {}

}
