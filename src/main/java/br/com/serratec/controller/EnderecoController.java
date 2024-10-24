package br.com.serratec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.serratec.dto.EnderecoResponseDTO;
import br.com.serratec.model.Cliente;
import br.com.serratec.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	
	@Autowired
	private EnderecoService enderecoService;
	
	
	 @Operation(summary = "Busca e guarda um cep pelo via Cep", description = "A resposta retorna um endereço.")
		@ApiResponses(value = { 
				@ApiResponse(responseCode = "201", 
				content = {@Content(schema = @Schema(implementation = Cliente.class), mediaType = "application/json")},
				description = "Endereço encontrado"),
				@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
				@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
				@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
				@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@GetMapping("/buscar/{cep}")
    public ResponseEntity<EnderecoResponseDTO> buscarEnderecoPorCep(@PathVariable String cep) {
        try {
            EnderecoResponseDTO enderecoResponse = enderecoService.buscaCep(cep);
            return ResponseEntity.ok(enderecoResponse);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
