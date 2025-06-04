package br.com.boteco.comanda.rest.dto;

public record FaturamentoTotalDTO(Double valorTotalFaturado) {
    public FaturamentoTotalDTO {
        if (valorTotalFaturado == null) {
            valorTotalFaturado = 0.0;
        }
    }
}
