package br.gov.sp.franciscomorato.educacao.processoseletivo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ComponentScan(basePackages = {
		"br.gov.sp.franciscomorato.educacao.processoseletivo.security",
		"br.gov.sp.franciscomorato.educacao.processoseletivo.controller",
})

public class ProcessoSeletivoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessoSeletivoApplication.class, args);
	}

}
