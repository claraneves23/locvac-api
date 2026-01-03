package com.api.locvac.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class VacinaRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private Long fabricanteId;

    private LocalDate dtFabricacao;
    private LocalDate dtValidade;
    private String descricao;

    public VacinaRequestDTO(String nome, Long fabricanteId, LocalDate dtFabricacao, LocalDate dtValidade, String descricao) {
        this.nome = nome;
        this.fabricanteId = fabricanteId;
        this.dtFabricacao = dtFabricacao;
        this.dtValidade = dtValidade;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDtValidade() {
        return dtValidade;
    }

    public LocalDate getDtFabricacao() {
        return dtFabricacao;
    }


    public Long getFabricanteId() {
        return fabricanteId;
    }

    public String getNome() {
        return nome;
    }


}
