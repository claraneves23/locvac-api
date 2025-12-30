package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AdultoRestricaoId implements Serializable {

    private Long cdAdulto;
    private Long cdRestricao;

}
