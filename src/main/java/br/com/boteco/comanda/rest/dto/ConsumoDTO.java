package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ConsumoModel;
import br.com.boteco.comanda.model.ProdutoModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class ConsumoDTO {
    private Long idConsumo;
    private Integer quantidade;
    private LocalDateTime dataHoraConsumo;
    private Double precoUnitarioVendido;
    private Double precoTotal;

    private ComandaDTO comandaDTO;
    private ProdutoDTO produtoDTO;

    public ConsumoModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ConsumoModel.class);
    }
}