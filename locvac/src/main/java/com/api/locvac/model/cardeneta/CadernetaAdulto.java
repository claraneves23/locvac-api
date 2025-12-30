package com.api.locvac.model.cardeneta;

import com.api.locvac.model.core.Vacina;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class CadernetaAdulto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cdAdulto;

    @ManyToOne
    private Vacina vacina;

    private LocalDate dtAplicacao;
}
