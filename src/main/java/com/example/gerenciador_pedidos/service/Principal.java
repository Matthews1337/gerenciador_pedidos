package com.example.gerenciador_pedidos.service;

import com.example.gerenciador_pedidos.repository.CategoriaRepository;
import com.example.gerenciador_pedidos.repository.PedidoRepository;
import com.example.gerenciador_pedidos.repository.ProdutoRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private ProdutoRepository produtoRepo;
    private PedidoRepository pedidoRepo;
    private CategoriaRepository categoriaRepo;
    private Scanner scanner = new Scanner(System.in);

    public Principal(ProdutoRepository produtoRepo, PedidoRepository pedidoRepo, CategoriaRepository categoriaRepo) {
        this.produtoRepo = produtoRepo;
        this.pedidoRepo = pedidoRepo;
        this.categoriaRepo = categoriaRepo;
    }
    public Principal(){}

    public void registrarPedido() {

        var opcao = -1;
        while (opcao != 0) {
            System.out.println("""
                    1 - Cadastrar pedido
                    2 - Buscar produto por preço maior que...
                    3 - Buscar produtos com menor preço
                    4 - Buscar produtos por maior preço
                    5 - Buscar produtos por letra inicial
                    6 - Buscar pedidos entre data
                    7 - Calcular média de preço dos produtos
                    
                    0 - Sair
                    """);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarPedido();
                    break;
                case 2:
                    buscarProdutoPorPrecoMaiorQue();
                    break;
                case 3:
                    buscarPorMenorPreco();
                    break;
                case 4:
                    buscarPorMaiorPreco();
                    break;
                case 5:
                    buscarProdutoPorLetra();
                    break;
                case 6:
                    buscarEntreDatas();
                    break;
                case 7:
                    calcularMediaPrecoProdutos();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
            }
        }
    }


private void cadastrarPedido() {
    System.out.println("Qual a categoria do pedido?");
    var categoriaNome = scanner.nextLine();

    Categoria categoria = categoriaRepo.findByNome(categoriaNome)
            .orElseGet(() -> new Categoria(null, categoriaNome));

    System.out.println("Qual o nome do produto?");
    var nomeProduto = scanner.nextLine();
    System.out.println("Qual o preço do produto?");
    var precoProduto = scanner.nextDouble();
    scanner.nextLine();
    Pedido pedido = new Pedido(LocalDate.now());
    Produto produto = new Produto(nomeProduto, precoProduto, categoria, pedido);

    categoriaRepo.save(categoria); // salva categoria (sem duplicar)
    pedidoRepo.save(pedido); // cascade salva produtos
    produtoRepo.save(produto);
}


    private void buscarProdutoPorPrecoMaiorQue(){
        System.out.println("Buscar por preço acima de:\n-> ");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        List<Produto> listaProdutos = produtoRepo.buscarProdutosPorPrecoMaiorQue(preco);
        listaProdutos.forEach(System.out::println);
    }

    private void buscarPorMenorPreco(){
        List<Produto> listaProdutos = produtoRepo.buscarEOrdenarPorMenorPreco();
        listaProdutos.forEach(System.out::println);

    }

    private void buscarPorMaiorPreco(){
        List<Produto> listaProdutos = produtoRepo.buscarEOrdenarPorMaiorPreco();
        listaProdutos.forEach(System.out::println);
    }

    private void buscarProdutoPorLetra(){
        System.out.println("Buscar por qual letra?\n->");
        var letra = scanner.nextLine();
        List<Produto> listaProdutos = produtoRepo.buscarProdutoPorLetra(letra);
        listaProdutos.forEach(System.out::println);
    }

    private void buscarEntreDatas() {
        try {
            System.out.print("Digite a data de INÍCIO (formato: yyyy-MM-dd): ");
            String dataInicioStr = scanner.nextLine();
            LocalDate dataInicio = LocalDate.parse(dataInicioStr);

            System.out.print("Digite a data de FIM (formato: yyyy-MM-dd): ");
            String dataFimStr = scanner.nextLine();
            LocalDate dataFim = LocalDate.parse(dataFimStr);

            List<Pedido> listaPedidos = pedidoRepo.buscarPorDataEntre(dataInicio, dataFim);

            if (listaPedidos.isEmpty()) {
                System.out.println("Nenhum pedido encontrado entre as datas informadas.");
            } else {
                System.out.println("Pedidos encontrados:");
                for (Pedido pedido : listaPedidos) {
                    System.out.println(pedido);
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Use o padrão yyyy-MM-dd.");
        } catch (Exception e) {
            System.out.println("Erro ao buscar pedidos: " + e.getMessage());
        }
    }
    private void calcularMediaPrecoProdutos(){
        Double mediaPrecoProdutos = produtoRepo.calcularMediaPrecoProdutos();
        System.out.println("O preço médio dos produtos é: " + "R$" + mediaPrecoProdutos);
    }


}
