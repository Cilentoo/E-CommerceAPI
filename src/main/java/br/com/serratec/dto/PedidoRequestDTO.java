package br.com.serratec.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.serratec.enums.StatusEnum;
import br.com.serratec.model.Cliente;
import br.com.serratec.model.Pedido;
import br.com.serratec.model.ProdutoPedido;

public class PedidoRequestDTO {

	private StatusEnum status;
	private Cliente cliente;
	private Set<ProdutoPedido> produtoPedidos = new HashSet<>();
	
	public PedidoRequestDTO() {
	}
	
	
	
	public PedidoRequestDTO(StatusEnum status, Cliente cliente, Set<ProdutoPedido> produtoPedidos) {
		super();
		this.status = status;
		this.cliente = cliente;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
