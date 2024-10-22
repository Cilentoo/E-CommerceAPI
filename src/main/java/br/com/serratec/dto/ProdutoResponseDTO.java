package br.com.serratec.dto;

import br.com.serratec.enums.CategoriaEnum;
import br.com.serratec.model.Produto;

public class ProdutoResponseDTO {

	private String nome;
	private Character tamanho;
	private CategoriaEnum categoriaEnum;
	
	public ProdutoResponseDTO() {
	}

	public ProdutoResponseDTO(Produto produto) {
		this.nome = produto.getNome();
		this.tamanho = produto.getTamanho();
		this.categoriaEnum = produto.getCategoriaEnum();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Character getTamanho() {
		return tamanho;
	}

	public void setTamanho(Character tamanho) {
		this.tamanho = tamanho;
	}

	public CategoriaEnum getCategoriaEnum() {
		return categoriaEnum;
	}

	public void setCategoriaEnum(CategoriaEnum categoriaEnum) {
		this.categoriaEnum = categoriaEnum;
	}
	
	
}
