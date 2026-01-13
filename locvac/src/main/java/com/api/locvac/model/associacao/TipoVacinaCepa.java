package com.api.locvac.model.associacao;

import com.api.locvac.model.core.*;
import com.api.locvac.model.id.EstoqueVacinaId;
import com.api.locvac.model.id.TipoVacinaCepaId;
import com.api.locvac.model.id.TipoVacinaFaixaId;
import jakarta.persistence.*;

@Entity
@Table(name = "tipo_vacina_cepa")
public class TipoVacinaCepa {

    @EmbeddedId
    private TipoVacinaCepaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cdTipoVacina")
    private TipoVacina tipoVacina;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("cdCepa")
    private Cepa cepa;

    public TipoVacinaCepa() {}

    public TipoVacinaCepa(TipoVacina tipoVacina, Cepa cepa) {
        this.tipoVacina = tipoVacina;
        this.cepa = cepa;
    }

    public static TipoVacinaCepa of(TipoVacina tipoVacina, Cepa cepa) {
        TipoVacinaCepa tvc = new TipoVacinaCepa();
        tvc.tipoVacina = tipoVacina;
        tvc.cepa = cepa;
        tvc.id = new TipoVacinaCepaId(
                tipoVacina.getCdTipoVacina(),
                cepa.getId()
        );
        return tvc;
    }

    public TipoVacina getTipoVacina() {
        return tipoVacina;
    }

    public Cepa getCepa() {
        return cepa;
    }
}
