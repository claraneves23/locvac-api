package com.api.locvac;

import com.api.locvac.dto.UnidadeSaudeRequestDTO;
import com.api.locvac.model.core.UnidadeSaude;
import com.api.locvac.repository.UnidadeSaudeRepository;
import com.api.locvac.service.impl.UnidadeSaudeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UnidadeSaudeServiceImplTest {

    @InjectMocks
    private UnidadeSaudeServiceImpl unidadeSaudeServiceImpl;

    @Mock
    private UnidadeSaudeRepository unidadeSaudeRepository;

    @Test
    public void deveAtualizarObjeto(){


    }
}
