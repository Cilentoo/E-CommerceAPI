package br.com.serratec.dto;
public class ComentariosRequestDTO {
	
	private String texto;
	
	public ComentariosRequestDTO() {
	}
	public ComentariosRequestDTO(String texto) {
		super();
		this.texto = texto;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}