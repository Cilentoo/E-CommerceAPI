package br.com.serratec.dto;
public class ProdutoPedidoDTO {
	private Long produtoId;
	private String produtoNome;
	private Integer quantidade;
	private Double valorVenda;
	private String desconto;
	public ProdutoPedidoDTO(Long produtoId, String produtoNome, Integer quantidade, Double valorVenda,
			String desconto) {
		this.produtoId = produtoId;
		this.produtoNome = produtoNome;
		this.quantidade = quantidade;
		this.valorVenda = valorVenda;
		this.desconto = desconto;
	}
	public ProdutoPedidoDTO() {
	}
	public Long getProdutoId() {
		return produtoId;
	}
	public void setProdutoId(Long produtoId) {
		this.produtoId = produtoId;
	}
	public String getProdutoNome() {
		return produtoNome;
	}
	public void setProdutoNome(String produtoNome) {
		this.produtoNome = produtoNome;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getValorVenda() {
		return valorVenda;
	}
	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}
	public String getDesconto() {
		return desconto;
	}
	public void setDesconto(String desconto) {
		this.desconto = desconto;
	}
	
	
}