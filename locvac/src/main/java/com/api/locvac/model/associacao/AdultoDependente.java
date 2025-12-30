package com.api.locvac.model.associacao;

import com.api.locvac.model.id.AdultoDependenteId;
import com.api.locvac.model.pessoa.Adulto;
import com.api.locvac.model.pessoa.Dependente;
import jakarta.persistence.*;

@Entity
@Table(name = "adulto_dependente")
public class AdultoDependente {

    @EmbeddedId
    private AdultoDependenteId id;

    @ManyToOne
    @MapsId("cdAdulto")
    private Adulto adulto;

    @ManyToOne
    @MapsId("cdDependente")
    private Dependente dependente;

    private String dsParentesco;
}
