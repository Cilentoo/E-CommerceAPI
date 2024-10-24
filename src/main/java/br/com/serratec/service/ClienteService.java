package br.com.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import br.com.serratec.exception.IdException;
import br.com.serratec.config.MailConfig;
import br.com.serratec.dto.ClienteReponseDTO;
import br.com.serratec.dto.ClienteRequestDTO;
import br.com.serratec.exception.EmailException;
import br.com.serratec.model.Cliente;
import br.com.serratec.model.Endereco;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.repository.EnderecoRepository;

@Service
public class ClienteService {
	
	@Autowired 
	private ClienteRepository clienteRepository;
	
	@Autowired
	private MailConfig mailConfig;
	
	@Autowired 
	private EnderecoRepository enderecoRepository;
	
	public List<ClienteReponseDTO> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteReponseDTO> dtos = new ArrayList<>();
		for (Cliente cliente : clientes) {
			dtos.add(new ClienteReponseDTO(cliente));
		}
		return dtos;
	}
    
	public ClienteReponseDTO listarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new IdException("Id do cliente não encontrado."));
        return new ClienteReponseDTO(cliente);
    }
	
	
	public ClienteReponseDTO inserir(ClienteRequestDTO dto) {
		Optional<Cliente> c = clienteRepository.findByEmail(dto.getEmail());
		if(c.isPresent()) {
			throw new EmailException("Email existente");
		}
		
		Cliente cliente = new Cliente();
		cliente.setNome(dto.getNome());
		cliente.setEmail(dto.getEmail());
		cliente.setCpf(dto.getCpf());
		cliente.setComplemento(dto.getComplemento());
		cliente.setNumero(dto.getNumero());
		
		var endereco = Optional.ofNullable(enderecoRepository.findByCep(dto.getCep()));
		if (endereco.isPresent()) {;
			cliente.setEndereco(endereco.get());
		}
		else {
			RestTemplate restTemplate = new RestTemplate();
			String uri ="https://viacep.com.br/ws/" + dto.getCep() + "/json/";
			Optional<Endereco> viaCep = Optional.ofNullable(restTemplate.getForObject(uri, Endereco.class));
			if (viaCep != null && viaCep.get().getCep() != null) {
				viaCep.get().setCep(dto.getCep());
				enderecoRepository.save(viaCep.get());
				cliente.setEndereco(endereco.get());
			}else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}
		}
		//EnderecoResponseDTO enderecoDTO = enderecoService.buscaCep(dto.getCep());
		//endereco.setCep(enderecoDTO.getCep());
		//endereco.setBairro(enderecoDTO.getBairro());
		//endereco.setLocalidade(enderecoDTO.getLocalidade());
		//endereco.setLogradouro(enderecoDTO.getLogradouro());
		//endereco.setUf(enderecoDTO.getUf());
		
		//cliente.setEndereco(endereco);
		
		cliente = clienteRepository.save(cliente);
		mailConfig.sendEmail(cliente.getEmail(), "Confirmação de Cadastro", cliente.toString());
		return new ClienteReponseDTO(cliente);
	}
}
