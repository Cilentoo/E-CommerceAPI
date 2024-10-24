package br.com.serratec.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.serratec.model.Cliente;
import br.com.serratec.model.Pedido;

public class ClienteReponseDTO {
	
	private String nome;
	private String cpf;
	private String email;
	private String complemento;
	private String numero;
	private List<PedidoResponseDTO> pedidosResponseDTO = new ArrayList<>();
	
	public ClienteReponseDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
		this.complemento = cliente.getComplemento();
		this.numero = cliente.getNumero();
		if (cliente.getPedidos() != null) {
			for (Pedido pedido : cliente.getPedidos()) {
				pedidosResponseDTO.add(new PedidoResponseDTO(pedido));
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public List<PedidoResponseDTO> getPedidosResponseDTO() {
		return pedidosResponseDTO;
	}
	public void setPedidosResponseDTO(List<PedidoResponseDTO> pedidosResponseDTO) {
		this.pedidosResponseDTO = pedidosResponseDTO;
	}
}
