package br.com.serratec.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import br.com.serratec.enums.CategoriaEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;	
	private Character tamanho;
	private Double preco;
	
	@Enumerated(EnumType.STRING)
	private CategoriaEnum categoriaEnum;
	
	@JsonBackReference
	@OneToMany(mappedBy = "id.produto")
	private Set<ProdutoPedido> produtoPedidos = new HashSet<>();
	
	@JsonManagedReference
	@OneToMany(mappedBy = "produto")
	private Set<Comentarios> comentarios = new HashSet<>(); 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public Character getTamanho() {
		return tamanho;
	}

	public void setTamanho(Character tamanho) {
		this.tamanho = tamanho;
	}

	public CategoriaEnum getCategoriaEnum() {
		return categoriaEnum;
	}

	public void setCategoriaEnum(CategoriaEnum categoriaEnum) {
		this.categoriaEnum = categoriaEnum;
	}

	public Set<ProdutoPedido> getProdutoPedidos() {
		return produtoPedidos;
	}

	public void setProdutoPedidos(Set<ProdutoPedido> produtoPedidos) {
		this.produtoPedidos = produtoPedidos;
	}
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Set<Comentarios> getComentarios() {
		return comentarios;
	}
	public void setComentarios(Set<Comentarios> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
