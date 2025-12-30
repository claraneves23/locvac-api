package com.api.locvac.model.associacao;

import com.api.locvac.model.core.Restricao;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.AdultoRestricaoId;
import com.api.locvac.model.id.DependenteRestricaoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class DependenteRestricao {

    @EmbeddedId
    private DependenteRestricaoId id;

    @ManyToOne
    @MapsId("cdDependente")
    private Vacina vacina;

    @ManyToOne
    @MapsId("cdRestricao")
    private Restricao restricao;

}
