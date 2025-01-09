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
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component  // Anotação para tornar a classe gerenciada pelo Spring
public class Principal {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    private Scanner scan = new Scanner(System.in);
    // Método para salvar dados
    public void executar() {
        buscarProdutosPeloNome();
        buscarProdutosPorCategoria();
        buscarProdutosValorMaiorIndicado();
        buscarProdutosValorMenorIndicado();
        buscarProdutosPeloTermoEspecificado();
        buscarProdutosSemDataEntrega();
        buscarProdutosComDataEntrega();
        buscarCategoriaOrderPrecoAsc();
        buscarCategoriaOrderPrecoDesc();
        contarProdutosPorCategoria();
        contarProdutosComPrecoMaior();
        buscarProdutosPorPrecoOuNome();
        buscarPedidosFeitosAposData();
        buscarPedidosFeitosAntesData();
        buscarPedidosFeitosEntreDatas();
        buscarTop3ProdutosCaros();
        buscarTop5ProdutosBaratosDeUmaCategoria();




//        int opcao;
//        do {
//            var menu = """
//                    1 - Criar produtos
//
//                    0 - Sair
//                    """;
//
//            System.out.println(menu);
//            opcao = scan.nextInt();
//            scan.nextLine();
//
//            switch (opcao) {
//                case 1:
//                    criarProduto();
//                    System.out.println("\n");
//                    break;
//                case 2:
//                    criarPedido();
//                    System.out.println("\n");
//                    break;
//                case 3:
//                    criarFornecedor();
//                    System.out.println("\n");
//                    break;
//                case 4:
//                    criarCategoria();
//                    System.out.println("\n");
//                    break;
//                case 5:
//                    buscarProdutosPeloNome();
//                case 0:
//                    System.out.println("Saindo...");
//                    System.out.println("\n");
//                    break;
//                default:
//                    System.out.println("Opção inválida");
//                    System.out.println("\n");
//            }
//        }while (opcao !=0);

//        // Criando categorias
//        Categoria categoriaEletronicos = new Categoria("Eletrônicos");
//        Categoria categoriaLivros = new Categoria("Livros");
//        categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));
//
//        // Criando fornecedores
//        Fornecedor fornecedorTech = new Fornecedor("Tech Supplier");
//        Fornecedor fornecedorLivros = new Fornecedor("Livraria Global");
//        fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));
//
//        // Criando produtos e associando às categorias
//        Produto produto1 = new Produto("Notebook", 3500.0, fornecedorTech);
//        Produto produto2 = new Produto("Smartphone", 2500.0, fornecedorTech);
//        Produto produto3 = new Produto("Livro de Java", 100.0, fornecedorLivros);
//        Produto produto4 = new Produto("Livro de Spring Boot", 150.0, fornecedorLivros);
//
//        // Associando produtos às categorias
//        produto1.adicionarCategoria(categoriaEletronicos);
//        produto2.adicionarCategoria(categoriaEletronicos);
//        produto3.adicionarCategoria(categoriaLivros);
//        produto4.adicionarCategoria(categoriaLivros);
//
//        // Agora, salvar os produtos após associar corretamente
//        produtoRepository.saveAll(List.of(produto1, produto2, produto3, produto4));
//
//        // Criando pedidos
//        Pedido pedido1 = new Pedido(LocalDate.now());
//        Pedido pedido2 = new Pedido(LocalDate.now());
//
//        // Adicionando produto a pedido
//        pedido1.adicionarProduto(produto1);
//        pedido2.adicionarProduto(produto2);
//
//        // Salvar os pedidos
//        pedidoRepository.saveAll(List.of(pedido1, pedido2));


//        // Testando consultas e verificando os relacionamentos
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

    private void criarPedido() {
    }
    private void criarProduto() {

    }
    private void criarFornecedor() {

    }
    private void criarCategoria() {

    }
    private void buscarProdutosPeloNome (){
        System.out.println("Digite o nome exato do produto que você busca: ");
        String nome = scan.nextLine();
        List<Produto> produtos = produtoRepository.findAllByNomeIgnoreCase(nome);
        produtos.forEach(System.out::println);
    }
    private void buscarProdutosPorCategoria(){
        System.out.println("Digite o nome exato da categoria que você busca: ");
        String categoriaNome = scan.nextLine();
        List<Produto> produtos = produtoRepository.findAllByCategoriaNomeIgnoreCase(categoriaNome);
        System.out.println("Lista de produtos na categoria "+ categoriaNome + ": ");
        produtos.forEach(p -> System.out.println("Nome: "+p.getNome()+ " Id:"+ p.getId()));
    }
    private void buscarProdutosValorMaiorIndicado(){
        System.out.println("Digite o valor: ");
        Double valor = scan.nextDouble();
        List<Produto> produtos = produtoRepository.findAllByPrecoGreaterThan(valor);
        System.out.println("Lista de produtos com o valor maior que "+ valor + ": ");
        produtos.forEach(System.out::println);
    }
    private void buscarProdutosValorMenorIndicado(){
        System.out.println("Digite o valor: ");
        Double valor = scan.nextDouble();
        List<Produto> produtos = produtoRepository.findAllByPrecoLessThan(valor);
        System.out.println("Lista de produtos com o valor menor que "+ valor + ": ");
        produtos.forEach(System.out::println);
    }
    private  void buscarProdutosPeloTermoEspecificado (){
        System.out.println("Digite o termo que você busca: ");
        String termo = scan.nextLine();
        List<Produto> produtos = produtoRepository.findAllByNomeContainingIgnoreCase(termo);
        produtos.forEach(System.out::println);
    }
    private void buscarProdutosSemDataEntrega (){
        List<Pedido> pedidos = pedidoRepository.findByDataIsNull();
        if(!pedidos.isEmpty()){
            pedidos.forEach(System.out::println);
        }else {
            System.out.println("Todos os pedidos possuem data de entrega");
        }
    }
    private void buscarProdutosComDataEntrega(){
        List<Pedido> pedidos = pedidoRepository.findByDataIsNotNull();
        if (!pedidos.isEmpty()){
            pedidos.forEach(System.out::println);
        }else {
            System.out.println("Todos os pedidos não possuem data de entrega");
        }
    }
    private void buscarCategoriaOrderPrecoAsc(){
        System.out.println("Digite o nome exato da categoria que você busca: ");
        String categoriaNome = scan.nextLine();
        List<Produto> produtos = produtoRepository.findByCategoriaNomeIgnoreCaseOrderByPrecoAsc(categoriaNome);
        produtos.forEach(p -> System.out.println("Preço: "+ p.getPreco() +" Nome: "+p.getNome()+ " Id:"+ p.getId()));
    }
    private  void buscarCategoriaOrderPrecoDesc(){
        System.out.println("Digite o nome exato da categoria que você busca: ");
        String categoriaNome = scan.nextLine();
        List<Produto> produtos = produtoRepository.findByCategoriaNomeIgnoreCaseOrderByPrecoDesc(categoriaNome);
        produtos.forEach(p -> System.out.println("Preço: "+ p.getPreco() +" Nome: "+p.getNome()+ " Id:"+ p.getId()));
    }
    private void contarProdutosPorCategoria(){
        System.out.println("Digite o nome exato da categoria que você busca: ");
        String categoriaNome = scan.nextLine();
        long countProdutos = produtoRepository.countAllByCategoriaNomeIgnoreCase(categoriaNome);
        System.out.println("A contagem é: "+ countProdutos);
    }
    private void contarProdutosComPrecoMaior(){
        System.out.println("Digite o preço: ");
        Double preco = scan.nextDouble();
        long countProdutos = produtoRepository.countByPrecoGreaterThan(preco);
        System.out.println("A contagem é: "+ countProdutos);
    }
    private void buscarProdutosPorPrecoOuNome(){
        System.out.println("Digite o termo: ");
        String termo = scan.nextLine();
        System.out.println("Digite o valor: ");
        Double preco = scan.nextDouble();

        List<Produto> produtos = produtoRepository.findAllByPrecoLessThanOrNomeContainingIgnoreCase(preco, termo);
        System.out.println("Lista de produtos: ");
        produtos.forEach(System.out::println);
    }
    private void buscarPedidosFeitosAposData (){
        System.out.println("Digite uma data no formato yyyy-MM-dd:");
        String input = scan.nextLine();
        LocalDate data = LocalDate.parse(input);
        List<Pedido> pedidos = pedidoRepository.findByDataAfter(data);
        System.out.println("Lista de pedidos: ");
        pedidos.forEach(System.out::println);

    }
    private void buscarPedidosFeitosAntesData (){
        System.out.println("Digite uma data no formato yyyy-MM-dd:");
        String input = scan.nextLine();
        LocalDate data = LocalDate.parse(input);
        List<Pedido> pedidos = pedidoRepository.findByDataBefore(data);
        System.out.println("Lista de pedidos: ");
        pedidos.forEach(System.out::println);
    }
    private void buscarPedidosFeitosEntreDatas(){
        System.out.println("Digite uma data no formato yyyy-MM-dd:");
        String input1 = scan.nextLine();
        LocalDate dataInicio = LocalDate.parse(input1);
        System.out.println("Digite uma data no formato yyyy-MM-dd:");
        String input2 = scan.nextLine();
        LocalDate datafim = LocalDate.parse(input2);
        List<Pedido> pedidos = pedidoRepository.findByDataBetween(dataInicio, datafim);
        System.out.println("Lista de pedidos: ");
        pedidos.forEach(System.out::println);
    }
    private void buscarTop3ProdutosCaros(){
        List<Produto> produtos = produtoRepository.findTop3ByOrderByPrecoDesc();
        System.out.println("Top 3 produtos mais caros: ");
        produtos.forEach(System.out::println);
    }
    private void buscarTop5ProdutosBaratosDeUmaCategoria(){
        System.out.println("Digite uma categoria");
        String categoria = scan.nextLine();
        List<Produto> produtos = produtoRepository.findTop5ByCategoriaNomeOrderByPrecoAsc(categoria);
        System.out.println("Top 5 produtos mais baratos da categoria " + categoria + ": ");
        produtos.forEach(System.out::println);
    }
}
