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
    private String dsTelefone;

    public UnidadeSaude() {}

    public UnidadeSaude(String dsEndereco, String nmCep, String nmUnidade, Double longitude, Double latitude, String dsTelefone) {
        this.dsEndereco = dsEndereco;
        this.nmCep = nmCep;
        this.nmUnidade = nmUnidade;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dsTelefone = dsTelefone;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

    public String getDsTelefone() {
        return dsTelefone;
    }
}
