package com.example.gerenciador_pedidos.repository;

import com.example.gerenciador_pedidos.service.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
