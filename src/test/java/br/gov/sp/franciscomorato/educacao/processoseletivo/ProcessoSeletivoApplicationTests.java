package br.gov.sp.franciscomorato.educacao.processoseletivo;

import br.gov.sp.franciscomorato.educacao.processoseletivo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProcessoSeletivoApplicationTests {

	@Autowired
	UserService userService;

	@Test
	void contextLoads() {

	}

}
