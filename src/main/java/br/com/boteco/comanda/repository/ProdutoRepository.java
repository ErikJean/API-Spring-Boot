package br.com.boteco.comanda.repository;

import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.rest.dto.ProdutoVendasDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    boolean existsByIdProduto(Long idProduto);

    /*@Query("SELECT new br.com.boteco.comanda.rest.dto.ProdutoVendasDTO(p.nome, SUM(consumo.quantidade)) " +
            "FROM ProdutoModel p " +
            "INNER JOIN ConsumoModel consumo ON p.idProduto = consumo.idProduto " +
            "INNER JOIN ComandaModel comanda ON consumo.idComanda = comanda.idComanda " +
            "WHERE consumo.dataHoraConsumo BETWEEN :dataInicio AND :dataFim AND comanda.status = 'Fechado'" +
            "GROUP BY p.nome " +
            "ORDER BY SUM(consumo.quantidade) DESC")
    List<ProdutoVendasDTO> findProdutoMaisVendidoByPeriodo(@Param("dataInicio") LocalDateTime dataInicio,
                                                           @Param("dataFim") LocalDateTime dataFim);*/
}