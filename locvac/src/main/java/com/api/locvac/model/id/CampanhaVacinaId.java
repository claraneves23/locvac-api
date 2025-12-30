package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CampanhaVacinaId implements Serializable {

    private Long cdVacina;
    private Long cdCampanha;

}
