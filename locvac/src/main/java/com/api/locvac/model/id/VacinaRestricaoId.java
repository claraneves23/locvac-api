package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class VacinaRestricaoId implements Serializable {

    private Long cdVacina;
    private Long cdRestricao;


    public VacinaRestricaoId(Long cdVacina, Long cdRestricao) {
        this.cdVacina = cdVacina;
        this.cdRestricao = cdRestricao;
    }

    public VacinaRestricaoId() {}
}
