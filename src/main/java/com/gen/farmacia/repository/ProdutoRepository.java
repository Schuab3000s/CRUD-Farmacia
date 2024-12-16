package com.gen.farmacia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gen.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

	public List<Produto> findAllByDataFabricacao(@Param("data_fabricacao") LocalDate dataFabricacao);

	public List<Produto> findAllByDataFabricacaoBetween(@Param("data_inicio") LocalDate dataInicio,
			@Param("data_fim") LocalDate dataFim);

	public List<Produto> findAllByDataValidade(@Param("data_validade") LocalDate dataValidade);

	public List<Produto> findAllByDataValidadeBetween(@Param("data_inicio") LocalDate dataInicio,
			@Param("data_fim") LocalDate dataFim);

	public List<Produto> findAllByDataValidadeAfter(@Param("data_validade") LocalDate dataValidade);

	public List<Produto> findAllByDataFabricacaoBefore(@Param("data_fabricacao") LocalDate dataFabricacao);

	public List<Produto> findAllByDataFabricacaoInMonth(@Param("mes") int mes, @Param("ano") int ano);

	public List<Produto> findAllByDataValidadeInMonth(@Param("mes") int mes, @Param("ano") int ano);

}
