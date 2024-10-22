package br.com.serratec.dto;

import java.util.stream.Stream;

import br.com.serratec.enums.StatusEnum;
import br.com.serratec.model.Pedido;

public class PedidoResponseDTO {

	private StatusEnum status;
	private Long clienteId;
	private Stream<Object> produtos;
	
	public PedidoResponseDTO() {
	}
	
	
	public PedidoResponseDTO(Pedido pedido) {
		this.status = pedido.getStatus();
		this.clienteId = pedido.getCliente().getId();
		this.setProdutos(pedido.getProdutoPedidos().stream().map(pp -> new ProdutoPedidoDTO(
				pp.getProduto().getId(),
				pp.getProduto().getNome(),
				pp.getQuantidade(),
				pp.getValorVenda(),
				pp.getDesconto())));
	}


	public StatusEnum getStatus() {
		return status;
	}


	public void setStatus(StatusEnum status) {
		this.status = status;
	}


	public Long getClienteId() {
		return clienteId;
	}


	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}


	public Stream<Object> getProdutos() {
		return produtos;
	}


	public void setProdutos(Stream<Object> produtos) {
		this.produtos = produtos;
	}
	
	
}
