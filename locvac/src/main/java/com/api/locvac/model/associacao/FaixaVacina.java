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



    public FaixaVacina(FaixaEtaria faixaEtaria, Vacina vacina) {
        this.faixaEtaria = faixaEtaria;
        this.vacina = vacina;
    }

    public FaixaVacina() {}

    public static FaixaVacina of(Vacina vacina, FaixaEtaria faixa) {
        FaixaVacina fv = new FaixaVacina();
        fv.id = new FaixaVacinaId(faixa.getId(), vacina.getId());
        fv.vacina = vacina;
        fv.faixaEtaria = faixa;
        return fv;
    }


    public FaixaVacinaId getId() {
        return id;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public FaixaEtaria getFaixaEtaria() {
        return faixaEtaria;
    }
}
