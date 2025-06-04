package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ComandaModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
public class ComandaDTO {

    private Long idComanda;
    private LocalDateTime dataHoraAbertura;
    private LocalDateTime dataHoraFechamento;
    private float valorTotalComanda;
    private float valorGorjeta;
    private String status;
    private Long idMesa;
    private Long idGarcom;
    private Long idFormaPagamento;

    public ComandaModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ComandaModel.class);
    }
}