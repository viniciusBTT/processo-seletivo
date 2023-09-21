package br.gov.sp.franciscomorato.educacao.processoseletivo;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
	public static void main(String[] args) {
		SpringApplication.run(ProcessoSeletivoApplication.class, args);
	}

}
