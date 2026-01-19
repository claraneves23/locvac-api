package com.api.locvac.model.core;

import com.api.locvac.model.associacao.TipoVacinaCepa;
import com.api.locvac.model.associacao.TipoVacinaFaixa;
import com.api.locvac.model.associacao.TipoVacinaRestricao;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class TipoVacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdTipoVacina;

    private String nmVacina;

    @Column(columnDefinition = "TEXT")
    private String dsTipoVacina;

    @OneToMany(mappedBy = "tipoVacina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoVacinaFaixa> faixasEtarias = new ArrayList<>();

    @OneToMany(mappedBy = "tipoVacina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoVacinaRestricao> restricoes = new ArrayList<>();

    @OneToMany(mappedBy = "tipoVacina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoVacinaCepa> cepas = new ArrayList<>();

    public TipoVacina(){}

    public TipoVacina(String nmVacina, String dsTipoVacina) {
        this.nmVacina = nmVacina;
        this.dsTipoVacina = dsTipoVacina;
    }

    public Long getCdTipoVacina() {
        return cdTipoVacina;
    }

    public String getNmVacina() {
        return nmVacina;
    }

    public String getDsTipoVacina() { return dsTipoVacina; }

    public List<TipoVacinaFaixa> getFaixasEtarias() {
        return faixasEtarias;
    }

    public List<TipoVacinaRestricao> getRestricoes() {
        return restricoes;
    }

    public List<TipoVacinaCepa> getCepas() { return cepas; }
}