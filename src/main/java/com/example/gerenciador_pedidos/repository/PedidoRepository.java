package com.example.gerenciador_pedidos.repository;

import com.example.gerenciador_pedidos.service.Pedido;
import com.example.gerenciador_pedidos.service.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("Select p FROM Pedido p WHERE p.data BETWEEN :inicio AND :fim ")
    List<Pedido> buscarPorDataEntre(LocalDate inicio, LocalDate fim);

}
