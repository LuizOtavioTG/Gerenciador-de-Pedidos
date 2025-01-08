package com.example.gerenciadorDePedidos.repository;

import com.example.gerenciadorDePedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
