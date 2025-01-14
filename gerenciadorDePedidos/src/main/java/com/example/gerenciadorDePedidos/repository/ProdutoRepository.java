package com.example.gerenciadorDePedidos.repository;

import com.example.gerenciadorDePedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT p FROM Produto p WHERE p.preco > :valor")
    List<Produto> EncontrarprodutosComValorMaior(Double valor);
    @Query("SELECT p FROM Produto p ORDER BY p.preco ASC")
    List<Produto> ordenarProdutosPorPrecoCrescente();
    @Query("SELECT p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> ordenarProdutosPorPrecoDecrescente();
    @Query("SELECT p FROM Produto p WHERE p.nome ILIKE :letra%")
    List<Produto> consultarProdutosQueComecemComLetraEspecifica(char letra);
    @Query("SELECT ROUND(AVG(p.preco),0) FROM Produto p")
    double calcularMediaDosPrecosDosrodutos();
    @Query("SELECT MAX(p.preco) FROM Produto p WHERE p.categoria.nome ILIKE :categoria")
    double encontrarPrecoMaximoDeUmProdutoEmUmaCategoria(@Param("categoria") String categoria);
    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome")
    List<Object[]> contarNumeroDeProdutosPorCategoria();
    @Query("SELECT c.nome, COUNT(p) FROM Produto p JOIN p.categoria c GROUP BY c.nome HAVING COUNT(p) > 10")
    List<Object[]> buscarCategoriasComMais10Produtos();
    @Query("""
    SELECT p 
    FROM Produto p 
    WHERE (:nome IS NULL OR p.nome ILIKE :nome) 
      AND (:categoria IS NULL OR p.categoria.nome ILIKE :categoria)
    """)
    List<Produto> filtrarProdutosPorNomeOuCategoria(@Param("nome") String nome, @Param("categoria") String categoria);
    @Query(value = "SELECT * FROM Produto ORDER BY valor DESC LIMIT 5", nativeQuery = true)
    List<Produto> filtrarTop5ProdutosMaisCaros();
}
