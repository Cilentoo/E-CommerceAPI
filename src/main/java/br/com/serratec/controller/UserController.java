package br.com.serratec.controller;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.dto.CadastroRequestDTO;
import br.com.serratec.dto.LoginRequestDTO;
import br.com.serratec.dto.LoginResponseDTO;
import br.com.serratec.enums.UsuarioRole;
import br.com.serratec.model.User;
import br.com.serratec.repository.UserRepository;
import br.com.serratec.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auth")
public class UserController {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final TokenService tokenService;

	public UserController(UserRepository repository, PasswordEncoder passwordEncoder, TokenService tokenService) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.tokenService = tokenService;
	}

	@Operation(summary = "Faz um login via token", description = "A resposta um nome de user e seu token pra mexer no sistema.")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }, description = "Usuario logado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequestDTO body) {
		User user = this.repository.findByEmail(body.email())
				.orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
		if (passwordEncoder.matches(body.password(), user.getPassword())) {
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new LoginResponseDTO(user.getNome(), token));
		}
		return ResponseEntity.badRequest().build();
	}

	@Operation(summary = "Insere um novo cadastro de Usuario/Admin", description = "A resposta retorna um novo cadastro")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }, description = "Usuario/Admin cadastrado com sucesso"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PostMapping("/cadastro")
	public ResponseEntity<LoginResponseDTO> register(@RequestBody CadastroRequestDTO body) {
		Optional<User> user = this.repository.findByEmail(body.email());

		if (user.isEmpty()) {
			User newUser = new User();
			newUser.setNome(body.nome());
			newUser.setEmail(body.email());
			newUser.setPassword(passwordEncoder.encode(body.password()));
			newUser.setRole(body.role());
			this.repository.save(newUser);

			String token = this.tokenService.generateToken(newUser);
			return ResponseEntity.ok(new LoginResponseDTO(newUser.getNome(), token));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@Operation(summary = "Atualiza um Usuário/Admin existente", description = "A resposta atualiza um Usuário/Admin")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }, description = "Usuario/Admin cadastrado"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@PutMapping("/{id}")
	public ResponseEntity<User> atualizarUsuario(@PathVariable Long id, @RequestBody CadastroRequestDTO body) {
		Optional<User> usuarioExistente = repository.findById(id);

		if (usuarioExistente.isPresent()) {
			User usuario = usuarioExistente.get();
			usuario.setNome(body.nome());
			usuario.setEmail(body.email());
			usuario.setPassword(passwordEncoder.encode(body.password()));
			usuario.setRole(body.role());
			repository.save(usuario);
			return ResponseEntity.ok(usuario);
		}
		return ResponseEntity.notFound().build();
	}

	
	@Operation(summary = "Deleta um Usuário existente", description = "A resposta deleta um Usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", content = {
			@Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }, description = "Usuario cadastrado"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Não há permissão para acessar o recurso"),
			@ApiResponse(responseCode = "404", description = "Recurso não encontrado"),
			@ApiResponse(responseCode = "505", description = "Exceção interna da aplicação") })
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
		Optional<User> usuario = repository.findById(id);

		if (usuario.isPresent()) {
			if(usuario.get().getRole().equals(UsuarioRole.USER)) {
				repository.deleteById(id);
				return ResponseEntity.noContent().build();
			}else {
				throw new RuntimeException("Você não pode deletar um Administrador");
			}
		}
		return ResponseEntity.notFound().build();
	}
}
