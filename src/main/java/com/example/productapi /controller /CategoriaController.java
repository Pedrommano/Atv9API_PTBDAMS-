package com.seuprojeto.controller;

import com.seuprojeto.dto.CategoriaDTO;
import com.seuprojeto.model.Categoria;
import com.seuprojeto.model.Produto;
import com.seuprojeto.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Operation(summary = "Cadastrar nova categoria")
    @PostMapping
    public ResponseEntity<Categoria> criar(@Valid @RequestBody CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.getNome());
        return ResponseEntity.ok(categoriaService.salvar(categoria));
    }

    @Operation(summary = "Listar produtos de uma categoria por ID")
    @GetMapping("/{id}/produtos")
    public ResponseEntity<List<Produto>> listarProdutos(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.listarProdutosPorCategoria(id));
    }

    @Operation(summary = "Listar todas as categorias")
    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }
}
