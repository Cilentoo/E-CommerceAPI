package br.com.serratec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String destinatario, String assunto, String texto) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("daiane.c.santos11@aluno.senai.br");
		mail.setTo(destinatario);
		mail.setSubject(assunto);
		mail.setText("Dados de cadastro do usuario:\n" + texto + "\n\n\n E-commerceAPI");
		javaMailSender.send(mail);
	}

}
