package com.example.gerenciadorDePedidos.model;

import jakarta.persistence.*;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    //métodos
    
    //construtor
    public Fornecedor() {
    }
    public Fornecedor(String nome) {
        this.nome = nome;
    }


    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    //getters
    public String getNome() {
        return nome;
    }
    public Long getId() {
        return id;
    }
}
