package com.api.locvac.integration;

import com.api.locvac.integration.dto.ViaCepResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCepClient {

    private final RestTemplate restTemplate;

    public ViaCepClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean cepExiste(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        ViaCepResponse response =
                restTemplate.getForObject(url, ViaCepResponse.class);

        return response != null && response.erro() == null;
    }

}

