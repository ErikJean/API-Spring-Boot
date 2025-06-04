package br.com.boteco.comanda.model;

import br.com.boteco.comanda.rest.dto.ComandaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "comanda")
public class ComandaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComanda")
    private Long idComanda;

    @Column(name = "dataHoraAbertura", nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    private LocalDateTime dataHoraAbertura;

    @Column(name = "dataHoraFechamento")
    private LocalDateTime dataHoraFechamento;

    @Column(name = "valorTotalComanda")
    private float valorTotalComanda;

    @Column(name = "valorGorjeta")
    private float valorGorjeta;

    @Column(name = "status", length = 255)
    private String status;

    @Column(name = "idMesa", nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    private Long idMesa;

    @Column(name = "idGarcom", nullable = false)
    @NotNull(message = "Não admite valor nulo.")
    private Long idGarcom;

    @Column(name = "idFormaPagamento")
    private Long idFormaPagamento;

    public ComandaDTO toDTO() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(this, ComandaDTO.class);
    }
}