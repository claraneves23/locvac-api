package com.api.locvac.model.pessoa;

import com.api.locvac.model.enums.Sexo;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Adulto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cd_adulto;

    private String nm_adulto;
    private LocalDate dt_nascimento;
    private String ds_email;

    @Enumerated(EnumType.STRING)
    private Sexo sg_sexo;

    private String nm_cep;
}
