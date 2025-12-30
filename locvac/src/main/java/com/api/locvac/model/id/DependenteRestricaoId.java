package com.api.locvac.model.id;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DependenteRestricaoId implements Serializable {

    private Long cdDependente;
    private Long cdRestricao;

}
