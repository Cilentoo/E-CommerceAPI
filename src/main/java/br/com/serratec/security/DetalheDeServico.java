package br.com.serratec.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.serratec.model.Cliente;
import br.com.serratec.model.User;
import br.com.serratec.repository.ClienteRepository;
import br.com.serratec.repository.UserRepository;

@Component
public class DetalheDeServico implements UserDetailsService {
	@Autowired
	private UserRepository repository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    User user = this.repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	}	
}
