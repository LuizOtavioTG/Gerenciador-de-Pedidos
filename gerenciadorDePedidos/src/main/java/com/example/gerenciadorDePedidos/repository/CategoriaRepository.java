package com.example.gerenciadorDePedidos.repository;

import com.example.gerenciadorDePedidos.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
