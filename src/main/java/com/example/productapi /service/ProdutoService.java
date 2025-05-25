package com.seuprojeto.service;

import com.seuprojeto.exception.RecursoNaoEncontradoException;
import com.seuprojeto.model.Categoria;
import com.seuprojeto.model.Produto;
import com.seuprojeto.repository.CategoriaRepository;
import com.seuprojeto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // 🟢 Cadastrar produto
    @Transactional
    public Produto salvar(Produto produto) {
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    // 🟡 Atualizar produto
    @Transactional
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado"));

        validarProduto(produtoAtualizado);
        existente.setNome(produtoAtualizado.getNome());
        existente.setPreco(produtoAtualizado.getPreco());
        existente.setCategoria(produtoAtualizado.getCategoria());

        return produtoRepository.save(existente);
    }

    // 🔍 Buscar produtos por nome (contendo, case-insensitive)
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    // 🧮 Calcular desconto
    public Produto calcularDesconto(Long id, double percentual) {
        if (percentual <= 0 || percentual > 50) {
            throw new IllegalArgumentException("Desconto inválido. Permitido apenas de 1% a 50%.");
        }

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado"));

        double precoFinal = produto.getPreco() * (1 - percentual / 100);
        produto.setPrecoFinal(precoFinal);
        produto.setDescontoAplicado(percentual);

        return produto;
    }

    // 🔐 Validações e regras de negóci
