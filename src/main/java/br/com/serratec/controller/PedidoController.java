package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.PedidoRequestDTO;
import br.com.serratec.dto.PedidoResponseDTO;
import br.com.serratec.model.Cliente;
import br.com.serratec.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;

	 @Operation(summary = "Busca uma lista de pedido", description = "A resposta retorna pedidos")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "201", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Pedidos encontrados"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping
	public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(){
		return ResponseEntity.ok(service.listarPedidos());
	}
	
	 @Operation(summary = "Insere um novo pedido", description = "A resposta retorna um pedido inserido")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "201", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Pedido inserido"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody PedidoRequestDTO dto){
		PedidoResponseDTO dtoResponse = service.inserir(dto);
		return ResponseEntity.created(null).body(dtoResponse);
	}
}
