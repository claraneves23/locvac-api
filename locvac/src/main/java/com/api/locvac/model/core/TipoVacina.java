package com.api.locvac.model.core;

import com.api.locvac.model.associacao.TipoVacinaCepa;
import com.api.locvac.model.associacao.TipoVacinaFaixa;
import com.api.locvac.model.associacao.TipoVacinaRestricao;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class TipoVacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdTipoVacina;

    private String nmVacina;

    @OneToMany(mappedBy = "tipoVacina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoVacinaFaixa> faixasEtarias;

    @OneToMany(mappedBy = "tipoVacina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoVacinaRestricao> restricoes;

    @OneToMany(mappedBy = "tipoVacina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TipoVacinaCepa> cepas;

    public TipoVacina(){}

    public TipoVacina(List<TipoVacinaCepa> cepas, List<TipoVacinaRestricao> restricoes, List<TipoVacinaFaixa> faixasEtarias, String nmVacina) {
        this.cepas = cepas;
        this.restricoes = restricoes;
        this.faixasEtarias = faixasEtarias;
        this.nmVacina = nmVacina;
    }

    public Long getCdTipoVacina() {
        return cdTipoVacina;
    }

    public String getNmVacina() {
        return nmVacina;
    }

    public List<TipoVacinaFaixa> getFaixasEtarias() {
        return faixasEtarias;
    }
    public List<TipoVacinaRestricao> getRestricoes() {
        return restricoes;
    }

    public List<TipoVacinaCepa> getCepas() {
        return cepas;
    }
}