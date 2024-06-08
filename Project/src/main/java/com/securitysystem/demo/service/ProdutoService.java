package com.securitysystem.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.securitysystem.demo.dto.ProdutoDTO;
import com.securitysystem.demo.model.Produto;
import com.securitysystem.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        return null; // Lógica para criar um produto
    }

    public ProdutoDTO obterProduto(Long id) {
        return null; // Lógica para obter um produto pelo ID
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public void atualizarProduto(Long id, ProdutoDTO produtoDTO) {
        Optional<Produto> existingProdutoOptional = produtoRepository.findById(id);
        if (existingProdutoOptional.isPresent()) {
            Produto existingProduto = existingProdutoOptional.get();
            existingProduto.setNome(produtoDTO.getNome());
            existingProduto.setPreco(produtoDTO.getPreco());
            produtoRepository.save(existingProduto);
        } else {
            throw new RuntimeException("Produto não encontrado com o ID: " + id);
        }
    }
}
