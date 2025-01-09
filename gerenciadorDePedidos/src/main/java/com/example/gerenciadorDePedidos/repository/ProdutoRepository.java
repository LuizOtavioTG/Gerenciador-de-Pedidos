package com.example.gerenciadorDePedidos.repository;

import com.example.gerenciadorDePedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByNomeIgnoreCase(String nome);
    List<Produto> findAllByCategoriaNomeIgnoreCase(String categoriaNome);
    List<Produto> findAllByPrecoGreaterThan(Double valor);
    List<Produto> findAllByPrecoLessThan(Double valor);
    List<Produto> findAllByNomeContainingIgnoreCase(String termo);
    List<Produto> findByCategoriaNomeIgnoreCaseOrderByPrecoAsc(String categoriaNome);
    List<Produto> findByCategoriaNomeIgnoreCaseOrderByPrecoDesc(String categoriaNome);
    long countAllByCategoriaNomeIgnoreCase(String categoriaNome);
    long countByPrecoGreaterThan(Double preco);
    List<Produto> findAllByPrecoLessThanOrNomeContainingIgnoreCase(Double preco, String termo);
    List<Produto> findTop3ByOrderByPrecoDesc();
    List<Produto> findTop5ByCategoriaNomeOrderByPrecoAsc(String categoriaNome);
}
