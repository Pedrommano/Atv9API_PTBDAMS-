package com.seuprojeto.service;

import com.seuprojeto.exception.RecursoNaoEncontradoException;
import com.seuprojeto.model.Categoria;
import com.seuprojeto.model.Produto;
import com.seuprojeto.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // 🟢 Cadastrar nova categoria
    public Categoria salvar(Categoria categoria) {
        if (categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome da categoria é obrigatório.");
        }
        return categoriaRepository.save(categoria);
    }

    // 🔍 Buscar categoria por ID
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria com ID " + id + " não encontrada"));
    }

    // 📦 Listar produtos da categoria
    public List<Produto> listarProdutosPorCategoria(Long idCategoria) {
        Categoria categoria = buscarPorId(idCategoria);
        return categoria.getProdutos();
    }
}
