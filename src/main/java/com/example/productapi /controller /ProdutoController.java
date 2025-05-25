package com.seuprojeto.controller;

import com.seuprojeto.dto.ProdutoDTO;
import com.seuprojeto.dto.ProdutoDescontoDTO;
import com.seuprojeto.model.Produto;
import com.seuprojeto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Cadastrar novo produto")
    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody ProdutoDTO dto) {
        Produto produto = produtoService.salvar(dto);
        return ResponseEntity.ok(produto);
    }

    @Operation(summary = "Atualizar produto por ID")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoDTO dto) {
        Produto atualizado = produtoService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Operation(summary = "Buscar produtos por nome (parcial, case-insensitive)")
    @GetMapping("/buscar")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(produtoService.buscarPorNome(nome));
    }

    @Operation(summary = "Calcular desconto de produto por ID e percentual")
    @GetMapping("/{id}/desconto")
    public ResponseEntity<ProdutoDescontoDTO> calcularDesconto(@PathVariable Long id,
                                                               @RequestParam Double percentual) {
        return ResponseEntity.ok(produtoService.calcularDesconto(id, percentual));
    }

    @Operation(summary = "Listar todos os produtos")
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }
}
