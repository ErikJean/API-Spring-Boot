package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "consumo")
public class ConsumoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConsumo")
    private Long idConsumo;

    @Column(name = "quantidade", nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    private Integer quantidade;

    @Column(name = "dataHoraConsumo", nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    private LocalDateTime dataHoraConsumo;

    @Column(name = "precoUnitarioVendido", nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    private Double precoUnitarioVendido;

    @Column(name = "precoTotal", nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    private Double precoTotal;

    @ManyToOne
    @JoinColumn(name = "idComanda", nullable = false)
    @NotNull(message = "A comanda não pode ser nula.")
    private ComandaModel comanda;

    @ManyToOne
    @JoinColumn(name = "idProduto", nullable = false)
    @NotNull(message = "O produto não pode ser nulo.")
    private ProdutoModel produto;

    private static final ModelMapper modelMapper = new ModelMapper();

    public ConsumoDTO toDTO() {
        return modelMapper.map(this, ConsumoDTO.class);
    }
}