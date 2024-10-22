package br.com.serratec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.model.Comentarios;

public interface ComentariosRepository extends JpaRepository<Comentarios, Long>{

}
