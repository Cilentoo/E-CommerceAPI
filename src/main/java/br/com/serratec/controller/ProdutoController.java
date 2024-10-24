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
import br.com.serratec.service.ComentarioService;
import br.com.serratec.dto.ProdutoRequestDTO;
import br.com.serratec.dto.ProdutoResponseDTO;
import br.com.serratec.enums.CategoriaEnum;
import br.com.serratec.model.Produto;
import br.com.serratec.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import br.com.serratec.model.Cliente;
import br.com.serratec.model.Comentarios;
import br.com.serratec.dto.ComentariosRequestDTO;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	 @Operation(summary = "Busca um produto por sua categoria", description = "A resposta retorna os produtos de determinada categoria")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "201", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Produto encontrado"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping("/categoria/{nomeCategoria}")
    public List<Produto> listarPorCategoria(@PathVariable String nomeCategoria) {
        CategoriaEnum categoriaEnum = CategoriaEnum.verifica(nomeCategoria); 
        return service.listarPorCategoria(categoriaEnum);  
    }
	
	@Autowired
	private ComentarioService comentarioService;
	
	 @Operation(summary = "Insere um comentario tipo avaliação para um produto por id", description = "A resposta retorna um produto com seu comentário")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "201", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Comentário postado"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping("/{id}/comentarios")
	public ResponseEntity<Comentarios> adicionarComentario(@PathVariable long id, @RequestBody ComentariosRequestDTO comentariosDTO){
		Comentarios novoComentarios = comentarioService.adicionarComentario(id, comentariosDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(novoComentarios);
	}
	
	 
	 @Operation(summary = "Lista produtos por seus ids", description = "A resposta retorna seu produto")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "201", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Produto encontrado com sucesso"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
        Produto produto = service.buscar(id);
        if (produto != null) {
            return ResponseEntity.ok(produto); 
        }
        return ResponseEntity.notFound().build(); 
    }
	
	 @Operation(summary = "Insere um novo produto", description = "A resposta retorna um produto inserido")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "201", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Cliente encontrado com sucesso"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProdutoResponseDTO> inserir(@Valid @RequestBody ProdutoRequestDTO dto) {
		ProdutoResponseDTO dtoResponse = service.save(dto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(dtoResponse);
	}

	 @Operation(summary = "Busca uma lista de produtos", description = "A resposta retorna produtos.")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "201", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Produtos encontrados"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping
	public ResponseEntity<List<Produto>> listarProdutos() {
	    List<Produto> produtos = service.listarTodos();	
	    return ResponseEntity.ok(produtos);
	}
}
