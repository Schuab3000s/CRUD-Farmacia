package com.gen.farmacia.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gen.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

	public List<Produto> findAllByDataValidade(@Param("dataValidade") LocalDate dataValidade);
	
	public List<Produto> findAllByDataValidadeBetween(
		    @Param("dataInicio") LocalDate dataInicio, 
		    @Param("dataFim") LocalDate dataFim
		);

}
