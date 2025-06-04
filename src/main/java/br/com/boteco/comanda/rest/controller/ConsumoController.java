package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.ConsumoModel;
import br.com.boteco.comanda.rest.dto.ConsumoCreateDTO;
import br.com.boteco.comanda.rest.dto.ConsumoDTO;
import br.com.boteco.comanda.service.ConsumoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumo")
public class ConsumoController {

    @Autowired
    private ConsumoService consumoService;

    @GetMapping()
    public ResponseEntity<List<ConsumoDTO>> obterTodos() {
        return ResponseEntity.ok(consumoService.obterTodos());
    }

    @PostMapping()
    public ResponseEntity<ConsumoDTO> salvar(@Valid @RequestBody ConsumoCreateDTO novoConsumo) {
        ConsumoDTO dto = consumoService.salvar(novoConsumo);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ConsumoModel consumoAtualizado) {
        ConsumoDTO dto = consumoService.atualizar(id, consumoAtualizado);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        consumoService.deletar(id);
        return ResponseEntity.ok("Consumo excluído com sucesso.");
    }
}
