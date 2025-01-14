package com.example.gerenciadorDePedidos.principal;

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

    private final Scanner scan = new Scanner(System.in);

    public void executar() {
        //métodos utilizando derived queries
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

        //métodos utilizando JPQL
        buscarProdutosComValorMaior();
        buscarProdutosOdernadorPorPrecoCrescente();
        buscarProdutosOdernadorPorPrecoDecrescente();
        buscarProdutosQueComecemComLetraEspecifica();
        buscarPedidosFeitosEntreDuasDatas();
        buscarMediaPrecosProdutos();
        buscarPrecoMaximoDeUmProdutoEmUmaCategoria();
        buscarNumeroDeProdutosPorCategoria();
        buscarCategoriasComMais10Produtos();
        buscarProdutosFiltradosPorNomeOuCategoria();

        //métodos com queries nativas
        buscarTop5ProdutosMaisCaros();







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
    private  void  buscarProdutosComValorMaior() {
        System.out.println("Digite o valor: ");
        Double valor = scan.nextDouble();
        scan.nextLine();
        List<Produto> produtos = produtoRepository.EncontrarprodutosComValorMaior(valor);
        produtos.forEach(System.out::println);
    }
    private void buscarProdutosOdernadorPorPrecoCrescente() {
        List<Produto> produtos = produtoRepository.ordenarProdutosPorPrecoCrescente();
        produtos.forEach(System.out::println);
    }
    private void buscarProdutosOdernadorPorPrecoDecrescente() {
        List<Produto> produtos = produtoRepository.ordenarProdutosPorPrecoDecrescente();
        produtos.forEach(System.out::println);
    }

    private void buscarProdutosQueComecemComLetraEspecifica() {
        System.out.println("Digite a letra: ");
        String entrada = scan.nextLine();
        char letra = entrada.charAt(0);

        List<Produto> produtos = produtoRepository.consultarProdutosQueComecemComLetraEspecifica(letra);
        produtos.forEach(p -> System.out.println("Nome do produto: " + p.getNome()));
    }
    private void buscarPedidosFeitosEntreDuasDatas() {
        System.out.println("Digite uma data no formato yyyy-MM-dd:");
        String input1 = scan.nextLine();
        LocalDate dataInicio = LocalDate.parse(input1);
        System.out.println("Digite uma data no formato yyyy-MM-dd:");
        String input2 = scan.nextLine();
        LocalDate dataFim = LocalDate.parse(input2);
        List<Pedido> pedidos = pedidoRepository.consultarPedidosFeitosEntreDuasDatas(dataInicio, dataFim);
        pedidos.forEach(System.out::println);
    }

    private void buscarMediaPrecosProdutos() {
        double media = produtoRepository.calcularMediaDosPrecosDosrodutos();
        System.out.println("A média dos preços dos produtos é " + media);
    }

    private void buscarPrecoMaximoDeUmProdutoEmUmaCategoria() {
        System.out.println("Digite uma categoria: ");
        String categoria = scan.nextLine();
        double precoMaximo = produtoRepository.encontrarPrecoMaximoDeUmProdutoEmUmaCategoria(categoria);
        System.out.println("Produto com o maior preço: \n" + precoMaximo);
    }

    private void buscarNumeroDeProdutosPorCategoria() {
        List<Object[]> resultado = produtoRepository.contarNumeroDeProdutosPorCategoria();
        for (Object[] registro : resultado) {
            String nomeCategoria = (String) registro[0];
            Long quantidadeProdutos = (Long) registro[1];
            System.out.println("Categoria: " + nomeCategoria + ", Quantidade de produtos: " + quantidadeProdutos);
        }
    }

    private void buscarCategoriasComMais10Produtos() {
        List<Object[]> resultadoBusca = produtoRepository.buscarCategoriasComMais10Produtos();
        for (Object[] registro : resultadoBusca){
            String nomeCategoria = (String) registro[0];
            Long quantidadeProdutos = (Long) registro[1];
            System.out.println("Categoria: " + nomeCategoria + ", Quantidade de produtos: " + quantidadeProdutos);
        }
    }

    private void buscarProdutosFiltradosPorNomeOuCategoria() {
        int selecao;
        String nome = null;
        String categoria = null;
        do{
            System.out.println("Digite '1' para filtrar por nome ou '2' para filtrar por categoria");
             selecao = scan.nextInt();
             scan.nextLine();
        }while(selecao != 1 && selecao != 2);


        if (selecao == 1) {
            System.out.println("Digite uma nome: ");
            nome = scan.nextLine();
        }
        if (selecao == 2) {
            System.out.println("Digite uma categoria: ");
            categoria = scan.nextLine();
        }
        List<Produto> produtos = produtoRepository.filtrarProdutosPorNomeOuCategoria(nome, categoria);
        String filtro = (selecao == 1) ? "nome" : "categoria";
        String valorFiltro = (selecao == 1) ? nome : categoria;
        System.out.printf("Produtos com %s igual a '%s':%n", filtro, valorFiltro);
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto encontrado para o filtro aplicado.");
        } else {
            produtos.forEach(System.out::println);
        }
    }

    private void buscarTop5ProdutosMaisCaros() {
        List<Produto> produtos = produtoRepository.filtrarTop5ProdutosMaisCaros();
        System.out.println("Top 5 produtos mais caros: ");
        produtos.forEach(System.out::println);
    }

}
