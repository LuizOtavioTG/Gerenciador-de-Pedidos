package com.example.gerenciadorDePedidos.repository;

import com.example.gerenciadorDePedidos.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}
