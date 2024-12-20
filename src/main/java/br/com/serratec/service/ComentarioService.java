package br.com.serratec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.serratec.dto.ComentariosRequestDTO;
import br.com.serratec.model.Comentarios;
import br.com.serratec.model.Produto;
import br.com.serratec.repository.ComentariosRepository;
import br.com.serratec.repository.ProdutoRepository;

@Service
public class ComentarioService {

	@Autowired
	private ComentariosRepository comentariosRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public Comentarios adicionarComentario(Long produtoId, ComentariosRequestDTO comentarioDTO) {
		Produto produto = produtoRepository.findById(produtoId)
				.orElseThrow(() -> new RuntimeException("Produto nao encontrado"));

		Comentarios comentarios = new Comentarios(produto, comentarioDTO.getTexto());

		return comentariosRepository.save(comentarios);
	}

	public Comentarios atualizarComentario(Long comentarioId, ComentariosRequestDTO comentarioDTO) {
		Comentarios comentario = comentariosRepository.findById(comentarioId)
				.orElseThrow(() -> new RuntimeException("Comentário não encontrado"));

		comentario.setTexto(comentarioDTO.getTexto());
		return comentariosRepository.save(comentario);
	}

	public void deletarComentario(Long comentarioId) {
		Comentarios comentario = comentariosRepository.findById(comentarioId)
				.orElseThrow(() -> new RuntimeException("Comentário não encontrado"));

		comentariosRepository.delete(comentario);
	}
}