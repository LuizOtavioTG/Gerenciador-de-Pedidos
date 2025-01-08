package com.example.gerenciadorDePedidos.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(name = "valor", nullable = false)
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToMany(mappedBy = "produtos", cascade = CascadeType.ALL)
    private List<Pedido> pedidos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;


    //métodos
    // verifica se  o pedido é nulo e se a lista ja não contem o pedido
    public void adicionarPedido(Pedido pedido) {
        if (pedido != null && !pedidos.contains(pedido)) {
            pedidos.add(pedido);
            pedido.adicionarProduto(this);
        } else {
            System.out.println("Pedido inválido ou já atribuído.");
        }
    }
    public void adicionarCategoria(Categoria categoria) {
        if (categoria != null && this.categoria != categoria) {
            this.categoria = categoria;
            categoria.adicionarProduto(this); // Sincroniza bidirecionalmente
        } else {
            System.out.println("Categoria inválida ou já atribuída.");
        }
    }
    //construtor
    public Produto() {}
    public Produto(String nome, Double preco, Fornecedor fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }
    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        if (fornecedor!=null && !fornecedor.equals(this.fornecedor)){
            this.fornecedor = fornecedor;
        } else{
            System.out.println("Fornecedor inválido ou já atribuído.");
        }
    }

    //getters
    public Categoria getCategoria() {
        return categoria;
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public Double getPreco() {
        return preco;
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
}
