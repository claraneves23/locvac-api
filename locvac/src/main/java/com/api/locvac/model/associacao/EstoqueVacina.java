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

    public EstoqueVacina() {}

    public EstoqueVacina(Integer quantidade, UnidadeSaude unidadeSaude, Vacina vacina) {
        this.quantidade = quantidade;
        this.unidadeSaude = unidadeSaude;
        this.vacina = vacina;
    }

    public static EstoqueVacina of(UnidadeSaude unidadeSaude, Vacina vacina, Integer quantidade){
        EstoqueVacina ev = new EstoqueVacina();

        ev.id = new EstoqueVacinaId(unidadeSaude.getCdUnidade(),
                vacina.getId());

        ev.unidadeSaude = unidadeSaude;
        ev.vacina = vacina;
        ev.quantidade = quantidade;

        return ev;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public EstoqueVacinaId getId() {
        return id;
    }

    public Vacina getVacina() {
        return vacina;
    }

    public UnidadeSaude getUnidadeSaude() {
        return unidadeSaude;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}

