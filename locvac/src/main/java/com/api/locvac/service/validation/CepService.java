package com.api.locvac.service.validation;

import com.api.locvac.integration.ViaCepClient;
import org.springframework.stereotype.Service;

@Service
public class CepService {

    private final ViaCepClient viaCepClient;

    public CepService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public void validarCep(String cep) {
        if (!viaCepClient.cepExiste(cep)) {
            throw new RuntimeException("CEP inválido");
        }
    }
}
