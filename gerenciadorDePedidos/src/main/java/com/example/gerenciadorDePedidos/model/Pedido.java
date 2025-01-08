package com.example.gerenciadorDePedidos.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    @ManyToMany()
    @JoinTable(
            name = "pedido_produtos",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Produto> produtos = new ArrayList<>();


    //métodos
    public void adicionarProduto (Produto produto){
        if(produto !=null && !produtos.contains(produto)){
            this.produtos.add(produto);
            produto.adicionarPedido(this);
        }
    }
    //construtor
    public Pedido(LocalDate data) {
        this.data = data;
    }
    public Pedido() {}

    //setters

    public void setData(LocalDate data) {
        this.data = data;
    }
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    //getters
    public Long getId() {
        return id;
    }
    public LocalDate getData() {
        return data;
    }
    public List<Produto> getProdutos() {
        return produtos;
    }
}
