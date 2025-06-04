package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.repository.ComandaRepository;
import br.com.boteco.comanda.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComandaService {
    @Autowired
    private ComandaRepository comandaRepository;

    @Transactional(readOnly = true)
    public List<ComandaDTO> obterTodas() {
        List<ComandaModel> comandas = comandaRepository.findAll();
        return comandas.stream()
                .map(c -> c.toDTO())
                .collect(Collectors.toList());
    }

    /*@Transactional(readOnly = true)
    public FaturamentoTotalDTO obterFaturamentoTotal(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return comandaRepository.findFaturamentoTotalByPeriodo(dataInicio, dataFim).orElseThrow(() -> new ObjectNotFoundException("Nenhum registro foi encontrado entre o período de " + dataInicio + " a " + dataFim + "!"));
    }

    @Transactional(readOnly = true)
    public TempoMedioDTO obterTempoMedio(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<ComandaPeriodoDTO> comandas = comandaRepository.findComandasByPeriodo(dataInicio, dataFim);

        if (comandas.isEmpty()) {
            return new TempoMedioDTO(0L);
        }

        Long soma = 0L;
        int count = 0;

        for (ComandaPeriodoDTO c : comandas) {
            long minutos = ChronoUnit.MINUTES.between(c.dataHoraAbertura(), c.dataHoraFechamento());
            soma += minutos;
            count++;
        }

        if (count > 0 ) {
            return new TempoMedioDTO(soma / count);
        }

        else {
            return new TempoMedioDTO(0L);
        }
    }

    @Transactional(readOnly = true)
    public List<ConsumoTotalComandaDTO> obterComandasMaiorConsumo(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<ConsumoTotalComandaDTO> comandas = comandaRepository.findTotalConsumoByPeriodo(dataInicio, dataFim);

        if (comandas.isEmpty()) {
            throw new ObjectNotFoundException("Nenhum registro de comanda com consumo foi encontrado entre o período de " + dataInicio + " a " + dataFim + "!");
        }

        return comandas;
    }*/

    @Transactional
    public ComandaDTO salvar(ComandaModel novaComanda) {
        try {
            return comandaRepository.save(novaComanda).toDTO();

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a comanda!");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar a comanda!");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível salvar a comanda. Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível salvar a comanda. Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public ComandaDTO atualizar(ComandaModel comandaExistente) {
        try {
            if (!comandaRepository.existsByIdComanda(comandaExistente.getIdComanda())) {
                throw new ConstraintException("A comanda com o ID " + comandaExistente.getIdComanda() + " não existe na base de dados!");
            }

            return comandaRepository.save(comandaExistente).toDTO();

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar a comanda " + comandaExistente.getIdComanda() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar a comanda " + comandaExistente.getIdComanda() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar a comanda " + comandaExistente.getIdComanda() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar a comanda " + comandaExistente.getIdComanda() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar a comanda" + comandaExistente.getIdComanda() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(ComandaModel comandaExistente) {
        try {
            if (!comandaRepository.existsByIdComanda(comandaExistente.getIdComanda())) {
                throw new ConstraintException("A comanda com o ID " + comandaExistente.getIdComanda() + " não existe na base de dados!");
            }

            comandaRepository.delete(comandaExistente);

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar a comanda " + comandaExistente.getIdComanda() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar a comanda " + comandaExistente.getIdComanda() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar a comanda " + comandaExistente.getIdComanda() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar a comanda " + comandaExistente.getIdComanda() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar a comanda" + comandaExistente.getIdComanda() + ". Não encontrado no banco de dados!");
        }
    }
}