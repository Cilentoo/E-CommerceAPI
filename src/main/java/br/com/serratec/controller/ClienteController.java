package br.com.serratec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.ClienteReponseDTO;
import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.service.ClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	
	
	@GetMapping
	public ResponseEntity<List<ClienteReponseDTO>> listarClientes(){
		return ResponseEntity.ok(service.listar());
	}
	
	@PostMapping
	public ResponseEntity<Object> inserir(@RequestBody ClienteRequestDTO dto){
		ClienteReponseDTO dtoResponse = service.inserir(dto);
		
		return ResponseEntity.created(null).body(dtoResponse);
	}
}
