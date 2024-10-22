package br.com.serratec.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.serratec.enums.CategoriaEnum;
import br.com.serratec.model.Produto;
import br.com.serratec.model.ProdutoPedido;

public class ProdutoRequestDTO {

	private String nome;
	private Character tamanho;
	private CategoriaEnum categoria;
	private Set<ProdutoPedido> produtoPedidos = new HashSet<>();
	
	public ProdutoRequestDTO() {
	}
	
	public ProdutoRequestDTO(Produto produto) {
		this.nome = produto.getNome();
		this.tamanho = produto.getTamanho();
		this.categoria = produto.getCategoriaEnum();
	}

	public Character getTamanho() {
		return tamanho;
	}

	public void setTamanho(Character tamanho) {
		this.tamanho = tamanho;
	}

	public CategoriaEnum getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEnum categoria) {
		this.categoria = categoria;
	}

	public Set<ProdutoPedido> getProdutoPedidos() {
		return produtoPedidos;
	}

	public void setProdutoPedidos(Set<ProdutoPedido> produtoPedidos) {
		this.produtoPedidos = produtoPedidos;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
