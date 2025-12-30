package com.api.locvac.model.cardeneta;

import com.api.locvac.model.core.Vacina;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CadernetaDependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdDependente;

    @ManyToOne
    private Vacina vacina;

    private LocalDate dtAplicacao;
}
