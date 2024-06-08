package com.securitysystem.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.securitysystem.demo.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Métodos de consulta personalizados, se necessário
}
