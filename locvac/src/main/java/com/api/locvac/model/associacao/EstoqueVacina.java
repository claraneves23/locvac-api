package com.api.locvac.model.associacao;

import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.EstoqueVacinaId;
import jakarta.persistence.*;

@Entity
@Table(name = "estoque_vacina")
public class EstoqueVacina {

    @EmbeddedId
    private EstoqueVacinaId id;

    @ManyToOne
    @MapsId("cdVacina")
    private Vacina vacina;

    @ManyToOne
    @MapsId("cdUnidade")
    private UnidadeSaude unidadeSaude;

    @Column(name = "qt_disponivel")
    private Integer quantidade;
}

