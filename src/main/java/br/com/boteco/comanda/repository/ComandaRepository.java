package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.rest.dto.ComandaPeriodoDTO;
import br.com.boteco.comanda.rest.dto.ConsumoTotalComandaDTO;
import br.com.boteco.comanda.rest.dto.FaturamentoTotalDTO;
import br.com.boteco.comanda.rest.dto.GarcomFaturamentoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComandaRepository extends JpaRepository<ComandaModel, Long> {
    boolean existsByIdComanda(Long idComanda);

    /*@Query("SELECT new br.com.boteco.comanda.rest.dto.FaturamentoTotalDTO(SUM(c.valorTotalComanda)) " +
            "FROM ComandaModel c " +
            "WHERE c.dataHoraAbertura BETWEEN :dataInicio AND :dataFim AND c.status = 'Fechado'")
    Optional<FaturamentoTotalDTO> findFaturamentoTotalByPeriodo(@Param("dataInicio") LocalDateTime dataInicio,
                                                                @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT new br.com.boteco.comanda.rest.dto.ComandaPeriodoDTO(c.dataHoraAbertura, c.dataHoraFechamento) " +
            "FROM ComandaModel c " +
            "WHERE c.dataHoraAbertura BETWEEN :dataInicio AND :dataFim " +
            "AND c.dataHoraFechamento BETWEEN :dataInicio AND :dataFim " +
            "AND c.status = 'Fechado'")
    List<ComandaPeriodoDTO> findComandasByPeriodo(@Param("dataInicio") LocalDateTime dataInicio,
                                                  @Param("dataFim") LocalDateTime dataFim);

    @Query("SELECT new br.com.boteco.comanda.rest.dto.ConsumoTotalComandaDTO(cm.idComanda, SUM(cs.precoTotal)) " +
            "FROM ComandaModel cm " +
            "INNER JOIN ConsumoModel cs ON cm.idComanda = cs.idComanda " +
            "WHERE cm.dataHoraAbertura BETWEEN :dataInicio AND :dataFim " +
            "AND cm.dataHoraFechamento BETWEEN :dataInicio AND :dataFim " +
            "AND cm.status = 'Fechado' " +
            "GROUP BY cm.idComanda " +
            "HAVING SUM(cs.precoTotal) = (" +
            "   SELECT MAX(totalConsumo) FROM (" +
            "       SELECT SUM(cs2.precoTotal) AS totalConsumo " +
            "       FROM ComandaModel cm2 " +
            "       INNER JOIN ConsumoModel cs2 ON cm2.idComanda = cs2.idComanda " +
            "       WHERE cm2.dataHoraAbertura BETWEEN :dataInicio AND :dataFim " +
            "       AND cm2.dataHoraFechamento BETWEEN :dataInicio AND :dataFim " +
            "       AND cm2.status = 'Fechado' " +
            "       GROUP BY cm2.idComanda" +
            "   ) subquery" +
            ")")
    List<ConsumoTotalComandaDTO> findTotalConsumoByPeriodo(@Param("dataInicio") LocalDateTime dataInicio,
                                                           @Param("dataFim") LocalDateTime dataFim);*/
}