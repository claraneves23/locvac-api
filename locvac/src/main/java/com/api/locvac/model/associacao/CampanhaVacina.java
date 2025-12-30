package com.api.locvac.model.associacao;

import com.api.locvac.model.core.Campanha;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.CampanhaVacinaId;
import jakarta.persistence.*;

@Entity
@Table(name = "campanha_vacina")
public class CampanhaVacina {

    @EmbeddedId
    private CampanhaVacinaId id;

    @ManyToOne
    @MapsId("cdVacina")
    private Vacina vacina;

    @ManyToOne
    @MapsId("cdCampanha")
    private Campanha campanha;

    @ManyToOne
    private UnidadeSaude unidadeSaude;
}
