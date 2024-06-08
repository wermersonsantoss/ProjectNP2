package com.securitysystem.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securitysystem.demo.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
