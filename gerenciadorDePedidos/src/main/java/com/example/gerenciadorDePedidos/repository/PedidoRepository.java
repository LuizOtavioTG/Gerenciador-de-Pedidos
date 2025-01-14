package com.example.gerenciadorDePedidos.repository;

import com.example.gerenciadorDePedidos.model.Pedido;
import com.example.gerenciadorDePedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByDataIsNull();
    List<Pedido> findByDataIsNotNull();
    List<Pedido> findByDataAfter(LocalDate data);
    List<Pedido> findByDataBefore(LocalDate data);
    List<Pedido> findByDataBetween(LocalDate data1, LocalDate data2);
    @Query("SELECT p FROM Pedido p WHERE p.data BETWEEN :inicio AND :fim")
    List<Pedido> consultarPedidosFeitosEntreDuasDatas(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
