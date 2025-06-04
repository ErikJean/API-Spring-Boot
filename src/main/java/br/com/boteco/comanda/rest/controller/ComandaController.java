package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.ComandaModel;
import br.com.boteco.comanda.rest.dto.*;
import br.com.boteco.comanda.service.ComandaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comanda")
public class ComandaController {
    @Autowired
    private ComandaService comandaService;

    @GetMapping()
    public ResponseEntity<List<ComandaDTO>> obterTodas(){
        List<ComandaDTO> comandaDTOS = comandaService.obterTodas();
        return ResponseEntity.ok(comandaDTOS);
    }

    /*@GetMapping("/faturamentoTotal")
    public ResponseEntity<FaturamentoTotalDTO> obterFaturamentoTotal(@RequestParam LocalDateTime dataInicio, @RequestParam LocalDateTime dataFim){
        FaturamentoTotalDTO faturamentoTotalDTO = comandaService.obterFaturamentoTotal(dataInicio,dataFim);
        return ResponseEntity.status(HttpStatus.OK).body(faturamentoTotalDTO);
    }

    @GetMapping("/tempoMedio")
    public ResponseEntity<TempoMedioDTO> obterTempoMedio(@RequestParam LocalDateTime dataInicio, @RequestParam LocalDateTime dataFim){
        TempoMedioDTO tempoMedioDTO = comandaService.obterTempoMedio(dataInicio,dataFim);
        return ResponseEntity.status(HttpStatus.OK).body(tempoMedioDTO);
    }

    @GetMapping("/comandaMaiorConsumo")
    public ResponseEntity<List<ConsumoTotalComandaDTO>> obterComandaMaiorConsumo(@RequestParam LocalDateTime dataInicio, @RequestParam LocalDateTime dataFim){
        List<ConsumoTotalComandaDTO> consumoTotalComandaDTO = comandaService.obterComandasMaiorConsumo(dataInicio,dataFim);
        return ResponseEntity.status(HttpStatus.OK).body(consumoTotalComandaDTO);
    }*/

    @PostMapping()
    public ResponseEntity<ComandaDTO> salvar(@Valid @RequestBody ComandaModel novaComanda) {
        ComandaDTO novaComandaDTO = comandaService.salvar(novaComanda);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaComandaDTO);
    }

    @PutMapping()
    public ResponseEntity<ComandaDTO> atualizar(@Valid @RequestBody ComandaModel comandaExistente) {
        ComandaDTO comandaExistenteDTO = comandaService.atualizar(comandaExistente);
        return ResponseEntity.status(HttpStatus.OK).body(comandaExistenteDTO);
    }

    @DeleteMapping
    public ResponseEntity<String> deletar(@Valid @RequestBody ComandaModel comandaExistente) {
        comandaService.deletar(comandaExistente);
        return ResponseEntity.status(HttpStatus.OK).body("Comanda excluída com sucesso.");
    }
}
