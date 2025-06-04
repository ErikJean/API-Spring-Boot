package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.MesaModel;
import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.repository.MesaRepository;
import br.com.boteco.comanda.rest.dto.MesaDTO;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import br.com.boteco.comanda.service.MesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mesa")
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @GetMapping()
    public ResponseEntity<List<MesaDTO>> obterTodas() {
        List<MesaDTO> mesaDTOS = mesaService.obterTodas();
        return ResponseEntity.ok(mesaDTOS);
    }

    @PostMapping()
    public ResponseEntity<MesaDTO> salvar(@Valid @RequestBody MesaModel novaMesa) {
        MesaDTO novaMesaDTO = mesaService.salvar(novaMesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMesaDTO);
    }

    @PutMapping()
    public ResponseEntity<MesaDTO> atualizar(@Valid @RequestBody MesaModel mesaExistente) {
        MesaDTO mesaExistenteDTO = mesaService.atualizar(mesaExistente);
        return ResponseEntity.status(HttpStatus.OK).body(mesaExistenteDTO);
    }

    @DeleteMapping()
    public ResponseEntity<String> deletar(@Valid @RequestBody MesaModel mesaExistente) {
        mesaService.deletar(mesaExistente);
        return ResponseEntity.status(HttpStatus.OK).body("Mesa excluída com sucesso.");
    }
}