package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.ProdutoRequestDTO;
import br.com.serratec.dto.ProdutoResponseDTO;
import br.com.serratec.model.Produto;
import br.com.serratec.service.ProdutoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	// Listagem por id
	@GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
        Produto produto = service.buscar(id);
        if (produto != null) {
            return ResponseEntity.ok(produto); 
        }
        return ResponseEntity.notFound().build(); 
    }
	
	// Adição de produtos(perfil: admin)
//	@PostMapping
//	public ResponseEntity<Object> inserir(@RequestBody ProdutoRequestDTO dto){
//		ProdutoResponseDTO dtoResponse = service.inserir(dto);
//		return ResponseEntity.ok(dtoResponse);
//	}	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProdutoResponseDTO> inserir(@Valid @RequestBody ProdutoRequestDTO dto) {
		ProdutoResponseDTO dtoResponse = service.save(dto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
	}

	// Listagem de todos os produtos (perfil: usuários)
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
	    List<Produto> produtos = service.listarTodos();	
	    return ResponseEntity.ok(produtos);
	}
}
