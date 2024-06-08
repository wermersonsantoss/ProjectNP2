package com.securitysystem.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitysystem.demo.dto.ClienteDTO;
import com.securitysystem.demo.model.Cliente;
import com.securitysystem.demo.repository.ClienteRepository;
import com.securitysystem.exception.ClienteNaoEncontradoException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;

	public ClienteDTO saveCliente(ClienteDTO clienteDTO) {
		Cliente cliente = toEntity(clienteDTO);
		cliente = clienteRepository.save(cliente);
		return toDTO(cliente);
	}

	public List<ClienteDTO> getAllClientes() {
		return clienteRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Optional<ClienteDTO> getClienteById(Long id) {
		Optional<Cliente> clienteOpt = clienteRepository.findById(id);
		if (clienteOpt.isPresent()) {
			return Optional.of(toDTO(clienteOpt.get()));
		} else {
			throw new ClienteNaoEncontradoException("Cliente com id " + id + " não encontrado.");
		}
	}

	public void deleteCliente(Long id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
		} else {
			throw new ClienteNaoEncontradoException("Cliente com id " + id + " não encontrado.");
		}
	}

	private Cliente toEntity(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setId(clienteDTO.getId());
		cliente.setNome(clienteDTO.getNome());
		cliente.setDatanasc(clienteDTO.getDatanasc());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setRg(clienteDTO.getRg());
		return cliente;
	}

	private ClienteDTO toDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setId(cliente.getId());
		clienteDTO.setNome(cliente.getNome());
		clienteDTO.setDatanasc(cliente.getDatanasc());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setRg(cliente.getRg());
		return clienteDTO;
	}

	public void updateCliente(Long id, ClienteDTO clienteDTO) {
		Optional<Cliente> existingClienteOptional = clienteRepository.findById(id);
		if (existingClienteOptional.isPresent()) {
			Cliente existingCliente = existingClienteOptional.get();
			existingCliente.setNome(clienteDTO.getNome());
			existingCliente.setDatanasc(clienteDTO.getDatanasc());
			existingCliente.setCpf(clienteDTO.getCpf());
			existingCliente.setRg(clienteDTO.getRg());
			// Adicione outros campos a serem atualizados, se necessário
			clienteRepository.save(existingCliente);
		} else {
			// Lidar com o caso em que o cliente não existe
			throw new RuntimeException("Cliente não encontrado com o ID: " + id);
		}
	}

}
