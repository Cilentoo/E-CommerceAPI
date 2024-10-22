package br.com.serratec.dto;

import br.com.serratec.enums.StatusEnum;
import br.com.serratec.model.Cliente;
import br.com.serratec.model.Pedido;

public class PedidoResponseDTO {

	private StatusEnum status;
	private Cliente cliente;
	
	
	public PedidoResponseDTO() {
	}
	
	
	public PedidoResponseDTO(Pedido pedido) {
		this.status = pedido.getStatus();
		this.cliente = pedido.getCliente();
	}


	public StatusEnum getStatus() {
		return status;
	}


	public void setStatus(StatusEnum status) {
		this.status = status;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
