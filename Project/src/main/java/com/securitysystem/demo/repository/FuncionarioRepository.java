package com.securitysystem.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.securitysystem.demo.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByMatricula(String matricula);
    Optional<Funcionario> findById(Long id);
}
    
    //Vai obrigar a classe a procurar por CPF ou matricula

