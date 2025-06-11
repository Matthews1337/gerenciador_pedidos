package com.example.gerenciador_pedidos.repository;

import com.example.gerenciador_pedidos.service.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("Select p FROM Produto p WHERE p.preco > :preco")
    List<Produto> buscarProdutosPorPrecoMaiorQue(@Param("preco")Double preco);

    @Query("Select p FROM Produto p ORDER BY p.preco ASC")
    List<Produto> buscarEOrdenarPorMenorPreco();

    @Query("Select p FROM Produto p ORDER BY p.preco DESC")
    List<Produto> buscarEOrdenarPorMaiorPreco();

    @Query("Select p From Produto p WHERE p.nome ILIKE :letra%")
    List<Produto> buscarProdutoPorLetra(String letra);

    @Query("Select AVG(p.preco) FROM Produto p")
    Double calcularMediaPrecoProdutos();
}
