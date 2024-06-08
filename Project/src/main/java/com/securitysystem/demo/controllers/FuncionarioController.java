package com.securitysystem.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securitysystem.demo.model.Funcionario;
import com.securitysystem.demo.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping
	public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
		Funcionario novoFuncionario = funcionarioService.cadastrarFuncionario(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoFuncionario);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Funcionario> atualizarFuncionario(@PathVariable Long id,
			@RequestBody Funcionario funcionario) {
		Funcionario funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, funcionario);
		return ResponseEntity.ok(funcionarioAtualizado);
	}

	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Funcionario>> listarFuncionarios() {
		List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
		Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorIdOuCpf(id, null);
		return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/cpf/{cpf}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Funcionario> buscarFuncionarioPorCpf(@PathVariable String cpf) {
		Optional<Funcionario> funcionario = funcionarioService.buscarFuncionarioPorIdOuCpf(null, cpf);
		return funcionario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
		funcionarioService.deletarFuncionario(id);
		return ResponseEntity.noContent().build();
	}

}





















/*	<!-- Flyway Core -->
		<dependency>
			<groupId>org.flywaydb</groupId>
			<artifactId>flyway-core</artifactId>
		</dependency>*/



