package com.example.gerenciador_pedidos.service;

import com.example.gerenciador_pedidos.repository.CategoriaRepository;
import com.example.gerenciador_pedidos.repository.PedidoRepository;
import com.example.gerenciador_pedidos.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

    private ProdutoRepository produtoRepo;
    private PedidoRepository pedidoRepo;
    private CategoriaRepository categoriaRepo;

    public Principal(ProdutoRepository produtoRepo, PedidoRepository pedidoRepo, CategoriaRepository categoriaRepo) {
        this.produtoRepo = produtoRepo;
        this.pedidoRepo = pedidoRepo;
        this.categoriaRepo = categoriaRepo;
    }
    public Principal(){}

    public void registrarPedido(){
        Scanner scanner = new Scanner(System.in);
        var opcao = -1;
        while (opcao != 0) {
            System.out.println("""
                    Deseja fazer cadastrar pedidos?
                    1 - Cadastrar pedido
                    0 - Sair
                    """);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 1:
                    System.out.println("Qual a categoria do pedido?");
                    var categoriaPedido = scanner.nextLine();
                    Categoria categoria = new Categoria(categoriaPedido);

                    System.out.println("Qual o nome do produto?");
                    var nomeProduto = scanner.nextLine();
                    System.out.println("Qual o preço do produto?");
                    String inputPreco = scanner.nextLine();
                    double precoProduto = Double.parseDouble(inputPreco);
                    Produto produto = new Produto(nomeProduto, precoProduto);

                    Pedido pedido = new Pedido(LocalDate.now());
                    produtoRepo.save(produto);
                    categoriaRepo.save(categoria);
                    pedidoRepo.save(pedido);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
            }

        }
    }
}
