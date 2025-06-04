package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.FormaPagamentoModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class FormaPagamentoDTO {
    private Long idFormaPagamento;
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    public FormaPagamentoModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, FormaPagamentoModel.class);
    }
}