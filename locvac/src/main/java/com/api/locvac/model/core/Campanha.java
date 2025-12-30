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
}
