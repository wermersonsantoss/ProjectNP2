package com.securitysystem.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitysystem.demo.model.Funcionario;
import com.securitysystem.demo.repository.FuncionarioRepository;
import com.securitysystem.exception.FuncionarioNotFoundException;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario cadastrarFuncionario(Funcionario funcionario) {
        // Verificar se já existe um funcionário com o mesmo CPF
        Optional<Funcionario> existingFuncionario = funcionarioRepository.findByCpf(funcionario.getCpf());
        if (existingFuncionario.isPresent()) {
            throw new FuncionarioNotFoundException("CPF já cadastrado");
        }
        
        // Verificar se já existe um funcionário com a mesma matrícula
        Optional<Funcionario> existingMatricula = funcionarioRepository.findByMatricula(funcionario.getMatricula());
        if (existingMatricula.isPresent()) {
            throw new FuncionarioNotFoundException("Matrícula já cadastrada");
        }
        
        // Se não houver funcionário com o mesmo CPF ou matrícula, salvar o novo funcionário
        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }


    public Optional<Funcionario> buscarFuncionarioPorCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }

    public Optional<Funcionario> buscarFuncionarioPorIdOuCpf(Long id, String cpf) {
        if (id != null) {
            return buscarFuncionarioPorId(id);
        } else if (cpf != null) {
            return buscarFuncionarioPorCpf(cpf);
        }
        return Optional.empty();
    }

    public Funcionario atualizarFuncionario(Long id, Funcionario funcionarioAtualizado) {
        Optional<Funcionario> existingFuncionarioOptional = funcionarioRepository.findById(id);
        if (!existingFuncionarioOptional.isPresent()) {
            throw new FuncionarioNotFoundException("Funcionário não encontrado");
        }

        Funcionario existingFuncionario = existingFuncionarioOptional.get();

        existingFuncionario.setNome(funcionarioAtualizado.getNome());
        existingFuncionario.setEmail(funcionarioAtualizado.getEmail());
        existingFuncionario.setMatricula(funcionarioAtualizado.getMatricula());

        return funcionarioRepository.save(existingFuncionario);
    }

    public void deletarFuncionario(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if (funcionario.isPresent()) {
            funcionarioRepository.deleteById(id);
        } else {
            throw new FuncionarioNotFoundException("Funcionário não encontrado");
        }
    }
    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

}
