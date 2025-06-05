package com.example.gerenciador_pedidos;

import com.example.gerenciador_pedidos.repository.CategoriaRepository;
import com.example.gerenciador_pedidos.repository.PedidoRepository;
import com.example.gerenciador_pedidos.repository.ProdutoRepository;
import com.example.gerenciador_pedidos.service.Categoria;
import com.example.gerenciador_pedidos.service.Pedido;
import com.example.gerenciador_pedidos.service.Principal;
import com.example.gerenciador_pedidos.service.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {
	@Autowired
	private PedidoRepository pedidoRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private CategoriaRepository categoriaRepo;


	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		Principal registrar = new Principal(produtoRepo, pedidoRepo, categoriaRepo);
		registrar.registrarPedido();

	}
}
