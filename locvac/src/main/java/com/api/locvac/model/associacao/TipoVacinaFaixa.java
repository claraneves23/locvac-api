package com.api.locvac.model.associacao;

import com.api.locvac.model.core.FaixaEtaria;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.model.id.TipoVacinaFaixaId;
import jakarta.persistence.*;

@Entity
@Table(name = "tipo_vacina_faixa")
public class TipoVacinaFaixa {

    @EmbeddedId
    private TipoVacinaFaixaId id;

    @ManyToOne
    @MapsId("cdTipoVacina")
    @JoinColumn(name = "cd_tipo_vacina")
    private TipoVacina tipoVacina;

    @ManyToOne
    @MapsId("cdFaixaEtaria")
    @JoinColumn(name = "cd_faixa_etaria")
    private FaixaEtaria faixa;


    protected TipoVacinaFaixa() {}

    public static TipoVacinaFaixa of(TipoVacina tipoVacina, FaixaEtaria faixa) {
        TipoVacinaFaixa tvf = new TipoVacinaFaixa();
        tvf.tipoVacina = tipoVacina;
        tvf.faixa = faixa;
        tvf.id = new TipoVacinaFaixaId(
                tipoVacina.getCdTipoVacina(),
                faixa.getId()
        );
        return tvf;
    }

    public TipoVacinaFaixaId getId() {
        return id;
    }

    public TipoVacina getTipoVacina() {
        return tipoVacina;
    }

    public FaixaEtaria getFaixa() {
        return faixa;
    }
}

