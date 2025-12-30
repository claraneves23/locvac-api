package com.api.locvac.model.core;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Fabricante {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long cdFabricante;

        private String nmFabricante;

}
