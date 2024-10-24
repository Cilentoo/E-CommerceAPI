package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.CadastroRequestDTO;
import br.com.serratec.dto.ClienteReponseDTO;
import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.dto.LoginRequestDTO;
import br.com.serratec.dto.LoginResponseDTO;
import br.com.serratec.model.Cliente;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.security.TokenService;
import br.com.serratec.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	
	private final ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
    @Operation(summary = "Busca um cliente e seus pedidos", description = "A resposta retorna um cliente e seus pedidos.")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", 
			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
			description = "Cliente encontrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })

	@GetMapping("/{id}")
	public ResponseEntity<ClienteReponseDTO> listarPorId(@PathVariable Long id) {
		ClienteReponseDTO ClienteDTO = service.listarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(ClienteDTO);
	}
    
  
    
    @Operation(summary = "Busca um cliente", description = "A resposta retorna um cliente.")
   	@ApiResponses(value = { 
   			@ApiResponse(responseCode = "201", 
   			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
   			description = "Cliente encontrado com sucesso"),
   			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
   			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
   			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
   			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @GetMapping
    public ResponseEntity<List<ClienteReponseDTO>> listarClientes() {
        return ResponseEntity.ok(service.listar());
    }
    
    
    @Operation(summary = "Insere um novo cliente", description = "A resposta retorna um cliente e seus atributos.")
   	@ApiResponses(value = { 
   			@ApiResponse(responseCode = "201", 
   			content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
   			description = "Cliente encontrado com sucesso"),
   			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
   			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
   			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
   			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody ClienteRequestDTO dto) {
        ClienteReponseDTO dtoResponse = service.inserir(dto);
        return ResponseEntity.created(null).body(dtoResponse);
    }
}
