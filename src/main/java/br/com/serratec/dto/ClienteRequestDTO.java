package br.com.serratec.dto;

import br.com.serratec.model.Cliente;

public class ClienteRequestDTO {
	
	private String nome;
	private String cpf;
	private String email;
	private String cep;
	private String complemento;
	private String numero;
	
	public ClienteRequestDTO() {

	}
	
	public ClienteRequestDTO(Cliente cliente) {
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		this.email = cliente.getEmail();
		this.complemento = cliente.getComplemento();
		this.numero = cliente.getNumero();
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

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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
	
	
}
