package br.com.serratec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.dto.EnderecoResponseDTO;
import br.com.serratec.model.Endereco;
import br.com.serratec.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	public EnderecoResponseDTO buscaCep(String cep) {
		var endereco = Optional.ofNullable(repository.findByCep(cep));
		System.out.println(endereco);
		if (endereco.isPresent()) {
			System.out.println("entrou");
			return new EnderecoResponseDTO(endereco.get());
		}
		else {
			RestTemplate restTemplate = new RestTemplate();
			String uri ="https://viacep.com.br/ws/" + cep + "/json/";
			Optional<Endereco> viaCep = Optional.ofNullable(restTemplate.getForObject(uri, Endereco.class));
			if (viaCep.get().getCep() != null) {
				viaCep.get().setCep(cep);
				return inserir(viaCep.get());
			}else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}
		}
	}
	
	private EnderecoResponseDTO inserir(Endereco endereco) {
		endereco = repository.save(endereco);
		return new EnderecoResponseDTO(endereco);
	}
}
