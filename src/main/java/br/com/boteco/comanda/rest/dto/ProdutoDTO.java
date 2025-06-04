package br.com.boteco.comanda.rest.dto;

import br.com.boteco.comanda.model.ProdutoModel;
import jakarta.persistence.Column;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class ProdutoDTO {
    private Long idProduto;
    private String nome;
    private String descricao;
    private Double preco;
    private String status;

    public ProdutoModel toModel(){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ProdutoModel.class);
    }
}