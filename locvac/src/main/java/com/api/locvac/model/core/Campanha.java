package com.api.locvac.model.core;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdCampanha;

    private String nmCampanha;

    @Column(columnDefinition = "TEXT")
    private String dsCampanha;

    private LocalDate dtComecoCampanha;
    private LocalDate dtFimCampanha;

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

    public LocalDate getDtFimCampanha() {
        return dtFimCampanha;
    }

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

    public String getDsCampanha() {
        return dsCampanha;
    }
}
