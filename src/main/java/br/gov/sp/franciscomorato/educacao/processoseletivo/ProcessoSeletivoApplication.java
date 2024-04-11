package br.gov.sp.franciscomorato.educacao.processoseletivo;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import br.gov.sp.franciscomorato.educacao.processoseletivo.model.Role;
import br.gov.sp.franciscomorato.educacao.processoseletivo.model.User;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.RoleRepository;
import br.gov.sp.franciscomorato.educacao.processoseletivo.repository.UserRepository;
import br.gov.sp.franciscomorato.educacao.processoseletivo.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication

public class ProcessoSeletivoApplication
{

	@Value("${spring.datasource.password}")
	private String password; 

	@Autowired
	private UserRepository userService;
        
	@Autowired
	private RoleRepository RoleRepository;
        
	@PostConstruct
	public void createDefaultUser()
	{
            
            Role roleComum = new Role("ROLE_COMUM");
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleRoot = new Role("ROLE_ROOT");
            
            RoleRepository.save(roleComum);
            RoleRepository.save(roleAdmin);
            RoleRepository.save(roleRoot);
            
            userService.save(new User("admin", password, new Role("ROLE_ADMIN")));
	}
	public static void main(String[] args) 
	{
		SpringApplication.run(ProcessoSeletivoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder)
	{
            return builder.build();
	}
}
