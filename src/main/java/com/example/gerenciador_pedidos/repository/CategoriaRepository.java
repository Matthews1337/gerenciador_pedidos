package com.example.gerenciador_pedidos.repository;

import com.example.gerenciador_pedidos.service.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
