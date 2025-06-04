package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.model.FormaPagamentoModel;
import br.com.boteco.comanda.repository.FormaPagamentoRepository;
import br.com.boteco.comanda.rest.dto.ComandaDTO;
import br.com.boteco.comanda.rest.dto.FormaPagamentoDTO;
import br.com.boteco.comanda.rest.dto.FormaPagamentoUsosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormaPagamentoService {
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Transactional(readOnly = true)
    public List<FormaPagamentoDTO> obterTodas() {
        List<FormaPagamentoModel> formasPagamento = formaPagamentoRepository.findAll();
        return formasPagamento.stream()
                .map(f -> f.toDTO())
                .collect(Collectors.toList());
    }

    /*@Transactional(readOnly = true)
    public FormaPagamentoUsosDTO obterFormaPagamentoMaisUtilizada(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return formaPagamentoRepository.findFormaPagamentoMaisUsadaByPeriodo(dataInicio, dataFim)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ObjectNotFoundException("Nenhum registro de forma de pagamento foi encontrado entre o período de " + dataInicio + " a " + dataFim + "!"));
    }*/

    @Transactional
    public FormaPagamentoDTO salvar(FormaPagamentoModel novaFormaPagamento) {
        try {
            return formaPagamentoRepository.save(novaFormaPagamento).toDTO();

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a forma de pagamento!");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar a forma de pagamento!");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível salvar a forma de pagamento. Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível salvar a forma de pagamento. Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public FormaPagamentoDTO atualizar(FormaPagamentoModel formaPagamentoExistente) {
        try {
            if (!formaPagamentoRepository.existsByIdFormaPagamento(formaPagamentoExistente.getIdFormaPagamento())) {
                throw new ConstraintException("A forma de pagamento com o ID " + formaPagamentoExistente.getIdFormaPagamento() + " não existe na base de dados!");
            }

            return formaPagamentoRepository.save(formaPagamentoExistente).toDTO();

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar a forma de pagamento " + formaPagamentoExistente.getIdFormaPagamento() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar a forma de pagamento " + formaPagamentoExistente.getIdFormaPagamento() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar a forma de pagamento " + formaPagamentoExistente.getIdFormaPagamento() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar a forma de pagamento " + formaPagamentoExistente.getIdFormaPagamento() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar a forma de pagamento" + formaPagamentoExistente.getIdFormaPagamento() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(FormaPagamentoModel formaPagamentoExistente) {
        try {
            if (!formaPagamentoRepository.existsByIdFormaPagamento(formaPagamentoExistente.getIdFormaPagamento())) {
                throw new ConstraintException("A forma de pagamento com o ID " + formaPagamentoExistente.getIdFormaPagamento() + " não existe na base de dados!");
            }

            formaPagamentoRepository.delete(formaPagamentoExistente);

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar a forma de pagamento " + formaPagamentoExistente.getIdFormaPagamento() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar a forma de pagamento " + formaPagamentoExistente.getIdFormaPagamento() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar a forma de pagamento " + formaPagamentoExistente.getIdFormaPagamento() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar a forma de pagamento " + formaPagamentoExistente.getIdFormaPagamento() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar a forma de pagamento" + formaPagamentoExistente.getIdFormaPagamento() + ". Não encontrado no banco de dados!");
        }
    }
}
