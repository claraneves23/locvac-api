package com.api.locvac.utils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Utilitário simplificado para atualizar entidades associativas many-to-many.
 */
public class AssociacaoUtils {

    /**
     * Atualiza uma coleção de entidades associativas de forma genérica.
     *
     * @param <A>                    tipo da entidade associativa
     * @param <E>                    tipo da entidade associada (ex: UnidadeSaude, Cepa)
     * @param <ID>                   tipo do ID
     *
     * @param colecaoAtual           coleção atual de associações
     * @param novasIds               lista de novos IDs para associar
     * @param idExtractor            função para extrair ID da entidade associativa
     * @param entityFinder           função para encontrar uma entidade por ID
     * @param associacaoFactory      função para criar uma nova associação dado a entidade
     */
    public static <A, E, ID> void atualizar(
            Collection<A> colecaoAtual,
            List<ID> novasIds,
            Function<A, ID> idExtractor,
            Function<ID, Optional<E>> entityFinder,
            Function<E, A> associacaoFactory) {

        if (novasIds == null) {
            return;
        }

        Set<ID> novosIds = new HashSet<>(novasIds);

        // IDs atuais
        Set<ID> atuaisIds = colecaoAtual.stream()
                .map(idExtractor)
                .collect(Collectors.toSet());

        // Remove associações não presentes nos novos IDs
        colecaoAtual.removeIf(a -> !novosIds.contains(idExtractor.apply(a)));

        // IDs a adicionar
        List<ID> idsAdicionar = novasIds.stream()
                .filter(id -> !atuaisIds.contains(id))
                .toList();

        // Busca entidades e cria novas associações
        for (ID id : idsAdicionar) {
            E entidade = entityFinder.apply(id)
                    .orElseThrow(() -> new IllegalArgumentException("Entidade com ID " + id + " não encontrada"));
            colecaoAtual.add(associacaoFactory.apply(entidade));
        }
    }
}

