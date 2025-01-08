package com.example.gerenciadorDePedidos.principal;

import com.example.gerenciadorDePedidos.model.Categoria;
import com.example.gerenciadorDePedidos.model.Fornecedor;
import com.example.gerenciadorDePedidos.model.Pedido;
import com.example.gerenciadorDePedidos.model.Produto;
import com.example.gerenciadorDePedidos.repository.CategoriaRepository;
import com.example.gerenciadorDePedidos.repository.FornecedorRepository;
import com.example.gerenciadorDePedidos.repository.PedidoRepository;
import com.example.gerenciadorDePedidos.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component  // Anotação para tornar a classe gerenciada pelo Spring
public class Principal {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    FornecedorRepository fornecedorRepository;


    // Método para salvar dados
    public void executar() {
        // Criando categorias
        Categoria categoriaEletronicos = new Categoria("Eletrônicos");
        Categoria categoriaLivros = new Categoria("Livros");
        categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        // Criando fornecedores
        Fornecedor fornecedorTech = new Fornecedor("Tech Supplier");
        Fornecedor fornecedorLivros = new Fornecedor("Livraria Global");
        fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));

        // Criando produtos e associando às categorias
        Produto produto1 = new Produto("Notebook", 3500.0, fornecedorTech);
        Produto produto2 = new Produto("Smartphone", 2500.0, fornecedorTech);
        Produto produto3 = new Produto("Livro de Java", 100.0, fornecedorLivros);
        Produto produto4 = new Produto("Livro de Spring Boot", 150.0, fornecedorLivros);

        // Associando produtos às categorias
        produto1.adicionarCategoria(categoriaEletronicos);
        produto2.adicionarCategoria(categoriaEletronicos);
        produto3.adicionarCategoria(categoriaLivros);
        produto4.adicionarCategoria(categoriaLivros);

        // Agora, salvar os produtos após associar corretamente
        produtoRepository.saveAll(List.of(produto1, produto2, produto3, produto4));

        // Criando pedidos
        Pedido pedido1 = new Pedido(LocalDate.now());
        Pedido pedido2 = new Pedido(LocalDate.now());

        // Adicionando produto a pedido
        pedido1.adicionarProduto(produto1);
        pedido2.adicionarProduto(produto2);

        // Salvar os pedidos
        pedidoRepository.saveAll(List.of(pedido1, pedido2));


        // Testando consultas e verificando os relacionamentos
//        System.out.println("Produtos na categoria Eletrônicos:");
//        categoriaRepository.findById(1L).ifPresent(categoria ->
//                categoria.getProdutoList().forEach(produto ->
//                        System.out.println(" - " + produto.getNome())
//                )
//        );
//
//        System.out.println("\nPedidos e seus produtos:");
//        pedidoRepository.findAll().forEach(pedido -> {
//            System.out.println("Pedido " + pedido.getId() + ":");
//            pedido.getProdutos().forEach(produto ->
//                    System.out.println(" - " + produto.getNome())
//            );
//        });
//
//        System.out.println("\nProdutos e seus fornecedores:");
//        produtoRepository.findAll().forEach(produto ->
//                System.out.println("Produto: " + produto.getNome() +
//                        ", Fornecedor: " + produto.getFornecedor().getNome())
//        );
    }
}
