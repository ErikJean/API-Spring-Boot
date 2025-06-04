package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.MesaModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class MesaDTO {
    private Long idMesa;
    private int numero;
    private String status;

    public MesaModel toModel() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, MesaModel.class);
    }
}