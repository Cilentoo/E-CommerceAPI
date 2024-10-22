package br.com.serratec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.ProdutoRequestDTO;
import br.com.serratec.dto.ProdutoResponseDTO;
import br.com.serratec.model.Produto;
import br.com.serratec.service.ProdutoService;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
        Produto produto = service.buscar(id);
        if (produto != null) {
            return ResponseEntity.ok(produto); 
        }
        return ResponseEntity.notFound().build(); 
    }
	
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody ProdutoRequestDTO dto){
		ProdutoResponseDTO dtoResponse = service.inserir(dto);
		return ResponseEntity.ok(dtoResponse);
	}
}
