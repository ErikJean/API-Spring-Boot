package br.com.boteco.comanda.service;

import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.model.ConsumoModel;
import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.repository.ComandaRepository;
import br.com.boteco.comanda.repository.ConsumoRepository;
import br.com.boteco.comanda.repository.ProdutoRepository;
import br.com.boteco.comanda.rest.dto.ConsumoCreateDTO;
import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumoService {

    @Autowired
    private ConsumoRepository consumoRepository;
    private ComandaRepository comandaRepository;
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<ConsumoDTO> obterTodos() {
        return consumoRepository.findAll().stream()
                .map(ConsumoModel::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ConsumoDTO salvar(ConsumoCreateDTO dto) {
        try {
            // Busca os relacionamentos
            ComandaModel comanda = comandaRepository.findById(dto.getIdComanda())
                    .orElseThrow(() -> new EntityNotFoundException("Comanda não encontrada com ID: " + dto.getIdComanda()));

            ProdutoModel produto = produtoRepository.findById(dto.getIdProduto())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + dto.getIdProduto()));

            // Mapeia o DTO para a entidade
            ConsumoModel consumo = dto.toModel();
            consumo.setComanda(comanda);
            consumo.setProduto(produto);

            // Salva o consumo
            ConsumoModel salvo = consumoRepository.save(consumo);

            /*// Atualiza valor total da comanda (opcional, caso deseje manter atualizado automaticamente)
            comanda.setValorTotalComanda(comanda.getValorTotalComanda() + consumo.getPrecoTotal());
            comandaRepository.save(comanda);*/

            return salvo.toDTO();
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Erro ao salvar o consumo: verifique os dados informados.", e);
        }
    }

    @Transactional
    public ConsumoDTO atualizar(Long id, ConsumoModel consumoAtualizado) {
        if (!consumoRepository.existsById(id)) {
            throw new EntityNotFoundException("Consumo com ID " + id + " não encontrado.");
        }
        consumoAtualizado.setIdConsumo(id);
        return consumoRepository.save(consumoAtualizado).toDTO();
    }

    @Transactional
    public void deletar(Long id) {
        if (!consumoRepository.existsById(id)) {
            throw new EntityNotFoundException("Consumo com ID " + id + " não encontrado.");
        }
        consumoRepository.deleteById(id);
    }
}