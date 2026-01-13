package com.api.locvac.model.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UnidadeSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdUnidade;
    private Double latitude;
    private Double longitude;
    private String nmUnidade;
    private String nmCep;
    private String dsEndereco;

    public UnidadeSaude() {}

    public UnidadeSaude(String nmCep, String dsEndereco, String nmUnidade, Double longitude, Double latitude) {
        this.nmCep = nmCep;
        this.dsEndereco = dsEndereco;
        this.nmUnidade = nmUnidade;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getCdUnidade() {
        return cdUnidade;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getNmUnidade() {
        return nmUnidade;
    }

    public String getNmCep() {
        return nmCep;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }
}
