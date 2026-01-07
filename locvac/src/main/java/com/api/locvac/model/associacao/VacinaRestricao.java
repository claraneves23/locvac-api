package com.api.locvac.model.associacao;

import com.api.locvac.model.core.FaixaEtaria;
import com.api.locvac.model.core.Restricao;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.FaixaVacinaId;
import com.api.locvac.model.id.VacinaRestricaoId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class VacinaRestricao {
    @EmbeddedId
    private VacinaRestricaoId id;

    @ManyToOne
    @MapsId("cdVacina")
    private Vacina vacina;

    @ManyToOne
    @MapsId("cdRestricao")
    private Restricao restricao;

    public VacinaRestricao() {}

    public VacinaRestricao(Vacina vacina, Restricao restricao) {
        this.vacina = vacina;
        this.restricao = restricao;
    }

    public static VacinaRestricao of(Vacina vacina, Restricao restricao) {
        VacinaRestricao vr = new VacinaRestricao();
        vr.id = new VacinaRestricaoId(restricao.getCdRestricao(), vacina.getId());
        vr.vacina = vacina;
        vr.restricao = restricao;
        return vr;
    }

    public VacinaRestricaoId getId() {
        return id;
    }

    public Restricao getRestricao() {
        return restricao;
    }

    public Vacina getVacina() {
        return vacina;
    }
}
