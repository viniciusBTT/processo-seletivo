package br.gov.sp.franciscomorato.educacao.processoseletivo;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.UserRepository;
import br.gov.sp.franciscomorato.educacao.processoseletivo.service.UserService;

@SpringBootApplication

@ComponentScan(basePackages = {
		"br.gov.sp.franciscomorato.educacao.processoseletivo.security",
		"br.gov.sp.franciscomorato.educacao.processoseletivo.controller",
		"br.gov.sp.franciscomorato.educacao.processoseletivo.model",
		"br.gov.sp.franciscomorato.educacao.processoseletivo.repository",
		"br.gov.sp.franciscomorato.educacao.processoseletivo.security",
		"br.gov.sp.franciscomorato.educacao.processoseletivo.service",
})

@EntityScan(basePackages = {"br.gov.sp.franciscomorato.educacao.processoseletivo.model"})

@EnableJpaRepositories(basePackages = {"br.gov.sp.franciscomorato.educacao.processoseletivo.repository"})

public class ProcessoSeletivoApplication
{


	@Autowired
	private UserRepository userService;

	@PostConstruct
	public void createDefaultUser()
	{	
		userService.save(new User("admin", "123", new Role("ROLE_ADMIN")));
	}
	public static void main(String[] args) {
		SpringApplication.run(ProcessoSeletivoApplication.class, args);
	}

}
