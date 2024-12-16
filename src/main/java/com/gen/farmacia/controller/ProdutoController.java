package com.gen.farmacia.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gen.farmacia.model.Produto;
import com.gen.farmacia.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoService.buscarTodos());
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoService.buscarPorId(id).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome")
	public ResponseEntity<List<Produto>> getByNome(@RequestParam String nome) {
		return ResponseEntity.ok(produtoService.buscarPorNome(nome));
	}

	@GetMapping("/validade")
	public ResponseEntity<List<Produto>> getByDataValidade(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataValidade) {
		return ResponseEntity.ok(produtoService.buscarPorDataValidade(dataValidade));
	}

	@GetMapping("/validade/intervalo")
	public ResponseEntity<List<Produto>> getByDataValidadeBetween(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
		return ResponseEntity.ok(produtoService.buscarPorIntervaloDeValidade(dataInicio, dataFim));
	}

	@GetMapping("/fabricacao/mes")
	public ResponseEntity<List<Produto>> getByFabricacaoNoMes(@RequestParam int mes, @RequestParam int ano) {
		return ResponseEntity.ok(produtoService.buscarPorFabricacaoNoMes(mes, ano));
	}

	@GetMapping("/validade/mes")
	public ResponseEntity<List<Produto>> getByValidadeNoMes(@RequestParam int mes, @RequestParam int ano) {
		return ResponseEntity.ok(produtoService.buscarPorValidadeNoMes(mes, ano));
	}

	@GetMapping("/fora-validade")
	public ResponseEntity<List<Produto>> getProdutosForaDaValidade() {
		return ResponseEntity.ok(produtoService.buscarForaDaValidade());
	}

	@PostMapping
	public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(produto));
	}

	@PutMapping
	public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
		return produtoService.atualizar(produto).map(ResponseEntity::ok)
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		produtoService.deletar(id);
	}
}
