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
	
	
	public ProdutoPedido() {
		
	}
	
	public ProdutoPedido(Pedido pedido, Produto produto , ProdutoPedidoPK id, Double valorVenda, Double desconto, Integer quantidade) {
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.valorVenda = valorVenda;
		this.desconto = desconto;
		this.quantidade = quantidade;
	}
	
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
	
	public Produto getProduto() {
		return id.getProduto();
	}
	
	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public void  setProduto(Produto produto) {
		this.id.setProduto(produto);
	}
	
	public void setPedido(Pedido pedido) {
		this.id.setPedido(pedido);
	}
	
}
