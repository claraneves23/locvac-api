package com.api.locvac.model.associacao;

import com.api.locvac.model.core.Campanha;
import com.api.locvac.model.core.TipoVacina;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.model.core.Vacina;
import com.api.locvac.model.id.CampanhaUnidadeId;
import com.api.locvac.model.id.EstoqueVacinaId;
import jakarta.persistence.*;

@Entity
@Table(name = "campanha_unidade")
public class CampanhaUnidade {

    @EmbeddedId
    private CampanhaUnidadeId id;

    @ManyToOne
    @MapsId("cdUnidade")
    private UnidadeSaude unidadeSaude;

    @ManyToOne
    @MapsId("cdCampanha")
    private Campanha campanha;

    public CampanhaUnidade() {}

    public CampanhaUnidade(UnidadeSaude unidadeSaude, Campanha campanha) {
        this.unidadeSaude = unidadeSaude;
        this.campanha = campanha;
    }

    public static CampanhaUnidade of(Campanha campanha, UnidadeSaude unidadeSaude){
        CampanhaUnidade cpu = new CampanhaUnidade();

        cpu.id = new CampanhaUnidadeId(unidadeSaude.getCdUnidade(),
                campanha.getCdCampanha());

        cpu.campanha = campanha;
        cpu.unidadeSaude = unidadeSaude;

        return cpu;
    }

    public UnidadeSaude getUnidadeSaude() {
        return unidadeSaude;
    }

    public Campanha getCampanha() {
        return campanha;
    }
}
