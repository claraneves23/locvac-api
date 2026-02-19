package com.api.locvac.model.core;

import com.api.locvac.model.associacao.CampanhaUnidade;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdCampanha;

    private String nmCampanha;

    private LocalDate dtComecoCampanha;
    private LocalDate dtFimCampanha;

    @OneToMany(mappedBy = "campanha", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CampanhaUnidade> unidades = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cd_tipo_vacina", nullable = false)
    private TipoVacina tipoVacina;

    public Campanha() {}

    public Campanha(String nmCampanha, LocalDate dtComecoCampanha, LocalDate dtFimCampanha, TipoVacina tipoVacina) {
        this.nmCampanha = nmCampanha;
        this.dtComecoCampanha = dtComecoCampanha;
        this.dtFimCampanha = dtFimCampanha;
        this.tipoVacina = tipoVacina;
    }

    public void setTipoVacina(TipoVacina tipoVacina) {
        this.tipoVacina = tipoVacina;
    }

    public void setUnidades(List<CampanhaUnidade> unidades) {
        this.unidades = unidades;
    }

    public void setDtComecoCampanha(LocalDate dtComecoCampanha) {
        this.dtComecoCampanha = dtComecoCampanha;
    }

    public void setNmCampanha(String nmCampanha) {
        this.nmCampanha = nmCampanha;
    }

    public void setDtFimCampanha(LocalDate dtFimCampanha) {
        this.dtFimCampanha = dtFimCampanha;
    }
    public LocalDate getDtFimCampanha() {return dtFimCampanha;}

    public LocalDate getDtComecoCampanha() {
        return dtComecoCampanha;
    }

    public String getNmCampanha() {
        return nmCampanha;
    }

    public Long getCdCampanha() {
        return cdCampanha;
    }

    public TipoVacina getTipoVacina() {
        return tipoVacina;
    }

    public List<CampanhaUnidade> getUnidades() {
        return unidades;
    }
}
