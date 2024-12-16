package com.gen.farmacia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name="tb_categorias")
@Data
public class Categoria {

	
	@Id
	@GeneratedValue()
	private Long id;
	
	@NotBlank(message="O Atributo categoria é obrigatório")
	private String categoria;
	
	private String descricao;
}
