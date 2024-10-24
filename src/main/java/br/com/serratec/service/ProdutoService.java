package br.com.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.dto.ProdutoRequestDTO;
import br.com.serratec.dto.ProdutoResponseDTO;
import br.com.serratec.enums.CategoriaEnum;
import br.com.serratec.model.Produto;
import br.com.serratec.repository.ProdutoRepository;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public List<Produto> listarPorCategoria(CategoriaEnum categoriaEnum) {
        return produtoRepository.findByCategoriaEnum(categoriaEnum);
    }
	
	public Produto buscar(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		if (produto.isPresent()) {
			return produto.get();
		}
		return null;
	}
	
	@Transactional
	public ProdutoResponseDTO save(ProdutoRequestDTO produtoDto) {
		Produto produto = new Produto();
		produto.setNome(produtoDto.getNome());
		produto.setTamanho(produtoDto.getTamanho());
		produto.setCategoriaEnum(produtoDto.getCategoria());
		//produtoRepository.save(produto);
		
		Produto produtoSalvo = produtoRepository.save(produto);
		
		return new ProdutoResponseDTO(produtoSalvo);
	}
	
	// Lista todos os produtos
	public List<Produto> listarTodos() {
	    return produtoRepository.findAll();
	}

}
