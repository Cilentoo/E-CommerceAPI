package br.com.serratec.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class ProdutoPedido {

	@EmbeddedId
	private ProdutoPedidoPK id = new ProdutoPedidoPK();
	
	private Double valorVenda;
	private Double desconto;
	private Integer quantidade;
	
	public ProdutoPedidoPK getId() {
		return id;
	}
	public void setId(ProdutoPedidoPK id) {
		this.id = id;
	}
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
