package com.securitysystem.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.securitysystem.demo.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
