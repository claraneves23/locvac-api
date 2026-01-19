package com.api.locvac.model.core;

import com.api.locvac.model.associacao.EstoqueVacina;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "vacina")
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_vacina")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_fabricante")
    private Fabricante fabricante;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tipo_vacina_id")
    private TipoVacina tipoVacina;

    @OneToMany(mappedBy = "vacina", fetch = FetchType.LAZY)
    private List<EstoqueVacina> estoques;

    private LocalDate dtFabricacao;
    private LocalDate dtValidade;

    public Vacina(){}

    public Vacina(Fabricante fabricante, TipoVacina tipoVacina, LocalDate dtFabricacao, LocalDate dtValidade) {
        this.fabricante = fabricante;
        this.tipoVacina = tipoVacina;
        this.dtFabricacao = dtFabricacao;
        this.dtValidade = dtValidade;
    }

    public Long getId() {
        return id;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public LocalDate getDtFabricacao() {
        return dtFabricacao;
    }

    public TipoVacina getTipoVacina() {return tipoVacina; }

    public LocalDate getDtValidade() {
        return dtValidade;
    }

}

