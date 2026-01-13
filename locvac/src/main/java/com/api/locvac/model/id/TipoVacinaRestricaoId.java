package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TipoVacinaRestricaoId implements Serializable {

    private Long cdTipoVacina;
    private Long cdRestricao;

    public TipoVacinaRestricaoId(Long cdRestricao, Long cdTipoVacina) {
        this.cdRestricao = cdRestricao;
        this.cdTipoVacina = cdTipoVacina;
    }

    public TipoVacinaRestricaoId() {}
}