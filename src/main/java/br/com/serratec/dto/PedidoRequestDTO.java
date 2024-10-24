package br.com.serratec.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.serratec.enums.StatusEnum;
import br.com.serratec.model.Pedido;
import br.com.serratec.model.ProdutoPedido;

public class PedidoRequestDTO {

	private StatusEnum status;
	private Long clienteId;
	private Set<ProdutoPedido> produtoPedidos = new HashSet<>();
	
	public PedidoRequestDTO() {
	}
	
	
	
	public PedidoRequestDTO(StatusEnum status, Set<ProdutoPedido> produtoPedidos, Long clienteId) {
		super();
		this.status = status;
		this.clienteId = clienteId;
		this.produtoPedidos = produtoPedidos;
	}



	public PedidoRequestDTO(Pedido pedido) {
		this.status = pedido.getStatus();
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public Set<ProdutoPedido> getProdutoPedidos() {
		return produtoPedidos;
	}

	public void setProdutoPedidos(Set<ProdutoPedido> produtoPedidos) {
		this.produtoPedidos = produtoPedidos;
	}



	public Long getClienteId() {
		return clienteId;
	}



	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	
	
}
