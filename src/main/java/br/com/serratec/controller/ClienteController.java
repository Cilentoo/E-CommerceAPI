package br.com.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@RequestMapping("/auth")
public class ClienteController {
    
    private final ClienteRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;    
    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteRepository repository, PasswordEncoder passwordEncoder, 
                             TokenService tokenService, ClienteService service) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClienteReponseDTO>> listarClientes() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody ClienteRequestDTO dto) {
        ClienteReponseDTO dtoResponse = service.inserir(dto);
        return ResponseEntity.created(null).body(dtoResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO body) {
        Cliente user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody CadastroRequestDTO body) {
        Optional<Cliente> user = this.repository.findByEmail(body.email());

        if (user.isEmpty()) {
            Cliente newUser = new Cliente();
            newUser.setNome(body.nome());
            newUser.setEmail(body.email());
            newUser.setCpf(body.cpf());
            newUser.setPassword(passwordEncoder.encode(body.password()));            
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginResponseDTO(newUser.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    // Getters e Setters
    public ClienteRepository getRepository() {
        return repository;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public TokenService getTokenService() {
        return tokenService;
    }

    public ClienteService getService() {
        return service;
    }
}
