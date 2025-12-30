package com.api.locvac.model.pessoa;

import com.api.locvac.model.enums.Sexo;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Dependente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cd_dependente;

    private String nm_dependente;

    @Enumerated(EnumType.STRING)
    private Sexo sg_sexo;

    private LocalDate dt_nascimento;
    private String nm_cep;
}
