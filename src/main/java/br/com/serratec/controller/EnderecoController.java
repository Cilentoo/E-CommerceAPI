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
import br.com.serratec.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	
	@Autowired
	private EnderecoService enderecoService;
	
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
