package com.example.gerenciadorDePedidos.repository;

import com.example.gerenciadorDePedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
