package br.com.boteco.comanda.rest.dto;

import java.time.LocalDateTime;

public record ComandaPeriodoDTO(LocalDateTime dataHoraAbertura, LocalDateTime dataHoraFechamento) {}