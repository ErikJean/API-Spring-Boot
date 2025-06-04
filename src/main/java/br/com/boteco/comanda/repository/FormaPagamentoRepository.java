package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.FormaPagamentoModel;
import br.com.boteco.comanda.rest.dto.FormaPagamentoUsosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoModel, Long> {
    boolean existsByIdFormaPagamento(Long idFormaPagamento);

    /*@Query("SELECT new br.com.boteco.comanda.rest.dto.FormaPagamentoUsosDTO(f.nome, COUNT(c.idFormaPagamento)) " +
            "FROM FormaPagamentoModel f " +
            "INNER JOIN ComandaModel c ON f.idFormaPagamento = c.idFormaPagamento " +
            "WHERE c.dataHoraFechamento BETWEEN :dataInicio AND :dataFim AND c.status = 'Fechado'" +
            "GROUP BY f.nome " +
            "ORDER BY COUNT(c.idFormaPagamento) DESC")
    public List<FormaPagamentoUsosDTO> findFormaPagamentoMaisUsadaByPeriodo(@Param("dataInicio") LocalDateTime dataInicio,
                                                                            @Param("dataFim") LocalDateTime dataFim);*/
}