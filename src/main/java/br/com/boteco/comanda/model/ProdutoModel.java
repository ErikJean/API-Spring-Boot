package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduto")
    private Long idProduto;

    @Column(name = "nome", length = 255, nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    private String nome;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "preco", nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    private float preco;

    @Column(name = "status", length = 255, nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    @NotBlank(message = "Não admite ausência de valor.")
    private String status;

    public ProdutoDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ProdutoDTO.class);
    }
}