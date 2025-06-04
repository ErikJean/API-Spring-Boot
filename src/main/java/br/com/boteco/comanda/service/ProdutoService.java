package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.repository.ProdutoRepository;
import br.com.boteco.comanda.rest.dto.ProdutoCreateDTO;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import br.com.boteco.comanda.rest.dto.ProdutoVendasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<ProdutoDTO> obterTodos() {
        List<ProdutoModel> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(p -> p.toDTO())
                .collect(Collectors.toList());
    }

    /*@Transactional(readOnly = true)
    public ProdutoVendasDTO obterProdutoMaisVendido(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return produtoRepository.findProdutoMaisVendidoByPeriodo(dataInicio, dataFim).stream().findFirst().orElseThrow(() -> new ObjectNotFoundException("Nenhum registro de produto foi encontrado entre o período de " + dataInicio + " a " + dataFim + "!"));
    }*/

    @Transactional
    public ProdutoDTO criarProduto(ProdutoCreateDTO dto) {
        try {
            ProdutoModel model = dto.toModel();
            return produtoRepository.save(model).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro ao salvar o produto: verifique os dados informados.");
        }
    }

    @Transactional
    public ProdutoDTO atualizar(ProdutoModel produtoExistente) {
        try {
            if (!produtoRepository.existsByIdProduto(produtoExistente.getIdProduto())) {
                throw new ConstraintException("O produto com o ID " + produtoExistente.getIdProduto() + " não existe na base de dados!");
            }

            return produtoRepository.save(produtoExistente).toDTO();
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar o produto " + produtoExistente.getIdProduto() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar o produto " + produtoExistente.getIdProduto() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar o produto " + produtoExistente.getIdProduto() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar o produto " + produtoExistente.getIdProduto() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar o produto" + produtoExistente.getIdProduto() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar (ProdutoModel produtoExistente) {
        try {
            if (!produtoRepository.existsByIdProduto(produtoExistente.getIdProduto())) {
                throw new ConstraintException("O produto com o ID " + produtoExistente.getIdProduto() + " não existe na base de dados!");
            }

            produtoRepository.delete(produtoExistente);

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar o produto " + produtoExistente.getIdProduto() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar o produto " + produtoExistente.getIdProduto() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar o produto " + produtoExistente.getIdProduto() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar o produto " + produtoExistente.getIdProduto() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar o produto" + produtoExistente.getIdProduto() + ". Não encontrado no banco de dados!");
        }
    }
}