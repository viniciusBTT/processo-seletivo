package br.gov.sp.franciscomorato.educacao.processoseletivo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * autenticação via ldap
 * @author thiago
 */
@Configuration
@EnableWebSecurity
@Order(1)
public class LdapSecurity
{
    @Value("${ldap.url}")
    private String url;

    @Value("${ldap.port}")
    private Integer port;

    @Value("${ldap.username}")
    private String userManager;

    @Value("${ldap.password}")
    private String password;

    @Value("${ldap.search.base}")
    private String groupSearchFilter;

    @Value("${ldap.base.dn}")
    private String baseDn;

    @Value("${ldap.user.search.filter}")
    private String userSearchFilter;

    @Autowired
    protected void configure(AuthenticationManagerBuilder builder) throws Exception
    {
        builder
            .ldapAuthentication()
            .userSearchFilter(this.userSearchFilter)
            .userSearchBase(this.baseDn)
            .groupSearchBase(this.groupSearchFilter+","+ this.baseDn)
            .groupSearchFilter(this.groupSearchFilter)
            .contextSource()
            .url(this.url)
            .port(this.port)
            .managerDn(this.userManager)
            .managerPassword(this.password);
    }
}
