package com.securitysystem.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securitysystem.demo.model.Pedido;
import com.securitysystem.demo.repository.PedidoRepository;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void deleteById(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido update(Long id, Pedido pedido) {
        Optional<Pedido> existingPedidoOptional = pedidoRepository.findById(id);
        if (existingPedidoOptional.isPresent()) {
            Pedido existingPedido = existingPedidoOptional.get();
            existingPedido.setCliente(pedido.getCliente());
            existingPedido.setProduto(pedido.getProduto());
            existingPedido.setData(pedido.getData());
            existingPedido.setTotal(pedido.getTotal());
            return pedidoRepository.save(existingPedido);
        } else {
            throw new RuntimeException("Pedido não encontrado com o ID: " + id);
        }
    }
}
