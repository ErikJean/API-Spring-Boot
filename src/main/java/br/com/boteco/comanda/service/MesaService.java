package br.com.boteco.comanda.service;

import br.com.boteco.comanda.exception.*;
import br.com.boteco.comanda.model.FormaPagamentoModel;
import br.com.boteco.comanda.model.MesaModel;
import br.com.boteco.comanda.repository.MesaRepository;
import br.com.boteco.comanda.rest.dto.FormaPagamentoDTO;
import br.com.boteco.comanda.rest.dto.MesaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;

    @Transactional(readOnly = true)
    public List<MesaDTO> obterTodas() {
        List<MesaModel> mesas = mesaRepository.findAll();
        return mesas.stream()
                .map(m -> m.toDTO())
                .collect(Collectors.toList());
    }

    @Transactional
    public MesaDTO salvar(MesaModel novaMesa) {
        try {
            return mesaRepository.save(novaMesa).toDTO();

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível salvar a mesa!");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro de restrição de integridade ao salvar a mesa!");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível salvar a mesa. Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível salvar a mesa. Falha na conexão com o banco de dados!");
        }
    }

    @Transactional
    public MesaDTO atualizar(MesaModel mesaExistente) {
        try {
            if (!mesaRepository.existsByIdMesa(mesaExistente.getIdMesa())) {
                throw new ConstraintException("A mesa com o ID " + mesaExistente.getIdMesa() + " não existe na base de dados!");
            }

            return mesaRepository.save(mesaExistente).toDTO();

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível atualizar a mesa " + mesaExistente.getIdMesa() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao atualizar a mesa " + mesaExistente.getIdMesa() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível atualizar a mesa " + mesaExistente.getIdMesa() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível atualizar a mesa " + mesaExistente.getIdMesa() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível atualizar a mesa" + mesaExistente.getIdMesa() + ". Não encontrado no banco de dados!");
        }
    }

    @Transactional
    public void deletar(MesaModel mesaExistente) {
        try {
            if (!mesaRepository.existsByIdMesa(mesaExistente.getIdMesa())) {
                throw new ConstraintException("A mesa com o ID " + mesaExistente.getIdMesa() + " não existe na base de dados!");
            }

            mesaRepository.delete(mesaExistente);

        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Erro! Não foi possível deletar a mesa " + mesaExistente.getIdMesa() + " !");
        } catch (ConstraintException e) {
            // Relança a mensagem original ou adiciona contexto
            if (e.getMessage() == null || e.getMessage().isBlank()) {
                throw new ConstraintException("Erro ao deletar a mesa " + mesaExistente.getIdMesa() + ": Restrição de integridade de dados.");
            }
            throw e;
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException("Erro! Não foi possível deletar a mesa " + mesaExistente.getIdMesa() + ". Violação de regra de negócio!");
        } catch (SQLException e) {
            throw new SQLException("Erro! Não foi possível deletar a mesa " + mesaExistente.getIdMesa() + ". Falha na conexão com o banco de dados!");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Erro! Não foi possível deletar a mesa" + mesaExistente.getIdMesa() + ". Não encontrado no banco de dados!");
        }
    }
}
