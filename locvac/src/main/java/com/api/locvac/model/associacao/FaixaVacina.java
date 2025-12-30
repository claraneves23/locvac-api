package com.api.locvac.model.associacao;

import com.api.locvac.model.core.FaixaEtaria;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.FaixaVacinaId;
import jakarta.persistence.*;

@Entity
@Table(name = "faixa_vacina")
public class FaixaVacina {

    @EmbeddedId
    private FaixaVacinaId id;

    @ManyToOne
    @MapsId("cdVacina")
    private Vacina vacina;

    @ManyToOne
    @MapsId("cdFaixa")
    private FaixaEtaria faixaEtaria;

}
