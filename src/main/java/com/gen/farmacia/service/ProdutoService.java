package com.gen.farmacia.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gen.farmacia.model.Produto;
import com.gen.farmacia.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<Produto> buscarTodos() {
		return produtoRepository.findAll();
	}

	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public Optional<Produto> atualizar(Produto produto) {
		return produtoRepository.findById(produto.getId()).map(produtoExistente -> produtoRepository.save(produto));
	}

	public void deletar(Long id) {
		produtoRepository.deleteById(id);
	}

	public List<Produto> buscarPorNome(String nome) {
		return produtoRepository.findAllByNomeContainingIgnoreCase(nome);
	}

	public List<Produto> buscarPorDataValidade(LocalDate dataValidade) {
		return produtoRepository.findAllByDataValidade(dataValidade);
	}

	public List<Produto> buscarPorIntervaloDeValidade(LocalDate dataInicio, LocalDate dataFim) {
		return produtoRepository.findAllByDataValidadeBetween(dataInicio, dataFim);
	}

	public List<Produto> buscarPorFabricacaoNoMes(int mes, int ano) {
		return produtoRepository.findAllByDataFabricacaoInMonth(mes, ano);
	}

	public List<Produto> buscarPorValidadeNoMes(int mes, int ano) {
		return produtoRepository.findAllByDataValidadeInMonth(mes, ano);
	}

	public boolean isForaDaValidade(Produto produto) {
		return produto.getDataValidade().isBefore(LocalDate.now());
	}

	public List<Produto> buscarForaDaValidade() {
		return produtoRepository.findAll().stream().filter(this::isForaDaValidade).toList();
	}

}
