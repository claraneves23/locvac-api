package com.api.locvac.model.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fabricante {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long cd_fabricante;

        private String nm_fabricante;

}
