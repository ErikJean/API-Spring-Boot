package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.GarcomModel;
import br.com.boteco.comanda.rest.dto.GarcomFaturamentoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositório responsável por gerenciar as operações de persistência
 * relacionadas à entidade GarcomModel.
 */
@Repository
public interface GarcomRepository extends JpaRepository<GarcomModel, Long> {

    /**
     * Verifica se existe um garçom cadastrado com o CPF especificado.
     *
     * @param cpf O CPF a ser verificado.
     * @return {@code true} se existir um garçom com o CPF fornecido, {@code false} caso contrário.
     */
    boolean existsByCpf(String cpf);

    /**
     * Verifica se existe um garçom cadastrado com o e-mail especificado.
     *
     * @param email O e-mail a ser verificado.
     * @return {@code true} se existir um garçom com o e-mail fornecido, {@code false} caso contrário.
     */
    boolean existsByEmail(String email);

    /*@Query("SELECT new br.com.boteco.comanda.rest.dto.GarcomFaturamentoDTO(g.nome, SUM(c.valorGorjeta)) " +
            "FROM GarcomModel g " +
            "INNER JOIN ComandaModel c ON c.idGarcom = g.idGarcom " +
            "WHERE c.dataHoraAbertura BETWEEN :dataInicio AND :dataFim AND c.status = 'Fechado'" +
            "GROUP BY g.nome " +
            "ORDER BY SUM(c.valorGorjeta) DESC")
    List<GarcomFaturamentoDTO> findMaiorGorjetaByPeriodo(@Param("dataInicio") LocalDateTime dataInicio,
                                                         @Param("dataFim") LocalDateTime dataFim);*/
}