package com.api.locvac.model.associacao;

import com.api.locvac.model.core.Cepa;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.CepaVacinaId;
import jakarta.persistence.*;

@Entity
@Table(name = "cepa_vacina")
public class CepaVacina {

    @EmbeddedId
    private CepaVacinaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cdVacina")
    private Vacina vacina;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cdCepa")
    private Cepa cepa;

}
