package com.example.gerenciadorDePedidos.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @OneToMany (mappedBy = "categoria", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto (Produto produto){
        if(produto != null && !produtos.contains(produto)){
            this.produtos.add(produto);
            produto.setCategoria(this);
        }else{
            System.out.println("Produto inválido ou já atribuído.");
        }
    }

    //construtor
    public Categoria(String nome) {
        this.nome = nome;
    }
    public Categoria() {}

    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    //getters
    public List<Produto> getProdutoList() {
        return produtos;
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

}
