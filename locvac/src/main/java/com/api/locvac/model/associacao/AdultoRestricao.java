package com.api.locvac.model.associacao;

import com.api.locvac.model.core.Restricao;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.AdultoRestricaoId;
import com.api.locvac.model.id.VacinaRestricaoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class AdultoRestricao {

    @EmbeddedId
    private AdultoRestricaoId id;

    @ManyToOne
    @MapsId("cdAdulto")
    private Vacina vacina;

    @ManyToOne
    @MapsId("cdRestricao")
    private Restricao restricao;
}
