package br.com.boteco.comanda.rest.dto;

public record TempoMedioDTO(Long tempoMedioPermanencia) {
    public TempoMedioDTO {
        if (tempoMedioPermanencia == null) {
            tempoMedioPermanencia = 0L;
        }
    }
}