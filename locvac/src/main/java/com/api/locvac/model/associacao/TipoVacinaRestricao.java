package com.api.locvac.model.associacao;

import com.api.locvac.model.core.Restricao;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.model.id.TipoVacinaRestricaoId;
import jakarta.persistence.*;

@Entity
@Table(name = "tipo_vacina_restricao")
public class TipoVacinaRestricao {

    @EmbeddedId
    private TipoVacinaRestricaoId id;

    @ManyToOne
    @MapsId("cdTipoVacina")
    @JoinColumn(name = "cd_tipo_vacina")
    private TipoVacina tipoVacina;

    @ManyToOne
    @MapsId("cdRestricao")
    @JoinColumn(name = "cd_restricao")
    private Restricao restricao;

    protected TipoVacinaRestricao() {}

    public static TipoVacinaRestricao of(TipoVacina tipoVacina, Restricao restricao) {
        TipoVacinaRestricao tvr = new TipoVacinaRestricao();
        tvr.tipoVacina = tipoVacina;
        tvr.restricao = restricao;
        tvr.id = new TipoVacinaRestricaoId(
                tipoVacina.getCdTipoVacina(),
                restricao.getCdRestricao()
        );
        return tvr;
    }

    public TipoVacinaRestricaoId getId() {
        return id;
    }

    public Restricao getRestricao() {
        return restricao;
    }

    public TipoVacina getTipoVacina() {
        return tipoVacina;
    }
}

