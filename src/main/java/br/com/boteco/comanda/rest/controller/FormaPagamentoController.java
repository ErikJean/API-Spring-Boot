package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.FormaPagamentoModel;
import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.repository.FormaPagamentoRepository;
import br.com.boteco.comanda.rest.dto.FormaPagamentoDTO;
import br.com.boteco.comanda.rest.dto.FormaPagamentoUsosDTO;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import br.com.boteco.comanda.service.FormaPagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/formaPagamento")
public class FormaPagamentoController {
    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping()
    public ResponseEntity<List<FormaPagamentoDTO>> obterTodas() {
        List<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoService.obterTodas();
        return ResponseEntity.ok(formaPagamentoDTOS);
    }

    /*@GetMapping("/formaPagamentoMaisUtilizada")
    public ResponseEntity<FormaPagamentoUsosDTO> obterFormaPagamentoMaisUtilizada(@RequestParam LocalDateTime dataInicio, @RequestParam LocalDateTime dataFim) {
        FormaPagamentoUsosDTO formaPagamentoUsosDTO = formaPagamentoService.obterFormaPagamentoMaisUtilizada(dataInicio, dataFim);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoUsosDTO);
    }*/

    @PostMapping()
    public ResponseEntity<FormaPagamentoDTO> salvar(@Valid @RequestBody FormaPagamentoModel novaFormaPagamento) {
        FormaPagamentoDTO novaFormaPagamentoDTO = formaPagamentoService.salvar(novaFormaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFormaPagamentoDTO);
    }

    @PutMapping()
    public ResponseEntity<FormaPagamentoDTO> atualizar(@Valid @RequestBody FormaPagamentoModel formaPagamentoExistente) {
        FormaPagamentoDTO formaPagamentoExistenteDTO = formaPagamentoService.atualizar(formaPagamentoExistente);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoExistenteDTO);
    }

    @DeleteMapping()
    public ResponseEntity<String> deletar(@Valid @RequestBody FormaPagamentoModel formaPagamentoExistente) {
        formaPagamentoService.deletar(formaPagamentoExistente);
        return ResponseEntity.status(HttpStatus.OK).body("Forma de pagamento excluída com sucesso.");
    }
}