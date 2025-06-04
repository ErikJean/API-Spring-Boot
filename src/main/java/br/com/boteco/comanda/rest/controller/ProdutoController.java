package br.com.boteco.comanda.rest.controller;

import br.com.boteco.comanda.model.ProdutoModel;
import br.com.boteco.comanda.rest.dto.ProdutoCreateDTO;
import br.com.boteco.comanda.rest.dto.ProdutoDTO;
import br.com.boteco.comanda.rest.dto.ProdutoVendasDTO;
import br.com.boteco.comanda.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping()
    public ResponseEntity<List<ProdutoDTO>> obterTodos() {
        List<ProdutoDTO> produtoDTOS = produtoService.obterTodos();
        return ResponseEntity.ok(produtoDTOS);
    }

    /*@GetMapping("/produtoMaisVendido")
    public ResponseEntity<ProdutoVendasDTO> obterProdutoMaisVendido(@RequestParam LocalDateTime dataInicio, @RequestParam LocalDateTime dataFim) {
        ProdutoVendasDTO produtoVendasDTO = produtoService.obterProdutoMaisVendido(dataInicio, dataFim);
        return ResponseEntity.status(HttpStatus.OK).body(produtoVendasDTO);
    }*/

    @PostMapping()
    public ResponseEntity<ProdutoDTO> criarProduto(@Valid @RequestBody ProdutoCreateDTO produtoCreateDTO) {
        ProdutoDTO produtoDTO = produtoService.criarProduto(produtoCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTO);
    }

    @PutMapping()
    public ResponseEntity<ProdutoDTO> atualizar(@Valid @RequestBody ProdutoModel produtoExistente) {
        ProdutoDTO produtoExistenteDTO = produtoService.atualizar(produtoExistente);
        return ResponseEntity.status(HttpStatus.OK).body(produtoExistenteDTO);
    }

    @DeleteMapping()
    public ResponseEntity<String> deletar(@Valid @RequestBody ProdutoModel produtoExistente) {
        produtoService.deletar(produtoExistente);
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso.");
    }
}