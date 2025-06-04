package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ConsumoModel;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class ConsumoCreateDTO {
    @NotNull
    private Integer quantidade;

    @NotNull
    private LocalDateTime dataHoraConsumo;

    @NotNull
    private Double precoUnitarioVendido;

    @NotNull
    private Double precoTotal;

    @NotNull
    private Long idComanda;

    @NotNull
    private Long idProduto;

    public ConsumoModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ConsumoModel.class);
    }
}
