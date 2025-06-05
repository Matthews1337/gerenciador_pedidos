package com.example.gerenciador_pedidos.repository;

import com.example.gerenciador_pedidos.service.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
