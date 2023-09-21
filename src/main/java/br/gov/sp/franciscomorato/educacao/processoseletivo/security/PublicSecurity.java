package br.gov.sp.franciscomorato.educacao.processoseletivo.security;

import br.gov.sp.franciscomorato.educacao.processoseletivo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

/**
 * classe de configuração de autenticação pública
 * será o primeiro local de tentativa de autenticação
 * caso não encontre, tentará via LDAP
 * @author
 * @see UserService
 */
@Configuration
@Order(2)
public class PublicSecurity
{
    @Autowired
    private UserService userService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder builder) throws Exception
    {
        builder.userDetailsService(userService);
    }
}
