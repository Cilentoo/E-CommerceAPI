package br.com.serratec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private DetalheDeServico userDetailsService;

    @Autowired
    SecurityFilter securityFilter;

    @SuppressWarnings("removal")
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                		.requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permite acesso ao login
                        .requestMatchers(HttpMethod.POST, "/auth/cadastro").permitAll() // Permite acesso ao cadastro
                        .requestMatchers("/h2-console/**").permitAll() // Permite acesso ao H2 Console
                        .requestMatchers(HttpMethod.POST, "/ADMIN/**").hasRole("ADMIN") // Definir o que será de permissão do admin
                        .requestMatchers(HttpMethod.GET, "/USER/**").hasRole("USER") // Definir o que será de permissão do user                       
                        .anyRequest().authenticated() // Qualquer outra requisição deve ser autenticada
                )                
                .headers((headers) -> headers.disable()) // Permite que o H2 Console funcione
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}