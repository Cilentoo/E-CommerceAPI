package br.com.serratec.dto;

import br.com.serratec.enums.StatusEnum;
import br.com.serratec.model.Pedido;

public class PedidoResponseDTO {

	private StatusEnum status;
	private Long clienteId;
	
	
	public PedidoResponseDTO() {
	}
	
	
	public PedidoResponseDTO(Pedido pedido) {
		this.status = pedido.getStatus();
		this.clienteId = pedido.getCliente().getId();
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
	
	
}
