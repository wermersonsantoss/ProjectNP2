package com.securitysystem.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitysystem.demo.model.Categoria;
import com.securitysystem.demo.repository.CategoriaRepository;
@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public Categoria updateCategoria(Long id, Categoria categoria) {
        Optional<Categoria> existingCategoriaOptional = categoriaRepository.findById(id);
        if (existingCategoriaOptional.isPresent()) {
            Categoria existingCategoria = existingCategoriaOptional.get();
            existingCategoria.setNome(categoria.getNome());
            // Adicione outros campos a serem atualizados, se necessário
            return categoriaRepository.save(existingCategoria);
        } else {
            // Lidar com o caso em que a categoria não existe
            throw new RuntimeException("Categoria não encontrada com o ID: " + id);
        }
    }
}
